package com.example.mydatabasece.controller;


import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/messages")
public class MessageController {
    @PostMapping("/login")
    public String helloPost(@RequestBody TextRequest  request){
        System.out.println(request.getUsername());
        System.out.println(request.getPassword());

        return "Hola "+ request.getUsername() + ", funcionó el post method lok";
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
