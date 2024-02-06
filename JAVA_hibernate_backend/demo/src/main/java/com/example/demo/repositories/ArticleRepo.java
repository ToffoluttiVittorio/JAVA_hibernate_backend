package com.example.demo.repositories;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;
import com.example.demo.models.Article;

@Repository
public interface ArticleRepo extends CrudRepository<Article, Long> {
}
