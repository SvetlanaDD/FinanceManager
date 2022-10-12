package ru.netology;

import org.json.simple.JSONObject;

import java.io.File;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.*;

public class Logic {

    public static JSONObject generateOut(Manager manager, String date) {

        Map<String, String> categoriesTSV = WorkWithFile.tsvRead(new File("categories.tsv"));

        Category maxCategory = maxCategory(manager, categoriesTSV);
        Category maxYearCategory = maxYearCategory(manager, date, categoriesTSV);
        Category maxMonthCategory = maxMonthCategory(manager, date, categoriesTSV);
        Category maxDayCategory = maxDayCategory(manager, date, categoriesTSV);

        JSONObject maxCategoryJSON = new JSONObject();
        JSONObject list = new JSONObject();
        list.put("sum", maxCategory.getSum());
        list.put("category", maxCategory.getName());
        maxCategoryJSON.put("maxCategory", list);

        JSONObject listYear = new JSONObject();
        listYear.put("sum", maxYearCategory.getSum());
        listYear.put("category", maxYearCategory.getName());
        maxCategoryJSON.put("maxYearCategory", listYear);

        JSONObject listMonth = new JSONObject();
        listMonth.put("sum", maxMonthCategory.getSum());
        listMonth.put("category", maxMonthCategory.getName());
        maxCategoryJSON.put("maxMonthCategory", listMonth);

        JSONObject listDay = new JSONObject();
        listDay.put("sum", maxDayCategory.getSum());
        listDay.put("category", maxDayCategory.getName());
        maxCategoryJSON.put("maxDayCategory", listDay);

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
        category.put("еда", 0L);
        category.put("одежда", 0L);
        category.put("быт", 0L);
        category.put("финансы", 0L);
        category.put("другое", 0L);
        Long max = 0L;
        Category maxCategory = new Category("");// категория с максимальной суммой покупок
        for (Buy buy : manager.getListBuy()) {
            if (categoriesTSV.containsKey(buy.getTitle())) {     // если у покупки нет категории, то выбираем категорию "другое"
                switch (categoriesTSV.get(buy.getTitle())) {
                    case ("еда"): {
                        category.put("еда", category.get("еда") + buy.getSum());
                        if (category.get("еда") > max) {
                            max = category.get("еда");
                        }
                        break;
                    }
                    case ("одежда"): {
                        category.put("одежда", category.get("одежда") + buy.getSum());
                        if (category.get("одежда") > max) {
                            max = category.get("одежда");
                        }
                        break;
                    }
                    case ("быт"): {
                        category.put("быт", category.get("быт") + buy.getSum());
                        if (category.get("быт") > max) {
                            max = category.get("быт");
                        }
                        break;
                    }
                    case ("финансы"): {
                        category.put("финансы", category.get("финансы") + buy.getSum());
                        if (category.get("финансы") > max) {
                            max = category.get("финансы");
                        }
                        break;
                    }
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
