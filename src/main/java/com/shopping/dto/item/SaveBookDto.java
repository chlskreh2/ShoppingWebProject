package com.shopping.dto.item;

import com.shopping.domain.Book;
import com.shopping.domain.FileImage;

import java.time.LocalDate;

public class SaveBookDto {

    private String itemName;
    private Integer price;
    private Integer stock;
    private Integer deliveryPrice;
    private String information;
    private FileImage fileImage;
    private String writer;
    private String publisher;
    private LocalDate datePublish;

    public Book saveBook() {
        Book book = new Book();
        book.setItemName(itemName);
        book.setPrice(price);
        book.setStock(stock);
        book.setDeliveryPrice(deliveryPrice);
        book.setInformation(information);
        book.setFileImage(fileImage);
        book.setWriter(writer);
        book.setPublisher(publisher);
        book.setDatePublish(datePublish);
        return book;
    }

}
