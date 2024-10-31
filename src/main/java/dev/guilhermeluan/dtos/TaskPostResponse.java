package dev.guilhermeluan.dtos;

import java.util.Date;

public record TaskPostResponse(
        Long id,
        String taskName,
        Double cost,
        Date dataLimit,
        Integer presentationOrder
) {
}
