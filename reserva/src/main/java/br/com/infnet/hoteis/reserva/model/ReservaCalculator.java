package br.com.infnet.hoteis.reserva.model;

import java.math.BigDecimal;
import java.math.RoundingMode;

public class ReservaCalculator {
    private static final BigDecimal PRECO_BASE_ADULTO = new BigDecimal("100.00");
    private static final BigDecimal PRECO_ADICIONAL_CRIANCA = new BigDecimal("50.00");
    private static final BigDecimal AUMENTO_CRIAANCA = new BigDecimal("0.20");

    public static BigDecimal calcularValorReserva(Reserva reserva) {

        BigDecimal precoBase = PRECO_BASE_ADULTO.multiply(BigDecimal.valueOf(reserva.getNumeroAdultos()))
                .add(PRECO_ADICIONAL_CRIANCA.multiply(BigDecimal.valueOf(reserva.getNumeroCriancas())));


        if (reserva.getNumeroCriancas() > 0) {
            BigDecimal acrescimo = precoBase.multiply(AUMENTO_CRIAANCA);
            precoBase = precoBase.add(acrescimo);
        }

        return precoBase.multiply(reserva.getHotel().getPercentHotel())
                .setScale(2, RoundingMode.HALF_UP);
    }

}
