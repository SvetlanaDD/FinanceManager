package ru.netology;

import java.io.*;
import java.util.ArrayList;
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

    public static void saveBin(Manager manager) throws IOException {
        ObjectOutputStream outSer = new ObjectOutputStream(new FileOutputStream("data.bin"));
        outSer.writeObject(manager);
        outSer.close();
    }

    public static Manager loadFromBinFile(File file) throws IOException, ClassNotFoundException {
        Manager manager = new Manager(new ArrayList<>());
        if (file.exists()) {
            ObjectInputStream in = new ObjectInputStream(new FileInputStream(file));
            manager = (Manager) in.readObject();
            in.close();
        }
        return manager;
    }
}
