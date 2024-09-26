package com.api.othon.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import com.api.othon.controller.email.EmailService;
import com.api.othon.model.MailConfig;

@RestController
@RequestMapping("/api/config")
public class ConfigController {

    @Autowired
    private MailConfig mailConfig;

    @Autowired
    private EmailService emailService; // Adicionar o serviço de e-mail

    @PostMapping("/email")
    public ResponseEntity<String> updateEmailConfig(@RequestParam String username, 
                                                    @RequestParam String password) {
        mailConfig.setUsername(username);
        mailConfig.setPassword(password);
        mailConfig.setAuth(true);
        mailConfig.setStarttls(true);

        emailService.updateMailSender(); // Atualizar o sender com as novas credenciais

        return ResponseEntity.ok("Configurações de email atualizadas com sucesso!");
    }
    
    @GetMapping("/email")
    public MailConfig getEmailConfig() {
        return mailConfig;
    }
}
