package ru.netology;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.util.HashMap;
import java.util.Map;

public class WorkWithFile {
    public static Map<String, String> tsvRead(File fileTSV) {
        Map<String, String> categoriesTSV = new HashMap<>();
        try (BufferedReader TSVReader = new BufferedReader(new FileReader(fileTSV))) {
            String line;
            while ((line = TSVReader.readLine()) != null) {
                String[] lineItems = line.split("\t");
                categoriesTSV.put(lineItems[0], lineItems[1]);
            }
        } catch (Exception e) {
            System.out.println("Не могу прочитать файл");
        }
        return categoriesTSV;
    }
}
