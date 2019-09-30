import java.util.Scanner;

public class Main {

    public static void main(String[] args) {

        PhoneBook myPhoneBook = new PhoneBook(); // создаем объект класса PhoneBook

        for (;;) {
            Scanner scan = new Scanner(System.in);
            System.out.println("Enter Name or phonenumber");
            String enterData = scan.nextLine();

            if (myPhoneBook.phoneBook.containsKey(enterData)) {
                System.out.println("This contact already exists.");
                myPhoneBook.printInfoAboutContactForKey(enterData);
            }
            else if (myPhoneBook.phoneBook.containsValue(enterData)) {
                System.out.println("This contact already exists.");
                myPhoneBook.printInfoAboutContactForValue(enterData);
            }
            else if (!myPhoneBook.phoneBook.containsValue(enterData) && !myPhoneBook.phoneBook.containsKey(enterData) && !enterData.equals("LIST")) {
                if (enterData.matches("\\D+")) {
                    System.out.println("This contact does not exist. You have entered a name.");
                    System.out.println("Enter phonenumber for create new contact.");
                    String enterNewNumber = scan.nextLine();
                    if (enterNewNumber.matches("\\+?\\d+")) {
                        myPhoneBook.addToMap(enterNewNumber, enterData);
                        System.out.println("A new contact has been added to your phonebook successfully.");
                        //myPhoneBook.printMap();
                    } else System.out.println("Invalid number. Try again.");
                }
                else if (enterData.matches("\\+?\\d+")) {
                    System.out.println("This contact does not exist. You have entered a number.");
                    System.out.println("Enter name for create new contact.");
                    String enterNewName = scan.nextLine();
                    if (enterNewName.matches("\\D+")) {
                        myPhoneBook.addToMap(enterData, enterNewName);
                        System.out.println("A new contact has been added to your phonebook successfully.");
                        //myPhoneBook.printMap();
                    } else System.out.println("Invalid name. Try again.");
                }
            }
            else if (enterData.equals("LIST")) {
                myPhoneBook.printMap();
            }
        }
    }
}
