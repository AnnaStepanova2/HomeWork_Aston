import java.util.HashMap;
import java.util.List;
import java.util.ArrayList;

public class PhoneBook {

    private HashMap<String, List<String>> phoneBook;

    public PhoneBook() {
        phoneBook = new HashMap<>();
    }

    public void add(String surname, String phoneNumber) {
        phoneBook.putIfAbsent(surname, new ArrayList<>());
        phoneBook.get(surname).add(phoneNumber);
    }

    public List<String> get(String surname) {
        return phoneBook.getOrDefault(surname, new ArrayList<>());
    }


}
