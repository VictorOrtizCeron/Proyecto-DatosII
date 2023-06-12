package com.example.mydatabasece.controller;

import org.w3c.dom.Document;
import org.w3c.dom.Element;
import org.w3c.dom.NodeList;

import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import java.io.*;

public class XML {
    private String username;
    private String password;

    public boolean value;
    public void setUsername(String username){
        this.username = username;
    }
    public void setPassword(String password){
        this.password = password;
    }
    public static void writeToXML(String filePath, String newContent) {
        try {
            // Leer el contenido actual del archivo XML
            BufferedReader reader = new BufferedReader(new FileReader(filePath));
            StringBuilder sb = new StringBuilder();
            String line;

            while ((line = reader.readLine()) != null) {
                sb.append(line).append(System.lineSeparator());
            }

            String content = sb.toString();
            reader.close();

            // Encontrar la posición de cierre de la etiqueta </datos>
            int endTagIndex = content.lastIndexOf("</datos>");

            if (endTagIndex != -1) {
                // Insertar la nueva información justo antes del cierre de la etiqueta </datos>
                StringBuilder updatedContent = new StringBuilder(content);
                updatedContent.insert(endTagIndex, System.lineSeparator() + newContent);

                // Escribir el contenido actualizado en el archivo XML
                BufferedWriter writer = new BufferedWriter(new FileWriter(filePath));
                writer.write(updatedContent.toString());
                writer.close();

                System.out.println("Nueva información agregada al archivo XML.");
            } else {
                System.out.println("La etiqueta </datos> no se encuentra en el archivo XML.");
            }
        } catch (IOException e) {
            System.out.println("Error al leer o escribir el archivo XML: " + e.getMessage());
        }
    }
    public void XML_Reader(){
        //String dir = "C:\\Users\\manue\\Documents\\Proyecto3-DatosII\\src\\main\\java\\com\\example\\mydatabasece\\assets\\data.xml";
        String dir = "C:\\Users\\victo\\IdeaProjects\\MyDataBaseCE\\src\\main\\java\\com\\example\\mydatabasece\\assets\\data.xml";
        Huffman huf = new Huffman();
        try {
            File inputFile =
                    new File(
                            dir);

            // Crear una instancia del DocumentBuilder
            DocumentBuilderFactory dbFactory = DocumentBuilderFactory.newInstance();
            DocumentBuilder dBuilder = dbFactory.newDocumentBuilder();

            // Analizar el archivo XML
            Document doc = dBuilder.parse(inputFile);
            doc.getDocumentElement().normalize();

            // Obtener la lista de elementos "linea"
            NodeList nodeList = doc.getElementsByTagName("linea");

            // Recorrer los elementos "linea" y almacenar los datos en las listas
            for (int i = 0; i < nodeList.getLength(); i++) {
                Element linea = (Element) nodeList.item(i);
                String texto = linea.getTextContent();

                // Separar las líneas por ";"
                String[] partes = texto.split(";");
                if (partes.length == 2) {
                    huf.lista_users.add(partes[0].trim());
                    huf.lista_passwords.add(partes[1].trim());
                }
            }
            // Imprimir las listas
            //System.out.println(huf.lista_users);
            //System.out.println(huf.lista_passwords);
        } catch (Exception e) {
            e.printStackTrace();
        }

        huf.lenght = huf.lista_users.size();
        huf.setPassword(password);
        huf.setUsername(username);
        huf.check();
        this.value = huf.value;
    }
}
