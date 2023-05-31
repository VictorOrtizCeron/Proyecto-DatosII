package com.example.mydatabasece;

import com.example.mydatabasece.controller.SerialReader;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
public class MyDataBaseCeApplication {

    public static void main(String[] args) {
        SpringApplication.run(MyDataBaseCeApplication.class, args);
        SerialReader reader = new SerialReader();
        reader.start();
    }

}
