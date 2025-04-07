
public class Main {
    public static int processArray(String[][] array) throws MyArraySizeException, MyArrayDataException {

        if (array.length != 4 || array[0].length != 4) {
            throw new MyArraySizeException("Массив должен быть размером 4x4.");
        }

        int sum = 0;

        for (int i = 0; i < array.length; i++) {
            for (int j = 0; j < array[i].length; j++) {
                try {
                    sum += Integer.parseInt(array[i][j]);
                } catch (NumberFormatException e) {
                    throw new MyArrayDataException("Неверные данные в ячейке [" + i + "][" + j + "]", i, j);
                }
            }
        }

        return sum;
    }

    public static void main(String[] args) {

        String[][] array = {
                {"1", "2", "3", "4"},
                {"5", "6", "7", "8"},
                {"9", "10", "11", "12"},
                {"13", "14", "fifteen", "16"} // Ошибка в этой строке
        };

        try {

            int result = processArray(array);
            System.out.println("Сумма элементов массива: " + result);
        } catch (MyArraySizeException e) {

            System.err.println("Ошибка размера массива: " + e.getMessage());
        } catch (MyArrayDataException e) {

            System.err.println("Ошибка данных в массиве: " + e.getMessage());
            System.err.println("Позиция ошибки: строка " + e.getRow() + ", столбец " + e.getCol());
        }
    }
}