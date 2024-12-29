package com.inn.clinisys.util;

import com.inn.clinisys.dto.ArticleDto;
import com.inn.clinisys.dto.DepotDto;
import com.inn.clinisys.dto.StockDto;
import com.inn.clinisys.entity.Article;
import com.inn.clinisys.entity.Depot;
import com.inn.clinisys.entity.Stock;
import com.inn.clinisys.exception.ArticleNotFoundException;
import com.inn.clinisys.exception.DepotNotFoundException;
import lombok.NonNull;

import java.util.List;

public interface Mappers {
    Article fromArticleDTO(ArticleDto dto);
    ArticleDto fromArticle(Article article);

    Depot fromDepotDto(DepotDto dto);
    DepotDto fromDepot(Depot depot);
    Stock fromStockDto(StockDto dto) throws ArticleNotFoundException, DepotNotFoundException;
    StockDto fromStock(Stock stock);

    List<StockDto> fromStockList(@NonNull List<Stock> stocks);
}
