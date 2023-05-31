package com.example.mydatabasece.controller;

import com.fazecast.jSerialComm.SerialPort;

import java.util.Objects;
import java.util.Scanner;

public class SerialReader extends Thread{
    @Override
    public void run() {
        SerialPort port = SerialPort.getCommPort("COM6");
        port.openPort();
        port.setComPortTimeouts(SerialPort.TIMEOUT_READ_SEMI_BLOCKING, 0,0);
        Scanner scanner = new Scanner(port.getInputStream());
        String line;
        while (scanner.hasNextLine()) {
            line = scanner.nextLine();
            String[] bytes = line.split(",");
            if (Objects.equals(bytes[0], "1")){
                System.out.println("-");
            }
            else if (Objects.equals(bytes[1], "1")){
                System.out.println(".");
            }
        }
    }
}
