package com.prueba.obrestdatajpa.Repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.prueba.obrestdatajpa.Models.Book;

@Repository
public interface BookRepository extends JpaRepository<Book, Long>{

}
