package com.inn.clinisys.service;

import com.inn.clinisys.dto.ArticleDto;
import com.inn.clinisys.entity.Article;
import com.inn.clinisys.exception.ArticleNotFoundException;

import java.util.List;

public interface ArticleService {
    ArticleDto save(ArticleDto articleDto);
    ArticleDto update(String id, ArticleDto articleDto) throws ArticleNotFoundException;
    void deleteById(String id);

    ArticleDto getById(String id) throws ArticleNotFoundException;

    List<Article> getAllArticles();

}
