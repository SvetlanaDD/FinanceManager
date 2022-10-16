package ru.netology;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;

import java.io.File;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.*;

public class Logic {

    public static String generateOut(Manager manager, String date) {

        Map<String, String> categoriesTSV = WorkWithFile.tsvRead(new File("categories.tsv"));

        Category maxCategory = maxCategory(manager, categoriesTSV);
        Category maxYearCategory = maxYearCategory(manager, date, categoriesTSV);
        Category maxMonthCategory = maxMonthCategory(manager, date, categoriesTSV);
        Category maxDayCategory = maxDayCategory(manager, date, categoriesTSV);

        Statistic statistic = new Statistic(maxCategory, maxYearCategory, maxMonthCategory, maxDayCategory);
        Gson gson = new GsonBuilder()
                .create();
        String maxCategoryJSON = gson.toJson(statistic);

        return maxCategoryJSON;
    }

    public static Category maxYearCategory(Manager manager, String date, Map<String, String> categoriesTSV) {
        Manager managerDate = new Manager(new ArrayList<>());
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy.MM.dd");
        LocalDate localDate = LocalDate.parse(date, formatter);
        for (Buy buy : manager.getListBuy()) {
            LocalDate buyDay = LocalDate.parse(buy.getDate(), formatter);
            if (localDate.getYear() == buyDay.getYear()) {
                managerDate.addBuy(buy);
            }
        }
        return maxCategory(managerDate, categoriesTSV);
    }

    public static Category maxMonthCategory(Manager manager, String date, Map<String, String> categoriesTSV) {
        Manager managerDate = new Manager(new ArrayList<>());
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy.MM.dd");
        LocalDate localDate = LocalDate.parse(date, formatter);
        for (Buy buy : manager.getListBuy()) {
            LocalDate buyDay = LocalDate.parse(buy.getDate(), formatter);
            if ((localDate.getYear() == buyDay.getYear()) && (localDate.getMonthValue() == buyDay.getMonthValue())) {
                managerDate.addBuy(buy);
            }
        }
        return maxCategory(managerDate, categoriesTSV);
    }

    public static Category maxDayCategory(Manager manager, String date, Map<String, String> categoriesTSV) {
        Manager managerDate = new Manager(new ArrayList<>());
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy.MM.dd");
        LocalDate localDate = LocalDate.parse(date, formatter);
        for (Buy buy : manager.getListBuy()) {
            LocalDate buyDay = LocalDate.parse(buy.getDate(), formatter);
            if ((localDate.getYear() == buyDay.getYear()) &&
                    (localDate.getMonthValue() == buyDay.getMonthValue()) &&
                    (localDate.getDayOfMonth() == buyDay.getDayOfMonth())) {
                managerDate.addBuy(buy);
            }
        }
        return maxCategory(managerDate, categoriesTSV);
    }

    public static Category maxCategory(Manager manager, Map<String, String> categoriesTSV) {
        Map<String, Long> category = new HashMap<>(); // общие суммы покупок по категориям
        categoriesTSV.forEach((key, value) ->
                {
                    if (category.isEmpty()) {
                        category.put(value, 0L);
                    } else {
                        if (!category.containsKey(value)) {
                            category.put(value, 0L);
                        }
                    }
                }
        );
        category.put("другое", 0L);
        Long max = 0L;
        Category maxCategory = new Category("");// категория с максимальной суммой покупок
        for (Buy buy : manager.getListBuy()) {
            if (categoriesTSV.containsKey(buy.getTitle())) {     // если у покупки нет категории, то выбираем категорию "другое"
                String categoryBuy = categoriesTSV.get(buy.getTitle());
                category.put(categoryBuy, category.get(categoryBuy) + buy.getSum());
                if (category.get(categoryBuy) > max) {
                    max = category.get(categoryBuy);
                }
            } else {
                category.put("другое", category.get("другое") + buy.getSum());
                if (category.get("другое") > max) {
                    max = category.get("другое");
                }
            }
            // поиск ключа по value в мапе
            for (Map.Entry<String, Long> pair : category.entrySet()) {
                if (pair.getValue() == max) {
                    maxCategory.setName(pair.getKey());
                    maxCategory.setSum(max);
                }
            }
        }
        return maxCategory;
    }
}
