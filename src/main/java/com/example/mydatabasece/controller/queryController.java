package com.example.mydatabasece.controller;

import com.fazecast.jSerialComm.SerialPort;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.w3c.dom.*;
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
    ResponseEntity<String> queryPost(@RequestBody queryRequest request) throws ParserConfigurationException, IOException, SAXException, TransformerException {

        System.out.println(request.getQuery());

        String[] palabras = request.getQuery().split(" ");
        if (Objects.equals(palabras[0], "DELETE")){
            if (Objects.equals(palabras[1], "FROM")){

                File xml = new File("C:\\Users\\manue\\Documents\\Proyecto3-DatosII\\src\\main\\java\\com\\example\\mydatabasece\\" + user + "\\" + palabras[2] + ".xml");
                if (xml.exists()) {
                    if (xml.delete()) {
                        SerialReader.success = 3;
                        System.out.println("Se ha borrado la tabla");
                    }
                }
                else {
                    System.out.println("La tabla no existe");
                }
            }
        }
        else if (Objects.equals(palabras[0], "SELECT")){
            System.out.println("Seleccionado");
        }
        else if (Objects.equals(palabras[0], "INSERT") && Objects.equals(palabras[1], "INTO")){
            File xml = new File("C:\\Users\\manue\\Documents\\Proyecto3-DatosII\\src\\main\\java\\com\\example\\mydatabasece\\" + user + "\\" + palabras[2] + ".xml");
            if (xml.exists()) {
                DocumentBuilderFactory factory = DocumentBuilderFactory.newInstance();
                DocumentBuilder builder = factory.newDocumentBuilder();

                Document document = builder.parse(xml);
                NodeList nodo_principal = document.getElementsByTagName(palabras[2]);


                for (int i = 0; i < nodo_principal.getLength(); i++) {
                    Node nodo = nodo_principal.item(i);
                    if (nodo.getNodeType() == Node.ELEMENT_NODE) {
                        Element element = (Element) nodo;
                        NodeList hijos = element.getChildNodes();
                        for (int j = 0; j < hijos.getLength(); j++) {
                            Node hijo = hijos.item(j);
                            if (hijo.getNodeType() == Node.ELEMENT_NODE) {
                                Element eHijo = (Element) hijo;
                                if (hijo.getNodeName().equals(palabras[3])) {
                                    Text valor = document.createTextNode(palabras[5]);
                                    eHijo.appendChild(valor);
                                    TransformerFactory transformerFactory1 = TransformerFactory.newInstance();
                                    Transformer transformer = transformerFactory1.newTransformer();
                                    Source source = new DOMSource(document);
                                    FileWriter fw = new FileWriter(xml);
                                    PrintWriter pw = new PrintWriter(fw);
                                    Result result = new StreamResult(pw);
                                    transformer.transform(source,result);
                                }
                            }
                        }
                    }
                }
            }
        }
        else {
            System.out.println("Comando no encontrado");
        }

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

        String[] attributes = request.getNewTableColumns().split(",");

        System.out.println(user);

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

        TransformerFactory transformerFactory2 = TransformerFactory.newInstance();
        Transformer transformer = transformerFactory2.newTransformer();

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
