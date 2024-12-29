package com.inn.clinisys.controller;

import com.inn.clinisys.dto.ArticleDto;
import com.inn.clinisys.entity.Article;
import com.inn.clinisys.exception.ArticleNotFoundException;
import com.inn.clinisys.service.ArticleService;
import org.jetbrains.annotations.NotNull;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/articles")
public class ArticleRestController {
    @Autowired
    ArticleService articleService;
    @PostMapping("/save")
    public ArticleDto save(@RequestBody ArticleDto articleDto) {
        return articleService.save(articleDto);
    }
    @PutMapping("/update/{id}")

    public ArticleDto update(@PathVariable String id, @RequestBody  ArticleDto articleDto) throws ArticleNotFoundException {
        return articleService.update(id, articleDto);
    }
    @GetMapping("/get/{id}")
    public ArticleDto getById(@PathVariable String id) throws ArticleNotFoundException {
        return articleService.getById(id);
    }

    @GetMapping("/all")

    public List<Article> getAllArticles(){
        List<Article> projects = articleService.getAllArticles();
        return projects.stream().toList();
    }

    @DeleteMapping("/delete/{id}")
    public void deleteById(@PathVariable String id){
        articleService.deleteById(id);
    }

    @ExceptionHandler(Exception.class)
    public ResponseEntity<String> exceptionHandler(@NotNull Exception exception){
        return new ResponseEntity<>(exception.getMessage(), HttpStatus.INTERNAL_SERVER_ERROR);
    }


}
