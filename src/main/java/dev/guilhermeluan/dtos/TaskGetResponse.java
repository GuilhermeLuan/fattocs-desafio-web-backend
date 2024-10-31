package dev.guilhermeluan.dtos;

import java.util.Date;

public record TaskGetResponse(
        Long id,
        String taskName,
        Double cost,
        Date dataLimit
) {
}
