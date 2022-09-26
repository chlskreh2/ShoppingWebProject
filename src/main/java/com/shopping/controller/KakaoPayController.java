package com.shopping.controller;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.SerializationFeature;
import com.fasterxml.jackson.datatype.jsr310.JavaTimeModule;
import com.shopping.dto.api.ApprovePaymentDto;
import com.shopping.dto.api.ReadyPaymentDto;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.CookieValue;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletResponse;
import java.io.*;
import java.net.HttpURLConnection;
import java.net.URL;

@Slf4j
@Controller
public class KakaoPayController {

    @RequestMapping("/items/pay/ready")
    public String readyPay(HttpServletResponse response) throws IOException {
        URL url = new URL("https://kapi.kakao.com/v1/payment/ready");
        HttpURLConnection httpURLConnection = setHttpURLConnectionProperty(url);
        String putApiValue = "cid=TC0ONETIME" +
                                "&partner_order_id=partner_order_id" +
                                "&partner_user_id=partner_user_id" +
                                "&item_name=초코파이" +
                                "&quantity=1" +
                                "&total_amount=1" +
                                "&vat_amount=0" +
                                "&tax_free_amount=0" +
                                "&approval_url=http://localhost:8080/items/pay/approve" +
                                "&fail_url=http://localhost:8080/" +
                                "&cancel_url=http://localhost:8080/";
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

        return receiveApi;
    }

    private HttpURLConnection setHttpURLConnectionProperty(URL url) throws IOException {
        HttpURLConnection httpURLConnection = (HttpURLConnection) url.openConnection();
        httpURLConnection.setRequestMethod("POST");
        httpURLConnection.setRequestProperty("Authorization", "KakaoAK ");
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