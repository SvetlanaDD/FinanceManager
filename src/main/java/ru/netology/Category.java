package ru.netology;

public class Category {
    private String name;
    private Long sum;

    public Category(String name) {
        this.name = name;
        this.sum = 0L;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Long getSum() {
        return sum;
    }

    public void setSum(Long sum) {
        this.sum = sum;
    }
}
