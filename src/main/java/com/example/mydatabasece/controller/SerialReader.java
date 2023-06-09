package com.example.mydatabasece.controller;

import com.fazecast.jSerialComm.SerialPort;

import java.io.IOException;
import java.util.Objects;
import java.util.Scanner;

public class SerialReader extends Thread{
    public static int success = 0;
    public static int notSuccess = 0;
    public static int delete = 0;

    @Override
    public void run() {
        SerialPort port = SerialPort.getCommPort("COM5");
        port.openPort();
        port.setComPortTimeouts(SerialPort.TIMEOUT_READ_SEMI_BLOCKING, 0,0);
        Scanner scanner = new Scanner(port.getInputStream());
        String line;
        while (scanner.hasNextLine()) {
            line = scanner.nextLine();
            String[] bytes = line.split(",");
            if (Objects.equals(bytes[0], "1")){
                LoginController.morse = LoginController.morse + "0";
            }
            else if (Objects.equals(bytes[1], "1")){
                LoginController.morse = LoginController.morse + "1";
            }
            try {
                port.getOutputStream().write(success);
                success = 0;
            } catch (IOException e) {
                throw new RuntimeException(e);
            }
            System.out.println(bytes[2]);
            System.out.println(LoginController.morse);
        }
    }
}
