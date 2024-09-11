package br.com.infnet.hoteis.disponibilidade.controller;

import br.com.infnet.hoteis.disponibilidade.dto.ReservaDto;
import br.com.infnet.hoteis.disponibilidade.model.Hotel;
import br.com.infnet.hoteis.disponibilidade.model.Reserva;
import br.com.infnet.hoteis.disponibilidade.service.HotelService;
import br.com.infnet.hoteis.disponibilidade.service.ReservaService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/")
@AllArgsConstructor
@Slf4j
@Tag(name = "Disponibilidade", description = " - Operações relacionadas ao microsserviço de disponibilidade para reserva")
public class DisponibilidadeController {

    private final HotelService hotelService;
    private final ReservaService reservaService;

    @Operation(summary = "Listar hotéis disponíveis para reserva")
    @PostMapping("/check")
    public List<Hotel> check(@RequestBody ReservaDto dto){
        log.info("Check Disponibilidade");
        return hotelService.findHotelsByDto(dto);
    }

    @Operation(summary = "Validar hotel para confirmação da reserva")
    @GetMapping("/getById/{id}")
    public Optional<Hotel> validar(@PathVariable Long id){
        log.info("Find by id");
        return hotelService.findById(id);
    }

    @Operation(summary = "Confirmar reserva")
    @PostMapping("/confirm")
    public void confirm(@RequestBody Reserva reserva){
        log.info("Reserva Salva");
        reservaService.save(reserva);
    }

}
