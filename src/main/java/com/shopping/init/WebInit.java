package com.shopping.init;

import com.shopping.domain.Address;
import com.shopping.domain.Book;
import com.shopping.domain.FileImage;
import com.shopping.domain.Member;
import com.shopping.service.ItemService;
import com.shopping.service.MemberService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.multipart.commons.CommonsMultipartFile;

import javax.annotation.PostConstruct;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.time.LocalDateTime;

@Component
@RequiredArgsConstructor
public class WebInit {

    private final ItemService itemService;
    private final MemberService memberService;

    @PostConstruct
    public void init() {
        Book book1 = new Book();
        book1.setItemName("book1");
        book1.setPrice(10000);
        book1.setStock(10);
        book1.setDeliveryPrice(3000);
        book1.setDiscountPercent(0);
        book1.setViewCount(0L);
        FileImage fileImage = new FileImage("spring-image.jpg");
        book1.setFileImage(fileImage);
        book1.setEnrollDate(LocalDateTime.now());
        itemService.save(book1);

        Book book3 = new Book();
        book3.setItemName("book3");
        book3.setPrice(30000);
        book3.setStock(0);
        book3.setDeliveryPrice(0);
        book3.setDiscountPercent(20);
        book3.setViewCount(0L);
        book3.setEnrollDate(LocalDateTime.now());
        itemService.save(book3);

        Book book4 = new Book();
        book4.setItemName("book4");
        book4.setPrice(40000);
        book4.setStock(10);
        book4.setDeliveryPrice(3000);
        book4.setDiscountPercent(10);
        book4.setViewCount(0L);
        book4.setEnrollDate(LocalDateTime.now());
        itemService.save(book4);

        Book book5 = new Book();
        book5.setItemName("book5");
        book5.setPrice(50000);
        book5.setStock(10);
        book5.setDeliveryPrice(3000);
        book5.setDiscountPercent(10);
        book5.setViewCount(0L);
        itemService.save(book5);

        Book book2 = new Book();
        book2.setItemName("book2");
        book2.setPrice(20000);
        book2.setStock(20);
        book2.setDeliveryPrice(2000);
        book2.setDiscountPercent(10);
        book2.setViewCount(0L);
        book2.setEnrollDate(LocalDateTime.now());
        itemService.save(book2);

        Member member1 = Member.builder()
                .userId("test").password("1234")
                .gender("남").birth(19990101)
                .phone("01012341234").address(new Address("1111", "2222"))
                .build();
        memberService.join(member1);
    }

}
