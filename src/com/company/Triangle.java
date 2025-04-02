package com.company;

public class Triangle implements Calculation {
    private double sideA;
    private double sideB;
    private double sideC;
    private String fillColor;
    private String borderColor;

    public Triangle(double sideA, double sideB, double sideC) {
        this.sideA = sideA;
        this.sideB = sideB;
        this.sideC = sideC;
    }

    @Override
    public double calculatePerimeter() {
        return sideA + sideB + sideC;
    }

    @Override
    public double calculateArea() {
        double s = calculatePerimeter() / 2;
        return Math.sqrt(s * (s - sideA) * (s - sideB) * (s - sideC));
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