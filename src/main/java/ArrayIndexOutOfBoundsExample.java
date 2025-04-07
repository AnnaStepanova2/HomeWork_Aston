public class ArrayIndexOutOfBoundsExample {
    public static void main(String[] args) {
        try {

            int[] numbers = {1, 2, 3, 4, 5};


            System.out.println("Попытка получить элемент с индексом 7...");
            int value = numbers[7];

        } catch (ArrayIndexOutOfBoundsException e) {

            System.err.println("Произошла ошибка: " + e.getMessage());
            System.err.println("Индекс выходит за пределы допустимого диапазона.");
        }


        System.out.println("Программа завершила выполнение.");
    }
}