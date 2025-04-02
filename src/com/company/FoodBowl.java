package com.company;

public class FoodBowl {
    private int foodAmount;

    public FoodBowl(int initialFood) {
        this.foodAmount = initialFood;
    }

    public boolean takeFood(int amount) {
        if (foodAmount >= amount) {
            foodAmount -= amount;
            return true;
        }
        return false;
    }

    public void addFood(int amount) {
        foodAmount += amount;
        System.out.println("Добавлено " + amount + " еды в миску. Текущее количество: " + foodAmount);
    }

    public int getFoodAmount() {
        return foodAmount;
    }
}