package com.zohoBank.zoho.Retail.banking.controllers;

import com.zohoBank.zoho.Retail.banking.DTOs.AuthenticationRequest;
import com.zohoBank.zoho.Retail.banking.DTOs.AuthenticationResponse;
import com.zohoBank.zoho.Retail.banking.DTOs.RegisterRequest;
import com.zohoBank.zoho.Retail.banking.DTOs.RegisterResponse;
import com.zohoBank.zoho.Retail.banking.Services.AuthenticationService;
import com.zohoBank.zoho.Retail.banking.repos.UserRepository;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.web.bind.annotation.*;

//
@RestController
@RequestMapping("/api/v1/auth")
@RequiredArgsConstructor
public class AuthenticationController {
    private final UserRepository userRepository;

    private final AuthenticationService service;

    @PostMapping("/register")
    public ResponseEntity<RegisterResponse> register(@RequestBody @Valid RegisterRequest request)
    {
        return ResponseEntity.ok(service.register(request));
    }

    @PostMapping("/authenticate")
    public ResponseEntity<AuthenticationResponse> register(@RequestBody AuthenticationRequest request)
    {
//        System.out.println("request = " + request);
        return ResponseEntity.ok(service.authenticate(request));
    }

    @PostMapping("/logout")
    public ResponseEntity<String> logout(){
        return  ResponseEntity.ok(service.logout());
    }



}
