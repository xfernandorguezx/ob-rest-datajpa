package com.prueba.obrestdatajpa.service;

import static org.junit.jupiter.api.Assertions.*;

import java.time.LocalDate;

import org.junit.jupiter.api.Test;

import com.prueba.obrestdatajpa.Models.Book;

public class BookPriceCalculatorTest {

    @Test
    void testCalculateBookPrice() {
        //Configuración de los datos para la realización de la prueba
        Book book = new Book(1L, "Titulo", "Yo", 340, 29.9, LocalDate.now(), true);
        BookPriceCalculator calculator = new BookPriceCalculator();

        //Se ejecuta el método que queremos testear
        double price = calculator.calculateBookPrice(book);

        //Comprobamos aserciones
        assertTrue(price > 0);
        assertEquals(34.9, price);
    }
    
}
