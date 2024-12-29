package com.inn.clinisys.repository;

import com.inn.clinisys.entity.Article;
import com.inn.clinisys.entity.Depot;
import org.springframework.data.jpa.repository.JpaRepository;

public interface DepotRepository extends JpaRepository<Depot, String> {
}
