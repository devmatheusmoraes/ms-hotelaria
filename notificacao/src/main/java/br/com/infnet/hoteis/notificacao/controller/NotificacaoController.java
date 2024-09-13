package br.com.infnet.hoteis.notificacao.controller;

import br.com.infnet.hoteis.notificacao.dto.Reserva;
import br.com.infnet.hoteis.notificacao.service.EmailService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@Tag(name = "Notificação", description = " - Operações para notificar os clientes das reservas")
@RestController
@RequestMapping("/")
public class NotificacaoController {

    @Autowired
    private EmailService emailService;

    @Operation(summary = "Notificar por e-mail")
    @PostMapping("/send")
    public void notificar(@RequestBody Reserva reserva) {
        emailService.sendEmail(reserva);
    }
}
