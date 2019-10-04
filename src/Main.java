import java.util.Scanner;

public class Main {

    public static void main(String[] args) {



        PhoneBook myPhoneBook = new PhoneBook(); // создаем объект класса PhoneBook

        System.out.println(myPhoneBook.HELP);

        for (;;) {
            Scanner scan = new Scanner(System.in);
            System.out.println("Enter Name/phonenumber or command (for help enter command HELP).");
            String enterData = scan.nextLine();

            if (myPhoneBook.isContainsKey(enterData)) {
                System.out.println("This contact already exists.");
                myPhoneBook.printInfoAboutContactForKey(enterData);
            }
            else if (myPhoneBook.isContainsValue(enterData)) {
                System.out.println("This contact already exists.");
                myPhoneBook.printInfoAboutContactForValue(enterData);
            }
            else if (!myPhoneBook.isContainsValue(enterData) && !myPhoneBook.isContainsKey(enterData) && !enterData.toUpperCase().matches("LIST|CLEAR|UPDATE|DELETE|HELP|EXIT")) {
                myPhoneBook.addToMap(enterData);
            }
            else if (enterData.toUpperCase().matches("LIST")) {
                if (myPhoneBook.isEmptyMap()) {
                    System.out.println("Your phonebook is empty. Create new phonebook.");
                }
                myPhoneBook.printMap();
            }
            else if (enterData.toUpperCase().matches("CLEAR")) {
                myPhoneBook.deleteAllContacts();
                System.out.println("Your phonebook is empty. Create new phonebook or enter EXIT.");
            }
            else if (enterData.toUpperCase().matches("UPDATE")) {
                myPhoneBook.updateContact();
            }
            else if (enterData.toUpperCase().matches("DELETE")) {
                myPhoneBook.deleteContact();
            }
            else if (enterData.toUpperCase().matches("EXIT")) {
                System.out.println("Goodbye");
                break;
            }
            else if (enterData.toUpperCase().matches("HELP")) {
                System.out.println(myPhoneBook.HELP);
            }
            else System.out.println("Invalid number, name or command. Try again (for help enter command HELP).");
        }
    }
}
