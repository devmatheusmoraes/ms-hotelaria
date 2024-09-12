package br.com.infnet.hoteis.reserva.controller;

import br.com.infnet.hoteis.reserva.config.ApplicationCache;
import br.com.infnet.hoteis.reserva.dto.ConfirmacaoReserva;
import br.com.infnet.hoteis.reserva.dto.ReservaDto;
import br.com.infnet.hoteis.reserva.model.Hotel;
import br.com.infnet.hoteis.reserva.model.Reserva;
import br.com.infnet.hoteis.reserva.model.Usuario;
import br.com.infnet.hoteis.reserva.service.*;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.server.ResponseStatusException;

import java.util.List;
import java.util.Objects;
import java.util.Optional;

import static br.com.infnet.hoteis.reserva.model.ReservaCalculator.calcularValorReserva;

@RestController
@RequestMapping("/")
@AllArgsConstructor
@Slf4j
@Tag(name = "Reserva", description = " - Operações relacionadas ao microsserviço de reserva")
public class ReservaController {

    private final ReservaService reservaService;
    private final HotelService hotelService;
    private final DisponibilidadeService disponibilidadeService;
    private final NotificacaoService notificacaoService;
    private final UsuarioService usuarioService;
    private final ApplicationCache cache;

    @Operation(summary = "Listar todas as reservas")
    @GetMapping
    public ResponseEntity<?> findAll() {
        return ResponseEntity.ok(reservaService.findAll());
    }

    @Operation(summary = "Listar uma reserva específica por ID")
    @GetMapping("/{id}")
    public ResponseEntity<?> findById(@PathVariable Long id) {
        log.info("NAME: "+ cache.appName);
        log.info("Find reserva by id: {}", id);
        Optional<Reserva> byId = reservaService.findById(id);
        if (byId.isPresent()) {
            return ResponseEntity.ok(byId.get());
        }else{
            return ResponseEntity.notFound().build();
        }
    }

    @Operation(summary = "Buscar disponibilidade para reserva")
    @PostMapping("/check")
    public ResponseEntity<?> check(@RequestBody ReservaDto dto) throws Exception {
        log.info("Printando Reserva Feita");
        List<Hotel> quartosDisponiveis = disponibilidadeService.check(dto);
        if (quartosDisponiveis.isEmpty()) {
            return ResponseEntity.ok("Não há quartos disponíveis");
        }else{
            log.info("NAME: "+ cache.appName);
            log.info("Iniciando confirmação reserva");
            return ResponseEntity.ok(quartosDisponiveis);
        }
    }

    @Operation(summary = "Confirmar reserva")
    @PostMapping("/confirm")
    private ResponseEntity<?> confirm(@RequestBody ConfirmacaoReserva dto) {
        try{
            Usuario usuario = usuarioService.findById(dto.idUsuario()).orElseThrow(() -> {
                String erroMsg = "Usuário não encontrado com o ID: " + dto.idUsuario().toString();
                log.error(erroMsg);
                return new ResponseStatusException(HttpStatus.BAD_REQUEST, erroMsg);
            });
            Hotel hotel = disponibilidadeService.validar(dto.idHotel()).orElseThrow(() -> {
                String erro = "Hotel não encontrado, id: " + dto.idHotel().toString();
                log.error(erro);
                return new ResponseStatusException(HttpStatus.BAD_REQUEST, erro);
            });
            usuarioService.save(usuario);
            hotelService.save(hotel);
            Reserva reserva = new Reserva(dto.reservaDto(), hotel, usuario);
            reserva.setValor(calcularValorReserva(reserva));
            reservaService.save(reserva);
            disponibilidadeService.confirm(reserva);
            notificacaoService.notificar(reserva);
            return ResponseEntity.ok().build();
        } catch (ResponseStatusException e) {
            log.error("Erro ao confirmar reserva: ", e);
            return ResponseEntity.status(e.getStatusCode()).body(e.getReason());
        } catch (Exception e) {
            log.error("Erro inesperado ao confirmar reserva: ", e);
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body("Erro ao confirmar reserva");
        }
    }
}
