import java.util.*;

public class PhoneBook {

    public HashMap<String, String > phoneBook = new HashMap<>(); // создаем объект класса HashMap. В этом "словаре" будем хранить телефонную книгу

    public void addToMap(String enterNewNumber, String enterData) { // метод для добавления пары (ключ, значение) в множество
        phoneBook.put(enterNewNumber, enterData);
    }

    public void printMap() { // метод, который выводит всю карту в консоль
        for (Map.Entry<String, String> map: phoneBook.entrySet())
        {
            System.out.printf("Name: %s - phonenumber: %s\n", map.getValue(), map.getKey());
        }
    }

    public void printInfoAboutContactForKey(String enterData) { // метода для вывода информации о контакте, если совпадает номер контакта
                System.out.printf("Name: %s - phonenumber: %s\n", phoneBook.get(enterData), enterData);
            }


    public void printInfoAboutContactForValue(String enterData) { // метода для вывода информации о контакте, если совпадает имя контакта
        Set<String> keys = phoneBook.keySet();
        for (String i: keys) {
            if (phoneBook.get(i).equals(enterData)) {
                String findKey = i;
                System.out.printf("Name: %s - phonenumber: %s\n", enterData, findKey);
            }
        }
    }
}



