package com.example.mydatabasece.controller;


import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.Objects;

@RestController
@RequestMapping("/api/messages")

public class LoginController {
    @PostMapping("/login")
    public ResponseEntity<String> helloPost(@RequestBody TextRequest  request){
        System.out.println(request.getUsername());
        System.out.println(request.getPassword());





        if (Objects.equals(request.getPassword(), "Ranita15")){
            return ResponseEntity.ok("Password is correct");
        }
        else {
            return ResponseEntity.status(HttpStatus.UNAUTHORIZED).body("Invalid username or password");
        }

    }
    public static class TextRequest {
        private String username;

        private String password;

        public String getUsername() {
            return username;
        }

        public String getPassword() {
            return password;
        }
    }
}
