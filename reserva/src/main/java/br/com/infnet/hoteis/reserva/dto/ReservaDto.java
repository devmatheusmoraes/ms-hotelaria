package br.com.infnet.hoteis.reserva.dto;

import jakarta.validation.constraints.Future;
import jakarta.validation.constraints.FutureOrPresent;
import jakarta.validation.constraints.NotBlank;

import java.time.LocalDate;

public record ReservaDto(

        @NotBlank
        @FutureOrPresent
        LocalDate dataInicio,

        @NotBlank
        @Future
        LocalDate dataFim,
        Integer numeroAdultos,
        Integer numeroCriancas,
        Integer numeroComodos

) {
}
