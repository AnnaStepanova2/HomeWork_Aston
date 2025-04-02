package com.company;

public interface Calculation {

    double calculatePerimeter();
    double calculateArea();

    void setFillColor(String fillColor);
    String getFillColor();
    void setBorderColor(String borderColor);
    String getBorderColor();

    default double defaultPerimeter() {
        return 0;
    }

    default double defaultArea() {
        return 0;
    }
}