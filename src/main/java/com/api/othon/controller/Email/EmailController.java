package com.api.othon.controller.Email;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
@RestController
@RequestMapping("/email")
public class EmailController {
    @Autowired
    private EmailService emailService;

    @PostMapping
    public ResponseEntity<String> enviarEmail(@RequestParam("destinatario") String destinatario,
                                               @RequestParam("assunto") String assunto,
                                               @RequestParam("corpo") String corpo) {
        emailService.enviarEmail(destinatario, assunto, corpo);
        return ResponseEntity.ok("Email enviado com sucesso!");
    }
}
