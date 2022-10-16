package ru.netology;

public class Statistic {
    private Category maxCategory;
    private Category maxYearCategory;
    private Category maxMonthCategory;
    private Category maxDayCategory;

    public Statistic(Category maxCategory, Category maxYearCategory, Category maxMonthCategory, Category maxDayCategory) {
        this.maxCategory = maxCategory;
        this.maxYearCategory = maxYearCategory;
        this.maxMonthCategory = maxMonthCategory;
        this.maxDayCategory = maxDayCategory;
    }

    @Override
    public String toString() {
        return "{\n" +
                "\"maxCategory\": " + maxCategory + ",\n" +
                "\"maxYearCategory\": " + maxYearCategory + ",\n" +
                "\"maxMonthCategory\": " + maxMonthCategory + ",\n" +
                "\"maxDayCategory\": " + maxDayCategory + "\n" +
                '}';
    }
}

