package com.prueba.obrestdatajpa.Controller;


import java.util.List;
import java.util.Optional;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.HttpHeaders;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestHeader;
import org.springframework.web.bind.annotation.RestController;

import com.prueba.obrestdatajpa.Models.Book;
import com.prueba.obrestdatajpa.Repository.BookRepository;

@RestController
public class BookController {

    private final Logger log = LoggerFactory.getLogger(BookController.class);

    private BookRepository repository;

    public BookController(BookRepository repository) {
        this.repository = repository;
    }
    
    
    //CRUD sobre la entidad Book

    //Buscar todos los libros en BBDD (ArrayList de libros)
    @GetMapping(value = "/api/books")
    public List<Book> findAll() {
        log.info("REST Request method findAll");
        //Recuperar y devolver los libros de BBDD
        return repository.findAll();
    }

    //Buscar un libro en BBDD filtrando por ID
    @GetMapping(value = "/api/books/{id}")
    public ResponseEntity<Book> findOneBookById(@PathVariable Long id){ //Vincula el id con el nombre de la variable. Si se llamara diferente de id por ejemplo identificador la variable seria @PathVariable("id") Long identificador
        log.info("REST Request method findOneBookById");
        Optional<Book> bookOptional = repository.findById(id);
        //Opcion 1

        if(bookOptional.isEmpty()){
            return ResponseEntity.notFound().build();
        } else{
            return ResponseEntity.ok(bookOptional.get());
        }

        //Opcion 2 (Programación funcional)

        /* return bookOptional.map(ResponseEntity::ok).orElseGet(() -> ResponseEntity.notFound().build()); */
    }

    //Crear un nuevo libro en la BBDD
    @PostMapping(value = "/api/books")
    public ResponseEntity<Book> createBook(@RequestBody Book book, @RequestHeader HttpHeaders headers){
        log.info("REST Request method createBook");

        System.out.println(headers.getConnection());

        if(book.getId() != null) {
            log.warn("Trying to create a book with existing id");
            return ResponseEntity.badRequest().build();
        }
        //Guardar un libro recibido por parametros en la BBDD
        Book result = repository.save(book);
        return ResponseEntity.ok(result);  //El libro devuelto se crea con una clave primaria
    }

    //Actualizar un libro existente en BBDD filtrando por su ID
    @PutMapping(value = "/api/books")
    public ResponseEntity<Book> updateBook(@RequestBody Book book) {
        log.info("REST Request method updateBook");
        if(book.getId().equals(null)){
            log.warn("Trying to update a unexisting book");
            return ResponseEntity.badRequest().build();
        }
        //Proceso de actualización
        if(!repository.existsById(book.getId())){
            log.warn("Trying to update a unexisting book");
            return ResponseEntity.notFound().build();
        }

        Book result = repository.save(book);
        return ResponseEntity.ok(result);
    }

    //Borrar un libro de la BBDD por si ID
    @DeleteMapping(value = "/api/books/{id}")
    public ResponseEntity<Book> deleteBook(@PathVariable Long id){
        log.info("REST Request method deleteBook");
        if(!repository.existsById(id)){
            log.warn("Trying to update a unexisting book");
            return ResponseEntity.notFound().build();
        }
        repository.deleteById(id);
        return ResponseEntity.noContent().build();
    }

    //Borrar todos los libros de la BBDD
    @DeleteMapping(value = "/api/books")
    public ResponseEntity<Book> deleteAllBooks() {
        log.info("REST Request method deleteAllBooks");
        repository.deleteAll();
        return ResponseEntity.noContent().build();
    }

    public BookRepository getRepository() {
        return repository;
    }

    public void setRepository(BookRepository repository) {
        this.repository = repository;
    }

}
