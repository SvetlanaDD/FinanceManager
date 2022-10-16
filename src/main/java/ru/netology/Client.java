package ru.netology;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.net.Socket;

public class Client {
    private static final int PORT = 8989;
    private static final String HOST = "127.0.0.1";


    public static void main(String[] arg) {
        try (Socket clientSocket = new Socket(HOST, PORT);
             PrintWriter out = new PrintWriter(clientSocket.getOutputStream(), true); // поток выхода
             BufferedReader in = new BufferedReader(new InputStreamReader(clientSocket.getInputStream()))) // поток
        {
            // отправляем запрос
            Buy buy = new Buy("булка", "2022.10.12", 200L);
//            Buy buy = new Buy("сухарики", "2022.10.10", 50L);
//            Buy buy = new Buy("шапка", "2022.09.15", 500L);
//            Buy buy = new Buy("колбаса", "2022.10.10", 150L);
//            Buy buy = new Buy("курица", "2021.05.15", 300L);
//            Buy buy = new Buy("акции", "2022.01.11", 1000L);
//            Buy buy = new Buy("тапки", "2021.11.11", 600L);
//            Buy buy = new Buy("игрушка", "2021.10.12", 2000L);

            Gson gson = new GsonBuilder()
                    .create();
            String jsonBuy = gson.toJson(buy);
            out.println(jsonBuy);

            // получаем ответ
            String jsonIn = in.readLine();
            GsonBuilder builder = new GsonBuilder();
            Gson gsonIn = builder
                    .create();
            Statistic statistic = gsonIn.fromJson(jsonIn, Statistic.class);
            System.out.println(statistic.toString());
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }
}

