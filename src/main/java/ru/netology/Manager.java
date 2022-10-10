package ru.netology;

import org.json.simple.JSONObject;
import java.util.List;

public class Manager {
    private List<Buy> listBuy;

    public Manager(List<Buy> listBuy) {
        this.listBuy = listBuy;
    }

    public List<Buy> getListBuy() {
        return listBuy;
    }

    public void addBuy (Object obj){
        JSONObject jsonObject = (JSONObject) obj;
        String title = (String) jsonObject.get("title");
        String date = (String) jsonObject.get("date");
        Long sum = (Long) jsonObject.get("sum");

        listBuy.add(new Buy(title, date, sum));
    }
}

