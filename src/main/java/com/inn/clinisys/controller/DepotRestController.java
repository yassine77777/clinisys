package com.inn.clinisys.controller;

import com.inn.clinisys.dto.DepotDto;
import com.inn.clinisys.entity.Depot;
import com.inn.clinisys.exception.DepotNotFoundException;
import com.inn.clinisys.service.DepotService;
import org.jetbrains.annotations.NotNull;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/depots")

public class DepotRestController {
    @Autowired
    DepotService depotService;
    @PostMapping("/save")
    public DepotDto save(@RequestBody DepotDto depotDto) {
        return depotService.save(depotDto);
    }
    @PutMapping("/update/{id}")

    public DepotDto update(@PathVariable String id, @RequestBody  DepotDto depotDto) throws DepotNotFoundException {
        return depotService.update(id, depotDto);
    }
    @GetMapping("/get/{id}")
    public DepotDto getById(@PathVariable String id) throws DepotNotFoundException {
        return depotService.getById(id);
    }

    @GetMapping("/all")

    public List<Depot> getAllDepots(){
        List<Depot> depots = depotService.getAllDepots();
        return depots.stream().toList();
    }

    @DeleteMapping("/delete/{id}")
    public void deleteById(@PathVariable String id){
        depotService.deleteById(id);
    }

    @ExceptionHandler(Exception.class)
    public ResponseEntity<String> exceptionHandler(@NotNull Exception exception){
        return new ResponseEntity<>(exception.getMessage(), HttpStatus.INTERNAL_SERVER_ERROR);
    }


}
