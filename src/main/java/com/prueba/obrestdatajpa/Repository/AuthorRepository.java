package com.prueba.obrestdatajpa.Repository;

import com.prueba.obrestdatajpa.Models.Author;
import org.springframework.data.jpa.repository.JpaRepository;

public interface AuthorRepository extends JpaRepository<Author, Long> {
}
