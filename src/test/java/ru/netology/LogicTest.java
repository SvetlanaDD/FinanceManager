package ru.netology;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import static org.junit.jupiter.api.Assertions.*;

class LogicTest {

    @Test
    @DisplayName("Тестирование суммы покупок по одной категории")
    void maxCategorySum() {
        List<Buy> listBuy = new ArrayList<>();
        listBuy.add(new Buy("булка", "2022.02.08", 200L));
        listBuy.add(new Buy("сухарики", "2022.02.10", 100L));

        Map<String, String> category = new HashMap<>();
        category.put("булка", "еда");
        category.put("сухарики", "еда");
        Category maxCategory = new Category("еда");
        maxCategory.setSum(300L);

        Assertions.assertEquals(maxCategory.getSum(), Logic.maxCategory(new Manager(listBuy), category).getSum());
    }

    @Test
    @DisplayName("Тестирование нахождения максимума суммы покупок по разным категориям")
    void maxCategoryMax() {
        List<Buy> listBuy = new ArrayList<>();
        listBuy.add(new Buy("булка", "2022.02.08", 200L));
        listBuy.add(new Buy("шапка", "2022.02.10", 300L));

        Map<String, String> category = new HashMap<>();
        category.put("булка", "еда");
        category.put("шапка", "одежда");

        Category maxCategory = new Category("одежда");
        maxCategory.setSum(300L);

        Assertions.assertEquals(maxCategory.getName(), Logic.maxCategory(new Manager(listBuy), category).getName());
    }

    @Test
    @DisplayName("Тестирование суммы покупок по категории 'другое'")
    void maxCategoryOther() {
        List<Buy> listBuy = new ArrayList<>();
        listBuy.add(new Buy("булка", "2022.02.08", 200L));
        listBuy.add(new Buy("сухарики", "2022.02.10", 100L));
        listBuy.add(new Buy("игрушка", "2022.02.11", 2000L));

        Map<String, String> category = new HashMap<>();
        category.put("булка", "еда");

        Category maxCategory = new Category("другое");
        maxCategory.setSum(2000L);

        Assertions.assertEquals(maxCategory.getName(), Logic.maxCategory(new Manager(listBuy), category).getName());
    }
}