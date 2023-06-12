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
import java.util.*;

//controlador de queries , aqui llegan las solicitudes de la página principal mediante el método post
@RestController
@RequestMapping("/api/database")
public class queryController {
    public static String user;
    String committ = "";
    String[] commit;
    @PostMapping("/table")
    ResponseEntity<TableResponse> queryPost(@RequestBody queryRequest request) throws ParserConfigurationException, IOException, SAXException, TransformerException {


        committ = committ + "," + request.getQuery();
        commit = committ.split(",");
        System.out.println(commit[1] + "," + commit[2]);
        List<String> headers = new ArrayList<>();
        List<List<String>> items = new ArrayList<>();
        TableResponse tableJSON = new TableResponse(headers,items);
        String[] palabras = request.getQuery().split(" ");

        if (Objects.equals(palabras[0], "SELECT")){
            String dir = "C:\\Users\\manue\\Documents\\Proyecto3-DatosII\\src\\main\\java\\com\\example\\mydatabasece\\"
                    + user + "\\" + palabras[1] + ".xml";

            tableJSON = read(dir);

        }
        return new ResponseEntity<TableResponse>(tableJSON, HttpStatus.OK);
    }
    //request de query básico
    public static class queryRequest {
        private String query;

        public String getQuery() {
            return query;
        }


    }
    @PostMapping("/commit")
    ResponseEntity<String> commitPost(@RequestBody CommitRequest request) throws IOException, SAXException, ParserConfigurationException, TransformerException {
        for (int l = 1;l < commit.length;l++) {
            String[] palabras = commit[l].split(" ");
            if (Objects.equals(palabras[0], "DELETE")) {
                if (Objects.equals(palabras[1], "FROM")) {
                    if (palabras.length >= 4) {
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
                                            String[] todo = palabras[4].split(",");
                                            if (todo.length == 1) {
                                                if (hijo.getNodeName().equals(todo[0])) {
                                                    nodo.removeChild(hijo);
                                                    TransformerFactory transformerFactory1 = TransformerFactory.newInstance();
                                                    Transformer transformer = transformerFactory1.newTransformer();
                                                    Source source = new DOMSource(document);
                                                    FileWriter fw = new FileWriter(xml);
                                                    PrintWriter pw = new PrintWriter(fw);
                                                    Result result = new StreamResult(pw);
                                                    transformer.transform(source, result);
                                                    SerialReader.success = 3;
                                                }
                                            } else {
                                                for (int k = 0; k < todo.length; k++) {
                                                    if (hijo.getNodeName().equals(todo[k])) {
                                                        nodo.removeChild(hijo);
                                                        TransformerFactory transformerFactory1 = TransformerFactory.newInstance();
                                                        Transformer transformer = transformerFactory1.newTransformer();
                                                        Source source = new DOMSource(document);
                                                        FileWriter fw = new FileWriter(xml);
                                                        PrintWriter pw = new PrintWriter(fw);
                                                        Result result = new StreamResult(pw);
                                                        transformer.transform(source, result);
                                                        SerialReader.success = 3;
                                                    }
                                                }
                                            }
                                        }
                                    }
                                }
                            }
                        }
                    } else {
                        File xml = new File("C:\\Users\\manue\\Documents\\Proyecto3-DatosII\\src\\main\\java\\com\\example\\mydatabasece\\" + user + "\\" + palabras[2] + ".xml");
                        if (xml.exists()) {
                            if (xml.delete()) {
                                SerialReader.success = 3;
                                System.out.println("Se ha borrado la tabla");
                            }
                        } else {
                            System.out.println("La tabla no existe");
                        }
                    }
                }
            } else if (Objects.equals(palabras[0], "INSERT") && Objects.equals(palabras[1], "INTO")) {
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
                                    String[] atributos = palabras[3].split(",");
                                    String[] escritura = palabras[5].split(",");
                                    for (int k = 0; k < atributos.length; k++) {
                                        if (hijo.getNodeName().equals(atributos[k])) {
                                            Text valor = document.createTextNode(escritura[k]);
                                            eHijo.appendChild(valor);
                                            TransformerFactory transformerFactory1 = TransformerFactory.newInstance();
                                            Transformer transformer = transformerFactory1.newTransformer();
                                            Source source = new DOMSource(document);
                                            FileWriter fw = new FileWriter(xml);
                                            PrintWriter pw = new PrintWriter(fw);
                                            Result result = new StreamResult(pw);
                                            transformer.transform(source, result);
                                        }
                                    }
                                }
                            }
                        }
                    }
                }
            } else if (Objects.equals(palabras[0], "UPDATE")) {
                File xml = new File("C:\\Users\\manue\\Documents\\Proyecto3-DatosII\\src\\main\\java\\com\\example\\mydatabasece\\" + user + "\\" + palabras[1] + ".xml");
                if (xml.exists()) {
                    DocumentBuilderFactory factory = DocumentBuilderFactory.newInstance();
                    DocumentBuilder builder = factory.newDocumentBuilder();

                    Document document = builder.parse(xml);
                    NodeList nodo_principal = document.getElementsByTagName(palabras[1]);
                    String[] atributos = palabras[3].split(",");

                    for (int i = 0; i < nodo_principal.getLength(); i++) {
                        Node nodo = nodo_principal.item(i);
                        if (nodo.getNodeType() == Node.ELEMENT_NODE) {
                            Element element = (Element) nodo;
                            NodeList hijos = element.getChildNodes();
                            for (int j = 0; j < hijos.getLength(); j++) {
                                Node hijo = hijos.item(j);
                                if (hijo.getNodeType() == Node.ELEMENT_NODE) {
                                    Element eHijo = (Element) hijo;
                                    for (int k = 0; k < atributos.length; k++) {
                                        String[] cambio = atributos[k].split("=");
                                        if (cambio[0].equals(hijo.getNodeName())) {
                                            System.out.println(cambio[0] + "," + cambio[1]);
                                            eHijo.setTextContent(cambio[1]);
                                            TransformerFactory transformerFactory1 = TransformerFactory.newInstance();
                                            Transformer transformer = transformerFactory1.newTransformer();
                                            Source source = new DOMSource(document);
                                            FileWriter fw = new FileWriter(xml);
                                            PrintWriter pw = new PrintWriter(fw);
                                            Result result = new StreamResult(pw);
                                            transformer.transform(source, result);
                                        }
                                    }
                                }
                            }
                        }
                    }
                }
            } else {
                System.out.println("Comando no encontrado");
            }
        }
        return ResponseEntity.ok("Funciono");
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
    public TableResponse read(String xmlFilePath){
        Map<String, List<String>> nodoValorMap = new HashMap<>();

        //List<String> columns = new ArrayList<>();
        List<List<String>>data = new ArrayList<>();
        List<String>columns = new ArrayList<>();
        try {
            DocumentBuilderFactory factory = DocumentBuilderFactory.newInstance();
            DocumentBuilder builder = factory.newDocumentBuilder();
            Document document = builder.parse(xmlFilePath);

            NodeList nodeList = document.getDocumentElement().getChildNodes();

            for (int i = 0; i < nodeList.getLength(); i++) {
                Node node = nodeList.item(i);
                List<String> tempData = new ArrayList<>();
                if (node.getNodeType() == Node.ELEMENT_NODE) {
                    Element element = (Element) node;
                    String nombreNodo = element.getNodeName();

                    if (!nodoValorMap.containsKey(nombreNodo)) {
                        nodoValorMap.put(nombreNodo, new ArrayList<>());

                    }

                    String informacionNodo = element.getTextContent();
                    nodoValorMap.get(nombreNodo).add(informacionNodo);

                }

            }
            columns = new ArrayList<>(nodoValorMap.keySet());
            Collections.reverse(columns);
            // Imprimir la información de los nodos
            for (Map.Entry<String, List<String>> entry : nodoValorMap.entrySet()) {
                String nombreNodo = entry.getKey();
                List<String> valoresNodo = entry.getValue();

                data.add(valoresNodo);

                System.out.println("Información en " + nombreNodo + ":");
                for (String valor : valoresNodo) {
                    System.out.println(valor);

                }

            }
            Collections.reverse(data);

        } catch (Exception e) {
            e.printStackTrace();
        }
        return new TableResponse(columns,data);
    }
    public ResponseEntity<TableResponse>sendTable(TableResponse tabla){
        return new ResponseEntity<>(tabla,HttpStatus.OK);
    }
    public static class TableResponse {
        private List<String> headers;
        private List<List<String>> items;

        public TableResponse(List<String> headers, List<List<String>> items) {
            this.headers = headers;
            this.items = items;
        }

        public List<String> getHeaders() {
            return headers;
        }

        public List<List<String>> getItems() {
            return items;
        }
    }
    public static class CommitRequest {
        private String commit;

        public String getCommit() {
            return commit;
        }

    }
}
