package com.api.othon.controller;


import jakarta.servlet.http.HttpServletRequest;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.api.othon.model.repository.UserRepository;
import com.api.othon.model.user.AuthenticationDTO;
import com.api.othon.model.user.LoginResponseDTO;
import com.api.othon.model.user.RegisterDTO;
import com.api.othon.model.user.User;
import com.api.othon.security.TokenService;
import com.api.othon.services.LogAuditoriaService;

@RestController
@RequestMapping("auth")
public class AuthenticationController {
    @Autowired
    private AuthenticationManager authenticationManager;
    @Autowired
    private UserRepository repository;
    @Autowired
    private TokenService tokenService;


    private final LogAuditoriaService logAuditoriaService;

    @Autowired
    public AuthenticationController(LogAuditoriaService logAuditoriaService) {
        this.logAuditoriaService = logAuditoriaService;
    }



    @PostMapping("/login")
    public ResponseEntity login(@RequestBody @Valid AuthenticationDTO data,  HttpServletRequest httpRequest){
        System.out.println(data);
        String enderecoIp = httpRequest.getRemoteAddr(); 

        System.out.println(enderecoIp);

        logAuditoriaService.registrarLog(1L, enderecoIp, enderecoIp, enderecoIp);
        var usernamePassword = new UsernamePasswordAuthenticationToken(data.login(), data.password());
        var auth = this.authenticationManager.authenticate(usernamePassword);

        var token = tokenService.generateToken((User) auth.getPrincipal());
        

        return ResponseEntity.ok(new LoginResponseDTO(token));
    }

    @PostMapping("/register")
    public ResponseEntity register(@RequestBody @Valid RegisterDTO data){
        if(this.repository.findByLogin(data.login()) != null) return ResponseEntity.badRequest().build();

        String encryptedPassword = new BCryptPasswordEncoder().encode(data.password());
        User newUser = new User(data.login(), encryptedPassword, data.role());

        this.repository.save(newUser);

        return ResponseEntity.ok().build();
    }
}
