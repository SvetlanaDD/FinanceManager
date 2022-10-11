package ru.netology;

import org.json.simple.JSONObject;

import java.io.File;
import java.util.*;

public class Logic {

    public static JSONObject generateOut(Manager manager) {

        Map<String, String> categoriesTSV = WorkWithFile.tsvRead(new File("categories.tsv"));

        Category maxCategory = maxCategory(manager, categoriesTSV);

        JSONObject list = new JSONObject();

        list.put("sum", maxCategory.getSum());
        list.put("category", maxCategory.getName());
        JSONObject maxCategoryJSON = new JSONObject();
        maxCategoryJSON.put("maxCategory", list);

        return maxCategoryJSON;
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
