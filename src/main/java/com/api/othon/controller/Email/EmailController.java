package com.api.othon.controller.email;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
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
    @PostMapping("/send")
    public ResponseEntity<String> sendEmail(@RequestBody EmailRequest emailRequest) {
        emailService.enviarEmail(emailRequest.getTo(), emailRequest.getSubject(), emailRequest.getBody());
        return ResponseEntity.ok("Email enviado com sucesso!");
    }
}
