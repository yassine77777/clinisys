package com.inn.clinisys.controller;
import com.inn.clinisys.dto.StockDto;
import com.inn.clinisys.entity.Stock;
import com.inn.clinisys.exception.ArticleNotFoundException;
import com.inn.clinisys.exception.DepotNotFoundException;
import com.inn.clinisys.exception.StockNotFoundException;
import com.inn.clinisys.service.StockService;
import org.jetbrains.annotations.NotNull;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/stocks")
public class StockRestController {
    @Autowired
    StockService stockService;

    @PostMapping("/save")
    public StockDto save(@RequestBody StockDto stockDto) throws ArticleNotFoundException,DepotNotFoundException {
        return stockService.save(stockDto);
    }
    @PutMapping("/update/{id}")
    public StockDto update(@PathVariable String id, @RequestBody  StockDto stockDto) throws StockNotFoundException {
        return stockService.update(id, stockDto);
    }
    @GetMapping("/getByArticleAndDepot/{articleId}/{depotId}")

    public List<StockDto> getByArticleAndDepot(@PathVariable String articleId , @PathVariable String depotId ) throws ArticleNotFoundException, DepotNotFoundException {
        return stockService.getByArticleAndDepot(articleId, depotId);
    }
    @GetMapping("/get/{id}")
    public StockDto getById(@PathVariable String id) throws StockNotFoundException {
        return stockService.getById(id);
    }

    @GetMapping("/all")

    public List<Stock> getAllStocks(){
        List<Stock> stocks = stockService.getAllStocks();
        return stocks.stream().toList();
    }

    @DeleteMapping("/delete/{id}")
    public void deleteById(@PathVariable String id){
        stockService.deleteById(id);
    }

    @ExceptionHandler(Exception.class)
    public ResponseEntity<String> exceptionHandler(@NotNull Exception exception){
        return new ResponseEntity<>(exception.getMessage(), HttpStatus.INTERNAL_SERVER_ERROR);
    }

}
