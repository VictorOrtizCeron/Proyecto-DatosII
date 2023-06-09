package com.example.mydatabasece.controller;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.w3c.dom.Document;
import org.w3c.dom.Element;
import org.xml.sax.SAXException;

import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.parsers.ParserConfigurationException;
import javax.xml.transform.*;
import javax.xml.transform.dom.DOMSource;
import javax.xml.transform.stream.StreamResult;
import java.io.*;
import java.util.Objects;

//controlador de queries , aqui llegan las solicitudes de la página principal mediante el método post
@RestController
@RequestMapping("/api/database")
public class queryController {
    public static String user;
    @PostMapping("/table")
    ResponseEntity<String> queryPost(@RequestBody queryRequest request) {

        System.out.println(request.getQuery());


        return ResponseEntity.ok("Query received in backend");

    }
    //request de query básico
    public static class queryRequest {
        private String query;

        public String getQuery() {
            return query;
        }


    }
    @PostMapping("/create")
    ResponseEntity<String> queryPost(@RequestBody newTableRequest request) throws IOException, ParserConfigurationException, SAXException, TransformerException {

        System.out.println(request.getNewTableColumns());

        String[] attributes = request.getNewTableColumns().split(",");

        String filePath = "C:\\Users\\manue\\Documents\\Proyecto3-DatosII\\src\\main\\java\\com\\example\\mydatabasece" + "\\" + user + "\\" + request.newTableName + ".xml";
        File fil = new File(filePath);
        if (fil.createNewFile()){
            System.out.println("se pudo hacer");
        }

        DocumentBuilderFactory factory = DocumentBuilderFactory.newInstance();
        DocumentBuilder builder = factory.newDocumentBuilder();
        Document doc = builder.newDocument();

        Element name = doc.createElement(request.newTableName);
        doc.appendChild(name);

        for (int i = 0; i < attributes.length; i++){
            Element attribute = doc.createElement(attributes[i]);
            name.appendChild(attribute);
        }

        TransformerFactory transformerFactory = TransformerFactory.newInstance();
        Transformer transformer = transformerFactory.newTransformer();

        Source source = new DOMSource(doc);
        FileWriter fw = new FileWriter(fil);
        PrintWriter pw = new PrintWriter(fw);
        Result result = new StreamResult(pw);

        transformer.transform(source,result);


        return ResponseEntity.ok("New Table creation request received in backend");

    }
    //request de creación de nueva base de datos
    public static class newTableRequest {
        private String newTableName;

        private String newTableColumns;

        public String getNewTableName() {
            return newTableName;
        }
        public String getNewTableColumns() {
            return newTableColumns;
        }


    }
}
