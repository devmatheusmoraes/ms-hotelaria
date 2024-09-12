package br.com.infnet.hoteis.notificacao.controller;

import br.com.infnet.hoteis.notificacao.dto.Reserva;
import br.com.infnet.hoteis.notificacao.service.EmailService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/")
public class NotificacaoController {

    @Autowired
    private EmailService emailService;

    @PostMapping("/send")
    public void notificar(@RequestBody Reserva reserva) {
        emailService.sendEmail(reserva);
    }
}
