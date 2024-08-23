package br.com.infnet.hoteis.reserva.service;

import br.com.infnet.hoteis.reserva.dto.ReservaDto;
import br.com.infnet.hoteis.reserva.model.Hotel;
import br.com.infnet.hoteis.reserva.model.Reserva;
import br.com.infnet.hoteis.reserva.service.clients.DisponibilidadeClient;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class DisponibilidadeService {
    private final DisponibilidadeClient client;
    public List<Hotel> check(ReservaDto reserva){
        return client.check(reserva);
    }

    public void confirm(Reserva reserva){
        client.confirm(reserva);
    }
}
