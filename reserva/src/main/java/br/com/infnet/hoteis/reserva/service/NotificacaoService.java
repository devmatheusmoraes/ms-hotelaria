package br.com.infnet.hoteis.reserva.service;

import br.com.infnet.hoteis.reserva.model.Reserva;
import br.com.infnet.hoteis.reserva.service.clients.NotificacaoClient;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class NotificacaoService {
    private final NotificacaoClient client;
    public void notificar(Reserva reserva){
        client.notificar(reserva);
    }
}
