package br.com.infnet.hoteis.reserva.controller;

import br.com.infnet.hoteis.reserva.config.ApplicationCache;
import br.com.infnet.hoteis.reserva.dto.ReservaDto;
import br.com.infnet.hoteis.reserva.model.Hotel;
import br.com.infnet.hoteis.reserva.model.Reserva;
import br.com.infnet.hoteis.reserva.model.Usuario;
import br.com.infnet.hoteis.reserva.service.DisponibilidadeService;
import br.com.infnet.hoteis.reserva.service.NotificacaoService;
import br.com.infnet.hoteis.reserva.service.ReservaService;
import br.com.infnet.hoteis.reserva.service.UsuarioService;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Objects;
import java.util.Optional;

@RestController
@RequestMapping("/")
@AllArgsConstructor
@Slf4j
public class ReservaController {

    private final ReservaService reservaService;
    private final DisponibilidadeService disponibilidadeService;
    private final NotificacaoService notificacaoService;
    private final UsuarioService usuarioService;
    private final ApplicationCache cache;

    @GetMapping
    public ResponseEntity<?> findAll() {
        return ResponseEntity.ok(reservaService.findAll());
    }
    @GetMapping("/{id}")
    public ResponseEntity<?> findById(@PathVariable Long id) {
        log.info("NAME: "+ cache.appName);
        log.info("Find reserva by id: {}", id);
        Optional<Reserva> byId = reservaService.findById(id);
        if (byId.isPresent()) {
            return ResponseEntity.ok(byId);
        }else{
            return ResponseEntity.notFound().build();
        }
    }

    @PostMapping
    public ResponseEntity<?> check(@RequestBody ReservaDto dto) throws Exception {
        log.info("Printando Reserva Feita");
        /*List<Hotel> quartosDisponiveis = disponibilidadeService.check(dto);
        if (quartosDisponiveis.isEmpty()) {
            return ResponseEntity.ok("Não há quartos disponíveis");
        }else{
            log.info("NAME: "+ cache.appName);
            log.info("Iniciando confirmação reserva");
            confirm(dto, quartosDisponiveis.get(1));*/
            return ResponseEntity.ok("Sua reserva foi realizada");
        //}
    }

    private void confirm(ReservaDto dto, Hotel hotel) throws Exception {
        if(Objects.isNull(dto.usuarioId())){
            log.error("Confirmação da reserva sem usuário");
            throw new Exception("Usuário não informado");
        }
        Usuario usuario = usuarioService.findById(dto.usuarioId()).orElseThrow(() -> {
            String erroMsg = "Usuário não encontrado com o ID: " + dto.usuarioId();
            log.error(erroMsg);
            return new Exception(erroMsg);
        });
        Reserva reserva = new Reserva(dto, hotel, usuario);
        disponibilidadeService.confirm(reserva);
        notificacaoService.notificar(reserva);
    }
}
