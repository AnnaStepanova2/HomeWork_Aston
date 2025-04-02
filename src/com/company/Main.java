package com.company;

public class Main {
    public static void main(String[] args) {

        Dog dog1 = new Dog("Бобик");
        Dog dog2 = new Dog("Шарик");
        Cat cat1 = new Cat("Мурзик");
        Cat cat2 = new Cat("Барсик");

        System.out.println("Всего животных: " + Animal.getAnimalCount());
        System.out.println("Котов: " + Cat.getCatCount());
        System.out.println("Собак: " + Dog.getDogCount());

        dog1.run(350);
        dog1.swim(25);
        cat1.run(450);
        cat1.swim(7);

        FoodBowl bowl = new FoodBowl(10);

        Cat[] cats = {cat1, cat2};

        for (Cat cat : cats) {
            cat.eatFromBowl(bowl, 5);
        }

        for (Cat cat : cats) {
            System.out.println(cat.name + " сыт: " + cat.isFull());
        }

        bowl.addFood(10);

        for (Cat cat : cats) {
            cat.eatFromBowl(bowl, 5);
        }

        for (Cat cat : cats) {
            System.out.println(cat.name + " сыт: " + cat.isFull());
        }

        System.out.println("_________________________________");
        Circle circle = new Circle(7);
        circle.setFillColor("White");
        circle.setBorderColor("Pink");

        Rectangle rectangle = new Rectangle(5, 9);
        rectangle.setFillColor("Blue");
        rectangle.setBorderColor("Black");

        Triangle triangle = new Triangle(3, 4, 5);
        triangle.setFillColor("Green");
        triangle.setBorderColor("Yellow");


        printShapeDetails(circle);
        printShapeDetails(rectangle);
        printShapeDetails(triangle);
    }


        private static void printShapeDetails (Calculation shape){
            System.out.println("Периметр: " + shape.calculatePerimeter());
            System.out.println("Площадь: " + shape.calculateArea());
            System.out.println("Цвет заливки: " + shape.getFillColor());
            System.out.println("Цвет границы: " + shape.getBorderColor());
            System.out.println("_________________________________");
        }

    }