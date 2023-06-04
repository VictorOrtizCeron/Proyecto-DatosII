package com.example.mydatabasece.controller;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.Objects;
//controlador de queries , aqui llegan las solicitudes de la página principal mediante el método post
@RestController
@RequestMapping("/api/database")
public class queryController {
    @PostMapping("/table")
    ResponseEntity<String> queryPost(@RequestBody queryRequest request) {

        System.out.println(request.getQuery());


        return ResponseEntity.ok("Query received in backend");

    }

    public static class queryRequest {
        private String query;

        public String getQuery() {
            return query;
        }


    }
}
