package com.prueba.obrestdatajpa;

import java.time.LocalDate;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.ApplicationContext;

import com.prueba.obrestdatajpa.Models.Book;
import com.prueba.obrestdatajpa.Repository.BookRepository;

@SpringBootApplication
public class ObRestDatajpaApplication {

	public static void main(String[] args) {
		ApplicationContext context = SpringApplication.run(ObRestDatajpaApplication.class, args);

		BookRepository repository = context.getBean(BookRepository.class);

		//CRUD
		//Crear un libro
		Book book1 = new Book(null, "Hasta los cojones del pensamiento positivo", "Buenaventura del charco", 450, 19.99, LocalDate.of(2018, 12, 1), true);
		Book book2 = new Book(null, "La Torre Oscura", "Stephen King", 250, 9.99, LocalDate.of(2015, 06, 12), true);

		//Almacenar el libro en la BBDD
		System.out.println("En la base de datos hay: " + repository.count() + " libros");
		repository.save(book1);
		System.out.println("En la base de datos hay: " + repository.count() + " libros");
		repository.save(book2);

		//Recuperar todos los libors
		repository.findAll();
		System.out.println("En la base de datos hay: " + repository.count() + " libros");

		//Borrar libros
		/* repository.deleteById(1L);
		System.out.println("En la base de datos hay: " + repository.count() + " libros"); */
	}

}
