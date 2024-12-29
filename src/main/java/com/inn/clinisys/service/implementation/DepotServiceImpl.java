package com.inn.clinisys.service.implementation;
import com.inn.clinisys.dto.DepotDto;
import com.inn.clinisys.entity.Article;
import com.inn.clinisys.entity.Depot;
import com.inn.clinisys.exception.ArticleNotFoundException;
import com.inn.clinisys.exception.DepotNotFoundException;
import com.inn.clinisys.repository.DepotRepository;
import com.inn.clinisys.service.DepotService;
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
public class DepotServiceImpl implements DepotService {
    @Autowired
    DepotRepository depotRepository;
    @Autowired
    Mappers mapper;

    @Override
    public DepotDto save(DepotDto depotDto) {
        Depot depot = mapper.fromDepotDto(depotDto);
        Depot depotSaved = depotRepository.save(depot);
        log.info("depot created successfully");
        return mapper.fromDepot(depotSaved);
    }

    @Override
    public DepotDto update(String id, DepotDto depotDto) throws DepotNotFoundException {
        Depot depot = depotRepository.findById(id)
                .orElseThrow( ()-> new DepotNotFoundException("depot you try to update with id = '"+id+"' not found."));
        updateArticleFields(depot, depotDto);
        Depot depotUpdate = depotRepository.save(depot);
        log.info("Depot updated successfully");
        return mapper.fromDepot(depotUpdate);
    }

    @Override
    public void deleteById(String id) {
        depotRepository.deleteById(id);
        log.info("depot deleted successfully");

    }

    @Override
    public DepotDto getById(String id) throws DepotNotFoundException {
        Depot depot = depotRepository.findById(id)
                .orElseThrow( () -> new DepotNotFoundException("Depot not found with id '"+id+"'."));
        log.info("depot found with id '"+id+"' found successfully.");
        return mapper.fromDepot(depot);
    }

    @Override
    public List<Depot> getAllDepots() {
       return depotRepository.findAll();
    }
    private void updateArticleFields(@NotNull Depot depot, @NotNull DepotDto depotDto){
        depot.setNomDepot(depotDto.nomDepot());


    }
}
