package com.example.demo.repositories;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;
import com.example.demo.models.Author;

@Repository
public interface AuthorRepo extends CrudRepository<Author, Long> {
}