package br.com.infnet.hoteis.disponibilidade.controller;

import br.com.infnet.hoteis.disponibilidade.dto.ReservaDto;
import br.com.infnet.hoteis.disponibilidade.model.Hotel;
import br.com.infnet.hoteis.disponibilidade.model.Reserva;
import br.com.infnet.hoteis.disponibilidade.service.HotelService;
import br.com.infnet.hoteis.disponibilidade.service.ReservaService;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("")
@AllArgsConstructor
@Slf4j
public class DisponibilidadeController {

    private final HotelService hotelService;
    private final ReservaService reservaService;

    @GetMapping
    public List<Hotel> check(@RequestBody ReservaDto dto){
        log.info("Check Disponibilidade");
        return hotelService.findHotelsByDto(dto);
    }

    @PostMapping
    public void confirm(@RequestBody Reserva reserva){
        log.info("Reserva Salva");
        reservaService.save(reserva);
    }

}
