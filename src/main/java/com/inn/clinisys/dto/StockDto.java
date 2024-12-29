package com.inn.clinisys.dto;

public record StockDto(
        String id,
        int qte ,
        String datePeremption,
        String articleId,
        String depotId
) {
}
