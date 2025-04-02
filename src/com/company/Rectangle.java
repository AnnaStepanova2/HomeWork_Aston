package com.company;

public class Rectangle implements Calculation {
    private double width;
    private double height;
    private String fillColor;
    private String borderColor;

    public Rectangle(double width, double height) {
        this.width = width;
        this.height = height;
    }

    @Override
    public double calculatePerimeter() {
        return 2 * (width + height);
    }

    @Override
    public double calculateArea() {
        return width * height;
    }

    @Override
    public void setFillColor(String fillColor) {
        this.fillColor = fillColor;
    }

    @Override
    public String getFillColor() {
        return fillColor;
    }

    @Override
    public void setBorderColor(String borderColor) {
        this.borderColor = borderColor;
    }

    @Override
    public String getBorderColor() {
        return borderColor;
    }
}