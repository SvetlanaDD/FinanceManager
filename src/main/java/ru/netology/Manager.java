package ru.netology;

import java.io.Serializable;
import java.util.List;

public class Manager implements Serializable {
    private static final long serialVersionUID = 1234567L;
    private List<Buy> listBuy;

    public Manager(List<Buy> listBuy) {
        this.listBuy = listBuy;
    }

    public List<Buy> getListBuy() {
        return listBuy;
    }

    public void addBuy(Buy buy) {
        this.listBuy.add(buy);
    }
}

