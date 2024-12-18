package dev.guilhermeluan.dtos;

import com.fasterxml.jackson.annotation.JsonFormat;

import java.util.Date;

public record TaskGetResponse(
        Long id,
        String taskName,
        Double cost,
        @JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "yyyy-MM-dd")
        Date dataLimit
) {
}
