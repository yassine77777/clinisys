package com.inn.clinisys.service;

import com.inn.clinisys.dto.StockDto;
import com.inn.clinisys.entity.Stock;
import com.inn.clinisys.exception.ArticleNotFoundException;
import com.inn.clinisys.exception.DepotNotFoundException;
import com.inn.clinisys.exception.StockNotFoundException;

import java.util.List;

public interface StockService {
    StockDto save(StockDto stockDto) throws ArticleNotFoundException,DepotNotFoundException;

    StockDto update(String id, StockDto stockDto) throws StockNotFoundException;

    StockDto getById(String id) throws StockNotFoundException;
    List<Stock> getAllStocks();

    void deleteById(String id);

    List<StockDto> getByArticleAndDepot(String articleId , String depotId) throws ArticleNotFoundException,DepotNotFoundException;
}
