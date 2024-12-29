package com.inn.clinisys.entity;

import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@Entity
@NoArgsConstructor
@AllArgsConstructor
@Data
@Builder
@Table(name = "articles")

public class Article {
    @Id
    private String id;

    @Column(unique = true , nullable = false)
    private String designation_article ;

    @OneToMany(mappedBy = "article", fetch = FetchType.LAZY)
    @JsonIgnore
    private List<Stock> stockList;



}
