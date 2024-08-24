package br.com.infnet.hoteis.reserva.service.clients;

import br.com.infnet.hoteis.reserva.dto.ReservaDto;
import br.com.infnet.hoteis.reserva.model.Hotel;
import br.com.infnet.hoteis.reserva.model.Reserva;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;

import java.util.List;

@FeignClient("DISPONIBILIDADE-SERVICE")
public interface DisponibilidadeClient {
    @GetMapping
    List<Hotel> check(@RequestBody ReservaDto reserva);
    @PostMapping
    void confirm(Reserva reserva);
}
