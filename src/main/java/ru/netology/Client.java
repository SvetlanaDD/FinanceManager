package ru.netology;

import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;
import org.json.simple.parser.ParseException;

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
            JSONObject buy = new JSONObject();

//            buy.put("title", "булка");
//            buy.put("date", "2022.02.08");
//            buy.put("sum", 200);

//            buy.put("title", "колбаса");
//            buy.put("date", "2022.02.10");
//            buy.put("sum", 150);

//            buy.put("title", "шапка");
//            buy.put("date", "2022.02.11");
//            buy.put("sum", 500);

//            buy.put("title", "акции");
//            buy.put("date", "2022.02.11");
//            buy.put("sum", 1000);

//            buy.put("title", "курица");
//            buy.put("date", "2022.02.11");
//            buy.put("sum", 200);

//            buy.put("title", "игрушка");
//            buy.put("date", "2022.02.11");
//            buy.put("sum", 2000);

            out.println(buy.toJSONString());

            // получаем ответ
            JSONParser parser = new JSONParser();
            Object obj;
            try {
                obj = parser.parse(in.readLine());
            } catch (ParseException e) {
                throw new RuntimeException(e);
            }

            JSONObject jsonObject = (JSONObject) obj;
            System.out.println(jsonObject.toJSONString());

        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }
}

