import java.time.LocalDate;
public class Product {
    private String name;
    private String productionDate;
    private String manufacturer;
    private String countryOfOrigin;
    private double price;
    private boolean isReserved;

    public Product(String name, String productionDate, String manufacturer,
                   String countryOfOrigin, double price, boolean isReserved) {
        this.name = name;
        this.productionDate = productionDate;
        this.manufacturer = manufacturer;
        this.countryOfOrigin = countryOfOrigin;
        this.price = price;
        this.isReserved = isReserved;
    }

    public void printProductInfo() {
        System.out.println("Информация о товаре:");
        System.out.println("Название: " + name);
        System.out.println("Дата производства: " + productionDate);
        System.out.println("Производитель: " + manufacturer);
        System.out.println("Страна происхождения: " + countryOfOrigin);
        System.out.println("Цена: " + price + " руб.");
        System.out.println("Забронирован: " + (isReserved ? "Да" : "Нет"));
    }

    public static void main(String[] args) {
        Product product = new Product(
                "Смартфон",
                "27.01.2025",
                "Xiaomi Corporation",
                "China",
                49999.99,
                false
        );


        product.printProductInfo();
    }
    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }


    @Override
    public String toString() {
        return "Product{" +
                "Название:'" + name + '\'' +
                ", Дата производства='" + productionDate + '\'' +
                ", Производитель='" + manufacturer + '\'' +
                ", Страна происхождения='" + countryOfOrigin + '\'' +
                ", Цена=" + price +
                ", Забронирован=" + isReserved +
                '}';
    }
}

