package br.com.infnet.hoteis.reserva.service;

import br.com.infnet.hoteis.reserva.dto.ReservaDto;
import br.com.infnet.hoteis.reserva.model.Hotel;
import br.com.infnet.hoteis.reserva.model.Reserva;
import br.com.infnet.hoteis.reserva.service.clients.DisponibilidadeClient;
import io.github.resilience4j.circuitbreaker.annotation.CircuitBreaker;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.Optional;
import java.util.concurrent.ConcurrentHashMap;

@Service
@Slf4j
@RequiredArgsConstructor
public class DisponibilidadeService {

    private static Map<Long, List<Hotel>> CACHE = new ConcurrentHashMap<>();
    private Long id = 1L;
    private final DisponibilidadeClient client;

    @CircuitBreaker(name = "disponibilidadeService", fallbackMethod = "buscarNoCache")
    public List<Hotel> check(ReservaDto reserva){
        List<Hotel> hoteis = client.check(reserva);

        if (!hoteis.isEmpty()) {
            CACHE.put(id, hoteis);
            this.id++;
        }

        return hoteis;
    }

    public List<Hotel> buscarNoCache(Throwable e) {
        log.info("Buscando no cache usando id {}", this.id - 1);
        return CACHE.getOrDefault(this.id - 1, new ArrayList<>());
    }

    public void confirm(Reserva reserva){
        client.confirm(reserva);
    }

    public Optional<Hotel> validar(Long idHotel){
        return client.validar(idHotel);
    }
}
