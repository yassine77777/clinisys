package com.inn.clinisys.service;

import com.inn.clinisys.dto.ArticleDto;
import com.inn.clinisys.dto.DepotDto;
import com.inn.clinisys.entity.Article;
import com.inn.clinisys.entity.Depot;
import com.inn.clinisys.exception.ArticleNotFoundException;
import com.inn.clinisys.exception.DepotNotFoundException;

import java.util.List;

public interface DepotService {
    DepotDto save(DepotDto depotDto);
    DepotDto update(String id, DepotDto depotDto) throws DepotNotFoundException;
    void deleteById(String id);

    DepotDto getById(String id) throws DepotNotFoundException;

    List<Depot> getAllDepots();
}
