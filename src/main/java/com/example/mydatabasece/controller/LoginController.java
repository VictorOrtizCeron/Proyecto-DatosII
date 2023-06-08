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
    //post mapping para manejar login
    @PostMapping("/login")
    public ResponseEntity<String> loginPost(@RequestBody TextRequest  request){

        XML xml_Reader = new XML();
        xml_Reader.setPassword(request.password);
        xml_Reader.setUsername(request.username);
        xml_Reader.XML_Reader();


        if (xml_Reader.value){

            return ResponseEntity.ok("Funciono");
        }
        else {
            return ResponseEntity.status(HttpStatus.UNAUTHORIZED).body("Invalid username or password");
        }

    }
    //Post mapping para registrar nuevo usuario :)
    @PostMapping("/register")
    public ResponseEntity<String> registerPost(@RequestBody TextRequest  request){
        //métodos de escritura de usuario en xml
        String dirXML = "C:\\Users\\victo\\IdeaProjects\\MyDataBaseCE\\src\\" +
                "main\\java\\com\\example\\mydatabasece\\assets\\data.xml";

        Huffman huf = new Huffman();

        huf.translate_new_password(request.password);


        String newAccount = "<linea>"+request.username+ ";" + huf.data_translated+ "</linea>";

        XML.writeToXML(dirXML,newAccount);



        return ResponseEntity.ok("Funciono");
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
