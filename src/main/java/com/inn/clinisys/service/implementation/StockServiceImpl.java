package com.inn.clinisys.service.implementation;

import com.inn.clinisys.dto.StockDto;
import com.inn.clinisys.entity.Article;
import com.inn.clinisys.entity.Depot;
import com.inn.clinisys.entity.Stock;
import com.inn.clinisys.exception.ArticleNotFoundException;
import com.inn.clinisys.exception.DepotNotFoundException;
import com.inn.clinisys.exception.StockNotFoundException;
import com.inn.clinisys.repository.ArticleRepository;
import com.inn.clinisys.repository.DepotRepository;
import com.inn.clinisys.repository.StockRepository;
import com.inn.clinisys.service.StockService;
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

public class StockServiceImpl implements StockService {
    @Autowired
    ArticleRepository articleRepository;
    @Autowired
    DepotRepository depotRepository;

    @Autowired
    StockRepository stockRepository;
    @Autowired
    Mappers mapper;
    @Override
    public StockDto save(StockDto stockDto) throws ArticleNotFoundException, DepotNotFoundException {
        Stock stock = mapper.fromStockDto(stockDto);
        Stock stockSaved = stockRepository.save(stock);
        log.info("stock created successfully");
        return mapper.fromStock(stockSaved);
    }

    @Override
    public StockDto update(String id, StockDto stockDto) throws StockNotFoundException {
        Stock stock = stockRepository.findById(id)
                .orElseThrow( ()-> new StockNotFoundException("stock you try to update with id = '"+id+"' not found."));
        updateStockFields(stock, stockDto);
        Stock stockUpdated = stockRepository.save(stock);
        log.info("Stock updated successfully");
        return mapper.fromStock(stockUpdated);
    }

    @Override
    public StockDto getById(String id) throws StockNotFoundException {
        Stock stock = stockRepository.findById(id)
                .orElseThrow( () -> new StockNotFoundException("Stock not found with id '"+id+"'."));
        log.info("Stock found with id '"+id+"' found successfully.");
        return mapper.fromStock(stock);
    }

    @Override
    public List<Stock> getAllStocks() {
        return stockRepository.findAll();
    }

    @Override
    public void deleteById(String id) {
        stockRepository.deleteById(id);
        log.info("stock deleted successfully");

    }

    @Override
    public List<StockDto> getByArticleAndDepot(String articleId , String depotId) throws ArticleNotFoundException , DepotNotFoundException {
        Article article = articleRepository.findById(articleId)
                .orElseThrow(() -> new ArticleNotFoundException("Article with id = '" + articleId + "' not found."));

        Depot depot = depotRepository.findById(depotId)
                .orElseThrow(() -> new DepotNotFoundException("Depot with id = '" + depotId + "' not found."));
        List<Stock> stockList = stockRepository.findByArticleAndDepot(article, depot);
        return mapper.fromStockList(stockList);

    }
    private void updateStockFields(@NotNull Stock stock, @NotNull StockDto stockDto) {
        stock.setQte(stockDto.qte());
        stock.setDatePeremption(stockDto.datePeremption());
    }


    }
