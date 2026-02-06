package ceu.dam.ad.users.controller;

import ceu.dam.ad.users.security.JwtService;

import ceu.dam.ad.users.dto.request.LoginRequest;
import ceu.dam.ad.users.dto.response.JwtResponse;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/auth")
public class AuthController {

    @Autowired
    private AuthenticationManager authenticationManager;

    @Autowired
    private JwtService jwtService;

    @PostMapping("/login")
    public ResponseEntity<?> login(@RequestBody LoginRequest request) {

        // 👉 Spring valida usuario y contraseña
        authenticationManager.authenticate(
                new UsernamePasswordAuthenticationToken(
                        request.getUsername(),
                        request.getPassword()
                )
        );

        // 👉 Si todo va bien, generamos el JWT
        String token = jwtService.generateToken(request.getUsername());

        return ResponseEntity.ok(new JwtResponse(token));
    }
}
