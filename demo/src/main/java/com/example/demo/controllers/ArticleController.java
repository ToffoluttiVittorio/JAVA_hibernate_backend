package com.example.demo.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import com.example.demo.models.Article;
import com.example.demo.models.Author;
import com.example.demo.services.ArticleService;
import com.example.demo.services.AuthorService;

import java.util.List;

@Controller
public class ArticleController {

    @Autowired
    private AuthorService authorService;

    @Autowired
    private ArticleService articleService;

    @GetMapping("/myarticle")
    public String listArticles(Model model) {
        List<Article> articles = articleService.findAll();
        List<Author> authors = authorService.findAll();
        model.addAttribute("authors", authors);
        model.addAttribute("articles", articles);
        return "list";
    }

    @GetMapping("/addAuthor")
    public String showAddAuthorForm(Model model) {
        model.addAttribute("author", new Author());
        return "add";
    }

    @PostMapping("/addAuthor")
    public String addAuthor(@ModelAttribute Author author) {
        authorService.save(author);
        return "redirect:/myarticle";
    }

    @GetMapping("/addArticle")
    public String showAddArticleForm(Model model) {
        List<Author> authors = authorService.findAll();
        model.addAttribute("authors", authors);
        model.addAttribute("article", new Article());
        return "add-article";
    }
    @PostMapping("/addArticle")
    public String addArticle(@ModelAttribute Article article) {
        articleService.save(article);
        return "redirect:/myarticle";
    }

    @GetMapping("/editArticle/{id}")
    public String showEditArticleForm(@PathVariable Long id, Model model) {
        Article article = articleService.findById(id)
                .orElseThrow(() -> new IllegalArgumentException("Invalid article id: " + id));

        List<Author> authors = authorService.findAll();

        model.addAttribute("authors", authors);
        model.addAttribute("article", article);
        return "edit";
    }

    @PostMapping("/editArticle/{id}")
    public String editArticle(@PathVariable Long id, @ModelAttribute Article article) {
        article.setId(id);
        articleService.save(article);
        return "redirect:/articles";
    }

    @GetMapping("/deleteArticle/{id}")
    public String deleteArticle(@PathVariable Long id) {
        Article article = articleService.findById(id)
                .orElseThrow(() -> new IllegalArgumentException("Invalid article id: " + id));

        articleService.delete(article);
        return "redirect:/articles";
    }
}
