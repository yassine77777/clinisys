package com.inn.clinisys.service.implementation;

import com.inn.clinisys.dto.ArticleDto;
import com.inn.clinisys.entity.Article;
import com.inn.clinisys.exception.ArticleNotFoundException;
import com.inn.clinisys.repository.ArticleRepository;
import com.inn.clinisys.service.ArticleService;
import com.inn.clinisys.util.Mappers;
import lombok.extern.slf4j.Slf4j;
import org.jetbrains.annotations.NotNull;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
@Service
@Transactional
@Slf4j

public class ArticleServiceImpl implements ArticleService {
    @Autowired
    ArticleRepository articleRepository;
    @Autowired
    Mappers mapper;


    @Override
    public ArticleDto save(ArticleDto articleDto) {
        Article article = mapper.fromArticleDTO(articleDto);
        Article articleSaved = articleRepository.save(article);
        log.info("article created successfully");
        return mapper.fromArticle(articleSaved);
    }

    @Override
    public ArticleDto update(String id, ArticleDto articleDto) throws ArticleNotFoundException {
        Article article = articleRepository.findById(id)
                .orElseThrow( ()-> new ArticleNotFoundException("article you try to update with id = '"+id+"' not found."));
        updateArticleFields(article, articleDto);
        Article articleUpdate = articleRepository.save(article);
        log.info("Article updated successfully");
        return mapper.fromArticle(articleUpdate);
    }

    @Override
    public void deleteById(String id) {
        articleRepository.deleteById(id);
        log.info("article deleted successfully");

    }

    @Override
    public ArticleDto getById(String id) throws ArticleNotFoundException {
        Article article = articleRepository.findById(id)
                .orElseThrow( () -> new ArticleNotFoundException("Article not found with id '"+id+"'."));
        log.info("article found with id '"+id+"' found successfully.");
        return mapper.fromArticle(article);
    }

    @Override
    public List<Article> getAllArticles() {
        return articleRepository.findAll();
    }
    private void updateArticleFields(@NotNull Article article, @NotNull ArticleDto articleDto){
        article.setDesignation_article(articleDto.designation_article());


    }

}
