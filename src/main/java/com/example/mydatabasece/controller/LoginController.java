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

    boolean value = false;
    public void setValue(boolean value){
        this.value = value;
    }

    @PostMapping("/login")
    public ResponseEntity<String> helloPost(@RequestBody TextRequest  request){

        XML xml_Reader = new XML();
        xml_Reader.setPassword(request.password);
        xml_Reader.setUsername(request.username);
        xml_Reader.XML_Reader();

        System.out.println(value);
        if (value){
            return ResponseEntity.ok("Funciono");
        }
        else {
            return ResponseEntity.status(HttpStatus.UNAUTHORIZED).body("Invalid username or password");
        }

    }
    public void nose(){
        value = true;
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
