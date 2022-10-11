package ru.netology;

import java.io.*;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.ArrayList;

import org.json.simple.parser.JSONParser;
import org.json.simple.parser.ParseException;

public class Server {
    private static final int PORT = 8989;

    public static void main(String[] arg) {
        Manager manager = new Manager(new ArrayList<>());
        //десериализация
        File file = new File("data.bin");
        try {
            manager = WorkWithFile.loadFromBinFile(file);
        } catch (IOException | ClassNotFoundException e) {
            e.printStackTrace();
        }

        try (ServerSocket serverSocket = new ServerSocket(PORT)) {
            while (true) {
                try (Socket clientSocket = serverSocket.accept();
                     PrintWriter out = new PrintWriter(clientSocket.getOutputStream(), true);
                     BufferedReader in = new BufferedReader(new InputStreamReader(clientSocket.getInputStream()))
                ) {
                    System.out.println("New connection accepted");

                    JSONParser parser = new JSONParser();
                    Object obj;
                    try {
                        obj = parser.parse(in.readLine());
                        manager.addBuy(obj);
                    } catch (ParseException e) {
                        throw new RuntimeException(e);
                    }
                    out.println(Logic.generateOut(manager));
                    // сериализация
                    WorkWithFile.saveBin(manager);
                }
            }
        } catch (IOException e) {
            System.out.println("Не могу стартовать сервер");
            e.printStackTrace();
        }
    }
}
