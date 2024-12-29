package com.inn.clinisys.util.implementation;

import com.inn.clinisys.dto.ArticleDto;
import com.inn.clinisys.dto.DepotDto;
import com.inn.clinisys.dto.StockDto;
import com.inn.clinisys.entity.Article;
import com.inn.clinisys.entity.Depot;
import com.inn.clinisys.entity.Stock;
import com.inn.clinisys.exception.ArticleNotFoundException;
import com.inn.clinisys.exception.DepotNotFoundException;
import com.inn.clinisys.repository.ArticleRepository;
import com.inn.clinisys.repository.DepotRepository;
import com.inn.clinisys.util.Mappers;
import lombok.NonNull;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service

public class mapperImpl implements Mappers {
    @Autowired
    ArticleRepository articleRepository;
    @Autowired
    DepotRepository depotRepository;
    @Override
    public Article fromArticleDTO( @NonNull ArticleDto dto) {
        return Article.builder().id(dto.id()).designation_article(dto.designation_article()).build();
    }

    @Override
    public ArticleDto fromArticle(Article article) {
        return new ArticleDto(article.getId(), article.getDesignation_article()
        );
    }

    @Override
    public Depot fromDepotDto(DepotDto dto) {
        return Depot.builder().id(dto.id()).nomDepot(dto.nomDepot()).build();
    }

    @Override
    public DepotDto fromDepot(Depot depot) {
        return new DepotDto(depot.getId(), depot.getNomDepot()
        );
    }

    @Override
    public Stock fromStockDto(StockDto dto) throws ArticleNotFoundException, DepotNotFoundException {
        Article article = articleRepository.findById(dto.articleId())
                .orElseThrow( ()-> new ArticleNotFoundException("stock you try to update with id = '"+dto.articleId()+"' not found."));
        Depot depot = depotRepository.findById(dto.depotId())
                .orElseThrow( ()-> new DepotNotFoundException("stock you try to update with id = '"+dto.depotId()+"' not found."));
        article.setId(dto.articleId());
        depot.setId(dto.depotId());
        return Stock.builder().id(dto.id()).qte(dto.qte())
                .datePeremption(dto.datePeremption()).article(article).depot(depot)
                .build();
    }

    @Override
    public StockDto fromStock(Stock stock) {
        return new StockDto(stock.getId(), stock.getQte(),
                stock.getDatePeremption(), stock.getArticle().getDesignation_article(),
                stock.getDepot().getNomDepot()

        );
    }



    @Override
    public List<StockDto> fromStockList(@NonNull List<Stock> stocks) {
        return stocks.stream()
                .map(this::fromStock)
                .collect(Collectors.toList());
    }
}
