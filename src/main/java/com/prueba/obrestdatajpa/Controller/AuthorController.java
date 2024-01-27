package com.prueba.obrestdatajpa.Controller;

import com.prueba.obrestdatajpa.Models.Author;
import com.prueba.obrestdatajpa.Repository.AuthorRepository;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.HttpHeaders;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
public class AuthorController {

    private final Logger log = LoggerFactory.getLogger(AuthorController.class);

    private AuthorRepository repository;

    public AuthorController(AuthorRepository repository) {
        this.repository = repository;
    }

    //Buscar los autores que están en la BBDD
    @GetMapping(value = "/api/author")
    public List<Author> findAll(){
        log.info("Rest request method findAll authors");
        return repository.findAll();
    }

    //Añadir un nuevo autor
    @PostMapping(value = "/api/author")
    public ResponseEntity<Author> addAuthor(@RequestBody Author author){
        log.info("REST Request method add Author");

        if(author.getId() != null){
            log.warn("Trying to add a existing author");
            return ResponseEntity.badRequest().build();
        }
        //Guardar el nuevo autor
        Author result = repository.save(author);
        return ResponseEntity.ok(result);
    }

    //Buscar un autor filtrando por su ID
    @GetMapping(value = "api/author/{id}")
    public ResponseEntity<Author> findAuthorById(@PathVariable Long id){
        log.info("REST Request method findAuthorById");
        Optional<Author> author = repository.findById(id);

        if(author.isEmpty()){
            return ResponseEntity.notFound().build();
        } else{
            return ResponseEntity.ok(author.get());
        }
    }
}
