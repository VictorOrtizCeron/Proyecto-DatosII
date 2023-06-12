package com.example.mydatabasece.controller;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.io.File;
import java.util.ArrayList;
import java.util.List;


@RestController
@RequestMapping("/api/database/sidebar")
public class sidebarController {
    public static String user;
    @GetMapping("/getInfo")
    ResponseEntity<sidebarInfo> getSidebarInfo(){

        sidebarInfo info = new sidebarInfo();

        info.xmlStores  = new ArrayList<>();

        String folderPath = "C:\\Users\\victo\\IdeaProjects\\MyDataBaseCE\\src\\main" +
                "\\java\\com\\example\\mydatabasece\\"+ user; // Replace with the path of the folder you want to traverse
        File folder = new File(folderPath);
        File[] files = folder.listFiles();

        assert files != null;

        for (File file : files) {
            System.out.println(file.getName());
            info.xmlStores.add(file.getName());
        }
        return new ResponseEntity<sidebarInfo> (info, HttpStatus.OK);
    }
    public static class sidebarInfo {
        private List<String> xmlStores;
        public List<String> getXmlStores() {
            return xmlStores;
        }


    }
}
