package com.company;

public class Cat extends Animal {
    private static int catCount = 0;
    private boolean isFull;

    public Cat(String name) {
        super(name, 200, 0);
        this.isFull = false;
        catCount++;
    }

    public static int getCatCount() {
        return catCount;
    }

    public boolean isFull() {
        return isFull;
    }

    public void eatFromBowl(FoodBowl bowl, int foodAmount) {
        if (bowl.takeFood(foodAmount)) {
            this.isFull = true;
            System.out.println(name + " покушал и теперь сыт.");
        } else {
            System.out.println(name + " не смог покушать (недостаточно еды в миске).");
        }
    }
}