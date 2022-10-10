package ru.netology;

import org.json.simple.JSONObject;

import java.io.File;
import java.util.*;

public class Logic {

    public static JSONObject generateOut(Manager manager) {
        Map<String, Long> category = new HashMap<>(); // общие суммы покупок по категориям
        category.put("еда", 0L);
        category.put("одежда", 0L);
        category.put("быт", 0L);
        category.put("финансы", 0L);
        category.put("другое", 0L);
        Long max = 0L;
        String maxCategoryKey = null;   // категория с максимальной суммой покупок

        Map<String, String> categoriesTSV = WorkWithFile.tsvRead(new File("categories.tsv"));

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
                    maxCategoryKey = pair.getKey();
                }
            }

        }

        JSONObject list = new JSONObject();
        list.put("sum", category.get(maxCategoryKey));
        list.put("category", maxCategoryKey);

        JSONObject maxCategory = new JSONObject();
        maxCategory.put("maxCategory", list);

        return maxCategory;
    }
}
