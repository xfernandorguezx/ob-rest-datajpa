package com.prueba.obrestdatajpa.service;

import com.prueba.obrestdatajpa.Models.Book;

public class BookPriceCalculator {
    public double calculateBookPrice(Book book){
        double price = book.getPrice();
        if(book.getPages() > 300){
            price += 5;
        }
        return price;
    }
}
