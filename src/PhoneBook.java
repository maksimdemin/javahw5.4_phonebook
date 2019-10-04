import java.util.*;

public class PhoneBook {

    private final String REGEX_NAME = "(.*(\\d\\D|\\D\\d).*|\\D+)";
    private final String REGEX_PHONE = "(\\+?\\d?\\s?-?\\(?\\d{3}\\)?\\s?-?\\d{3}\\s?-?\\d{2}\\s?-?\\d{2})";
    private static final String HELP_FOR_NUMBER = "The phonenumber should contain only 10 digits (not including +7/8)\n" +
            "for example: <(495)-111-11-11> or <4951111111>\n" +
            "or 11 digits (including +7/8)\n" +
            "for example: <+7(495)-111-11-11> or <84951111111>";
    public static final String HELP = "\n" + HELP_FOR_NUMBER  +
            "\nThe contact name should not contain only numbers.\n" +
            "You can use all characters except <-> (minus), <()> (brackets), <+> (plus).\n\n" +
            "Available commands:\n" +
            "LIST - show all posts phonebook.\n" +
            "CLEAR - delete all contacts.\n" +
            "UPDATE - change contact.\n" +
            "DELETE - delete one of the contacts.\n" +
            "HELP - help section.\n" +
            "EXIT - stop program";


    private TreeMap<String, String > phoneBook = new TreeMap<>(); // создаем объект класса TreeMap. В этом "словаре" будем хранить телефонную книгу


    //-------------------------------------CREATE-----------------------------------------------------------------------

    public void addToMap(String enterData) { // метод для добавления пары (ключ, значение) в множество
        if (enterData.matches(REGEX_NAME) && enterData.matches("[^+()-]+")) { // проверка введенной строки на имя
            System.out.println("This contact does not exist. You have entered a name.\n" +
                    "Enter phonenumber for create new contact.");
            System.out.println(HELP_FOR_NUMBER);
            while (true) {
                Scanner scanner = new Scanner(System.in);
                String enterNewNumber = scanner.nextLine();
                if (enterNewNumber.matches(REGEX_PHONE)) {

                    enterNewNumber = enterNewNumber.replaceAll("[^0-9]", "");
                    if (enterNewNumber.matches("\\d{10}")) {
                        String regionRus = "+7";
                        enterNewNumber = regionRus.concat(enterNewNumber);
                        phoneBook.put(enterNewNumber, enterData);
                        System.out.println("A new contact has been added to your phonebook successfully.");
                        return;
                    } else if (enterNewNumber.matches("\\d{11}")) {
                        enterNewNumber = enterNewNumber.replaceAll("^\\d", "+7");
                        phoneBook.put(enterNewNumber, enterData);
                        System.out.println("A new contact has been added to your phonebook successfully.");
                        return;
                    }
                } else System.out.println("Invalid number. Try again. " + HELP_FOR_NUMBER);
            }
        } else if (enterData.matches(REGEX_PHONE)) {

            System.out.println("This contact does not exist. You have entered a number.");
            System.out.println("Enter name for create new contact. Important! The contact name should not contain only numbers.");
            while (true) {
                Scanner scanner = new Scanner(System.in);
                String enterNewName = scanner.nextLine();
                if (enterNewName.matches(REGEX_NAME) && enterNewName.matches("[^+()-]+") && !enterNewName.toUpperCase().matches("LIST|CLEAR|UPDATE|DELETE|HELP")) {
                    phoneBook.put(validationNumber(enterData), enterNewName);
                    System.out.println("A new contact has been added to your phonebook successfully.");
                    return;
                } else System.out.println("Invalid name. Try again. Important! The contact name should not contain only numbers.");
            }
        } else System.out.println("Invalid number, name or command. Try again.");
    }

    //--------------------------------------READ------------------------------------------------------------------------

    public void printInfoAboutContactForKey(String enterData) { // метода для вывода информации о контакте, если совпадает номер контакта
        System.out.printf("Name: %s - phonenumber: %s\n", phoneBook.get(validationNumber(enterData)), validationNumber(enterData));
    }


    public void printInfoAboutContactForValue(String enterData) { // метода для вывода информации о контакте, если совпадает имя контакта
        Set<String> keys = phoneBook.keySet();
        for (String number: keys) {
            if (phoneBook.get(number).equals(enterData)) {
                System.out.printf("Name: %s - phonenumber: %s\n", enterData, number);
            }
        }
    }


    public void printMap() { // метод, который выводит всю карту в консоль
        for (Map.Entry<String, String> map: phoneBook.entrySet())
        {
            System.out.printf("Name: %s - phonenumber: %s\n", map.getValue(), map.getKey());
        }
    }


    public boolean isContainsKey(String enterData) { // метод для проверки наличия в книге номера, также проверка на валидность введенных данных
        boolean isContains = false;
        if (enterData.matches(REGEX_PHONE)) {
            enterData = enterData.replaceAll("[^0-9]", "");
            if (enterData.matches("^\\d+") && enterData.matches("\\d{10}")) {
                String regionRus = "+7";
                enterData = regionRus.concat(enterData);
                if (phoneBook.containsKey(enterData)) {
                    isContains = true;
                }
            }
            else if (enterData.matches("\\d{11}")) {
                enterData = enterData.replaceAll("^\\d", "+7");
                if (phoneBook.containsKey(enterData)) {
                    isContains = true;
                }
            }
        }
        return isContains;
    }


    public boolean isContainsValue(String enterData) { // метод для проверки наличия в книге имени, также проверка на валидность введенных данных
        boolean isContains = false;
        if (phoneBook.containsValue(enterData) && enterData.matches(REGEX_NAME) && enterData.matches("[^+()-]+")) {
            isContains = true;
        }
        return isContains;
    }

    public boolean isEmptyMap() { // метод для проверки пустое множество или нет
        boolean isEmpty = false;
        if (phoneBook.isEmpty()) {
            isEmpty = true;
        }
        return isEmpty;
    }

    private String validationNumber(String enterData) { // валидация введенного номера в иных случаях
        enterData = enterData.replaceAll("[^0-9]", "");
        if (enterData.matches("^\\d+") && enterData.matches("\\d{10}")) {
            String regionRus = "+7";
            enterData = regionRus.concat(enterData);
        } else if (enterData.matches("\\d{11}")) {
            enterData = enterData.replaceAll("^\\d", "+7");
        }
        return enterData;
    }

    private String validatonTenDigitsNumber(String enterData) {
        enterData = enterData.replaceAll("[^0-9]", "");
        if (enterData.matches("^\\d+") && enterData.matches("\\d{10}")) {
            String regionRus = "+7";
            enterData = regionRus.concat(enterData);
        }
        return enterData;
    }

    //---------------------------------------------UPDATE---------------------------------------------------------------

    public void updateContact() {
        if (isEmptyMap()) {
            System.out.println("Your phonebook is empty. Create new phonebook.");
        }
        else
            while (true) {
            System.out.println("Enter the number or name of the contact to be modified.");
        Scanner scanner = new Scanner(System.in);
        String enterData = scanner.nextLine();
         if (isContainsKey(enterData)) {
             System.out.println("This contact will be changed.");
             printInfoAboutContactForKey(validationNumber(enterData));
             System.out.println("Enter new phonenumber.");

             while (true) {
                 String enterUpdateNumber = scanner.nextLine();
                 if (enterUpdateNumber.matches(REGEX_PHONE)) {
                     enterUpdateNumber = enterUpdateNumber.replaceAll("[^0-9]", "");
                     if (enterUpdateNumber.matches("^\\d+") && enterUpdateNumber.matches("\\d{10}")) {
                         String regionRus = "+7";
                         enterUpdateNumber = regionRus.concat(enterUpdateNumber);
                         String oldNumber = validationNumber(enterData);
                         phoneBook.put(enterUpdateNumber, phoneBook.get(validationNumber(enterData)));
                         phoneBook.remove(oldNumber);
                         System.out.println("Your contact has been updated successfully.");
                         return;
                     } else if (enterUpdateNumber.matches("\\d{11}")) {
                         enterUpdateNumber = enterUpdateNumber.replaceAll("^\\d", "+7");
                         String oldNumber = validationNumber(enterData);
                         phoneBook.put(enterUpdateNumber, phoneBook.get(validationNumber(enterData)));
                         phoneBook.remove(oldNumber);
                         System.out.println("Your contact has been updated successfully.");
                         return;
                     }
                 }
                 else System.out.println("Invalid phonenumber. Try again. " + HELP_FOR_NUMBER);
             }
         }
         else System.out.println("Phonenumber not found in your phone book. Try again.");
            }
            System.out.println("Invalid phonenumber, name or command. Try again.");
    }

    //----------------------------------------------DELETE--------------------------------------------------------------

    public void deleteContact() { // метод, который удаляет контакт по номеру
        if (isEmptyMap()) {
            System.out.println("Your phonebook is empty. Create new phonebook.");
        } else
            while (true)
            {
                System.out.println("Enter the phonenumber to delete this contact.");
                Scanner scanner = new Scanner(System.in);
                String enterData = scanner.nextLine();

                if (isContainsKey(enterData)) {
                      String deletedNumber = validationNumber(enterData);
                    System.out.printf("Name: %s - phonenumber: %s\n", phoneBook.get(validationNumber(enterData)), deletedNumber +" <- this contact deleted successfully.");
                    phoneBook.remove(validationNumber(enterData));
                    return;
                }
                else System.out.println("Phonenumber not found in your phonebook. Try again.. " + HELP_FOR_NUMBER);
            }
            System.out.println("Foooooo.");
    }


    public void deleteAllContacts() { // метод, который удаляет всё множество полностью
        if (isEmptyMap()) {
            System.out.println("Cannot empty an empty phone book.");
        }
        phoneBook.clear();
    }
}



