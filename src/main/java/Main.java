public class Main {
    public static void main(String[] args) {

        Product[] productsArray = new Product[5];

        // Заполняем массив товарами
        productsArray[0] = new Product("Samsung S25 Ultra", "01.02.2025",
                "Samsung Corp.", "Korea", 5599, true);
        productsArray[1] = new Product("iPhone 16", "25.09.2024",
                "Apple Inc.", "USA", 10499, false);
        productsArray[2] = new Product("Xiaomi 15", "10.01.2025",
                "Xiaomi Corporation", "China", 13799, true);
        productsArray[3] = new Product("Galaxy Tab S10", "01.03.2025",
                "Samsung Corp.", "Korea", 8299, false);
        productsArray[4] = new Product("MacBook Pro 16", "13.11.2024",
                "Apple Inc.", "USA", 14599, true);


        for (Product product : productsArray) {
            System.out.println(product);
        }
    }

}