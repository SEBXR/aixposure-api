package org.aixposure.controller;

import java.util.List;

import org.aixposure.model.Admin;
import org.aixposure.model.Article;
import org.aixposure.model.Category;
import org.aixposure.repository.AdminRepository;
import org.aixposure.repository.ArticleRepository;
import org.aixposure.repository.CategoryRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/aixposure-api/")
public class MainController {
    @Autowired
    private CategoryRepository categoryJPA;
    @Autowired
    private ArticleRepository articleJPA;
    @Autowired
    private AdminRepository adminJPA;

    @GetMapping("/categories")
    @CrossOrigin
    public List<Category> findAllCategory() {
        return categoryJPA.findAll();
    }

    @GetMapping("/articles")
    @CrossOrigin
    @Cacheable("articles")
    public List<Article> findAllArticle() {
        return articleJPA.findAll();
    }

    @PostMapping("/delete/{id}")
    @CrossOrigin
    public Integer delete(@PathVariable Integer id) {
        Article at = articleJPA.findById(id).get();
        try {
            articleJPA.delete(at);
        } catch (Exception e) {
            return 0;
        }
        return 1;
    }

    @PostMapping("/create")
    @CrossOrigin
    public @ResponseBody Integer create(String title, String category, String content) {
        Category ct = categoryJPA.findById(Integer.parseInt(category)).get();
        Article at = new Article(null, title, ct, content);
        articleJPA.save(at);
        return at.getId();
    }

    @PostMapping("/login")
    @CrossOrigin
    public Integer login(String username, String password) {
        Admin ad = adminJPA.findByUsername(username);
        if (ad == null)
            return 0;
        if (!ad.getPassword().equals(password))
            return 0;
        return ad.getId();
    }

    @PostMapping("/modify")
    @CrossOrigin
    public Integer modify(Integer id, String title, String content) {
        Article at = articleJPA.findById(id).get();
        at.setTitle(title);
        at.setContent(content);
        try {
            articleJPA.save(at);
        } catch (Exception e) {
            return 0;
        }
        return 1;
    }

    @GetMapping("/findArticle/{id}")
    @CrossOrigin
    public Article findById(@PathVariable Integer id ){
        return articleJPA.findById(id).get();
    }
}
