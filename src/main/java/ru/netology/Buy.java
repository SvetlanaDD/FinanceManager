package ru.netology;

public class Buy {
    private String title;
    private String date;
    private Long sum;

    public Buy(String title, String date, Long sum) {
        this.title = title;
        this.date = date;
        this.sum = sum;
    }

    public String getTitle() {
        return title;
    }

    public String getDate() {
        return date;
    }

    public Long getSum() {
        return sum;
    }

    @Override
    public String toString() {
        return "Buy{" +
                "title='" + title + '\'' +
                ", date='" + date + '\'' +
                ", sum=" + sum +
                '}';
    }
}
