import java.time.LocalTime;
import java.util.ArrayList;
import java.util.List;

public class Park {
    private String name;
    private List<Attraction> attractions;


    public Park(String name) {
        this.name = name;
        this.attractions = new ArrayList<>();
    }

    public class Attraction {
        private String name;
        private LocalTime openingTime;
        private LocalTime closingTime;
        private double price;

        public Attraction(String name, LocalTime openingTime, LocalTime closingTime, double price) {
            this.name = name;
            this.openingTime = openingTime;
            this.closingTime = closingTime;
            this.price = price;
        }

        public String getName() {
            return name;
        }

        public void setName(String name) {
            this.name = name;
        }

        public LocalTime getOpeningTime() {
            return openingTime;
        }

        public void setOpeningTime(LocalTime openingTime) {
            this.openingTime = openingTime;
        }

        public LocalTime getClosingTime() {
            return closingTime;
        }

        public void setClosingTime(LocalTime closingTime) {
            this.closingTime = closingTime;
        }

        public double getPrice() {
            return price;
        }

        public void setPrice(double price) {
            this.price = price;
        }

        @Override
        public String toString() {
            return String.format("Аттракцион: %s, Время работы: %s - %s, Цена: %.2f руб.",
                    name, openingTime, closingTime, price);
        }
    }

    public void addAttraction(String name, LocalTime openingTime, LocalTime closingTime, double price) {
        Attraction newAttraction = new Attraction(name, openingTime, closingTime, price);
        attractions.add(newAttraction);
    }

    public void removeAttraction(String name) {
        attractions.removeIf(attraction -> attraction.getName().equals(name));
    }

    public List<Attraction> getAttractions() {
        return attractions;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder();
        sb.append("Парк: ").append(name).append("\n");
        sb.append("Аттракционы:\n");
        for (Attraction attraction : attractions) {
            sb.append(attraction).append("\n");
        }
        return sb.toString();
    }

    public static void main(String[] args) {
        Park dreamisland = new Park("Остров мечты");

        dreamisland.addAttraction("Американские горки",
                LocalTime.of(10, 0), LocalTime.of(20, 0), 200.00);
        dreamisland.addAttraction("Молот судьбы",
                LocalTime.of(9, 30), LocalTime.of(19, 30), 500.00);
        dreamisland.addAttraction("Вихрь",
                LocalTime.of(11, 0), LocalTime.of(18, 0), 350.50);


        System.out.println(dreamisland);
    }
}