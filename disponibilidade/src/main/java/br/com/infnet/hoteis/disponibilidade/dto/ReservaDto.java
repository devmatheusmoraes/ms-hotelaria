package br.com.infnet.hoteis.reserva.dto;

import java.time.LocalDate;

public record ReservaDto(

        LocalDate dataInicio,
        LocalDate dataFim,
        Integer numeroAdultos,
        Integer numeroCriancas,
        Integer numeroComodos,
        Long usuarioId

) {
}
