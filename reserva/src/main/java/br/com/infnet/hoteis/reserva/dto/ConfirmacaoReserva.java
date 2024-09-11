package br.com.infnet.hoteis.reserva.dto;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Positive;

public record ConfirmacaoReserva(

        ReservaDto reservaDto,

        @NotBlank
        @Positive
        Long idUsuario,
        @NotBlank
        @Positive
        Long idHotel

) {
}
