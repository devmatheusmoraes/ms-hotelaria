package br.com.infnet.hoteis.reserva.service.clients;

import br.com.infnet.hoteis.reserva.model.Reserva;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.PostMapping;

@FeignClient("NOTIFICACAO-SERVICE")
public interface NotificacaoClient {
    @PostMapping
    void notificar(Reserva reserva);
}
