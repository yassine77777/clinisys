package com.inn.clinisys.repository;

import com.inn.clinisys.entity.Article;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ArticleRepository extends JpaRepository<Article, String> {

}
