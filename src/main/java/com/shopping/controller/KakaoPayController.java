package com.shopping.controller;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.SerializationFeature;
import com.fasterxml.jackson.datatype.jsr310.JavaTimeModule;
import com.shopping.domain.Address;
import com.shopping.domain.Item;
import com.shopping.domain.Member;
import com.shopping.dto.api.ApprovePaymentDto;
import com.shopping.dto.api.ReadyPaymentDto;
import com.shopping.service.ItemService;
import com.shopping.service.OrderService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.*;
import java.net.HttpURLConnection;
import java.net.URL;

@Slf4j
@Controller
@RequiredArgsConstructor
public class KakaoPayController {

    private final OrderService orderService;
    private final ItemService itemService;

    @PostMapping("/items/pay/ready")
    public String readyPay(@RequestParam("itemId") Long id, @RequestParam("orderCount") Integer orderCount,
                           @ModelAttribute Address address, HttpServletRequest request, HttpServletResponse response) throws IOException {
        HttpSession session = request.getSession();
        Member member = (Member)session.getAttribute(MemberSession.LOGIN_SESSION);
        Long orderId = orderService.save(member, id, orderCount, address);
        Item item = itemService.showItem(id);
        int discount = item.getPrice() * orderCount * item.getDiscountPercent() / 100;
        int orderPrice = item.getPrice() * orderCount - discount + item.getDeliveryPrice();

        URL url = new URL("https://kapi.kakao.com/v1/payment/ready");
        HttpURLConnection httpURLConnection = setHttpURLConnectionProperty(url);
        String putApiValue = "cid=TC0ONETIME" +
                                "&partner_order_id=partner_order_id" +
                                "&partner_user_id=partner_user_id" +
                                "&item_name=" + id +
                                "&quantity=" + orderCount +
                                "&total_amount=" + orderPrice +
                                "&vat_amount=0" +
                                "&tax_free_amount=0" +
                                "&approval_url=http://localhost:8080/items/pay/approve" +
                                "&fail_url=http://localhost:8080/items/pay/fail/" + orderId +
                                "&cancel_url=http://localhost:8080/items/pay/cancel/" + orderId;
        sendOutputStream(httpURLConnection, putApiValue);

        String receiveApi = receiveInputStream(httpURLConnection);

        ObjectMapper objectMapper = new ObjectMapper();
        objectMapper.registerModule(new JavaTimeModule());
        objectMapper.disable(SerializationFeature.WRITE_DATES_AS_TIMESTAMPS);
        ReadyPaymentDto readyPaymentDto = objectMapper.readValue(receiveApi, ReadyPaymentDto.class);

        Cookie cookie = new Cookie("userTid", readyPaymentDto.getTid());
        response.addCookie(cookie);

        return "redirect:" + readyPaymentDto.getNext_redirect_pc_url();
    }

    @RequestMapping("/items/pay/approve")
    public String approvePay(@CookieValue(value = "userTid") String userTid ,@RequestParam String pg_token, Model model) throws IOException {
        URL url = new URL("https://kapi.kakao.com/v1/payment/approve");
        HttpURLConnection httpURLConnection = setHttpURLConnectionProperty(url);
        String putApiValue = "cid=TC0ONETIME" +
                                "&tid=" + userTid +
                                "&partner_order_id=partner_order_id" +
                                "&partner_user_id=partner_user_id" +
                                "&pg_token=" + pg_token;
        sendOutputStream(httpURLConnection, putApiValue);

        String receiveApi = receiveInputStream(httpURLConnection);

        ObjectMapper objectMapper = new ObjectMapper();
        objectMapper.registerModule(new JavaTimeModule());
        objectMapper.disable(SerializationFeature.WRITE_DATES_AS_TIMESTAMPS);
        ApprovePaymentDto approvePaymentDto = objectMapper.readValue(receiveApi, ApprovePaymentDto.class);
        model.addAttribute("approve", approvePaymentDto);

        return "item/paySuccess";
    }

    private HttpURLConnection setHttpURLConnectionProperty(URL url) throws IOException {
        HttpURLConnection httpURLConnection = (HttpURLConnection) url.openConnection();
        httpURLConnection.setRequestMethod("POST");
        httpURLConnection.setRequestProperty("Authorization", "KakaoAK 245d027cd0ac01ad2e6dce9ec2a580e2");
        httpURLConnection.setRequestProperty("Content-type", "application/x-www-form-urlencoded;charset=utf-8");
        httpURLConnection.setDoOutput(true);
        return httpURLConnection;
    }

    private static void sendOutputStream(HttpURLConnection httpURLConnection, String putApiValue) throws IOException {
        OutputStream outputStream = httpURLConnection.getOutputStream();
        DataOutputStream dataOutputStream = new DataOutputStream(outputStream);
        dataOutputStream.writeBytes(putApiValue);
        dataOutputStream.close();
    }

    private String receiveInputStream(HttpURLConnection httpURLConnection) throws IOException {
        int responseResult = httpURLConnection.getResponseCode();
        InputStream inputStream;
        if (responseResult == 200) {
            inputStream = httpURLConnection.getInputStream();
        } else {
            inputStream = httpURLConnection.getErrorStream();
        }
        InputStreamReader inputStreamReader = new InputStreamReader(inputStream);
        BufferedReader bufferedReader = new BufferedReader(inputStreamReader);
        String receiveApi = bufferedReader.readLine();
        return receiveApi;
    }

}