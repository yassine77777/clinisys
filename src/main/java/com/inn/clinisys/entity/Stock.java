package com.inn.clinisys.entity;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@NoArgsConstructor
@AllArgsConstructor
@Data
@Builder
@Table(name = "stocks")

public class Stock {
    @Id
    private String id;

    @Column(unique = true , nullable = false)
    private int qte;

    @Column(unique = true , nullable = false)
    private String datePeremption;

    @ManyToOne
    private Article article;

    @ManyToOne
    private Depot depot;
}

