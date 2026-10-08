import java.util.Collections;
import java.util.List;
import java.util.Scanner;

public class Main {
    private static final Scanner scanner = new Scanner(System.in);

    public static void main(String[] args) {
        ConnectionManager connection = new ConnectionManager();

        System.out.print("Username: ");
        String username = scanner.nextLine();

        System.out.print("Password: ");
        String password = scanner.nextLine();

        if (!connection.connect(username, password)) {
            System.out.println("Login failed.");
            return;
        }

        try {
            List<Card> cards = connection.readCards();
            runMenu(connection, cards);
        } catch (Exception e) {
            System.out.println("Error communicating with the server.");
        } finally {
            connection.close();
        }
    }

    private static void runMenu(ConnectionManager connection, List<Card> cards) throws Exception {
        while (true) {
            printMenu();

            System.out.print("Choice: ");
            String input = scanner.nextLine();

            int choice;
            try {
                choice = Integer.parseInt(input);
            } catch (NumberFormatException e) {
                System.out.println("Please enter a number.");
                continue;
            }

            switch (choice) {
                case 1:
                    cards = connection.getCards();
                    printCards(cards);
                    break;

                case 2:
                    System.out.println("Credits: " + connection.getCredits());
                    break;

                case 3:
                    List<Card> offers = connection.getOffers();
                    printCards(offers);
                    break;

                case 4:
                    buyCard(connection);
                    break;

                case 5:
                    sellCard(connection);
                    break;

                case 7:
                    return;

                default:
                    System.out.println("Invalid choice.");
            }
        }
    }

    private static void printMenu() {
        System.out.println();
        System.out.println("1. list my cards");
        System.out.println("2. show my credits");
        System.out.println("3. list available cards");
        System.out.println("4. buy card");
        System.out.println("5. sell card");
        System.out.println("7. exit");
    }

    private static void printCards(List<Card> cards) {
        Collections.sort(cards);

        for (Card card : cards) {
            System.out.println(card);
        }
    }

    private static void buyCard(ConnectionManager connection) throws Exception {
        System.out.print("Enter card ID: ");
        int id = readInteger();

        System.out.println(connection.buy(id) ? "Bought." : "Failed.");
    }

    private static void sellCard(ConnectionManager connection) throws Exception {
        System.out.print("Enter card ID: ");
        int id = readInteger();

        System.out.print("Enter price: ");
        int price = readInteger();

        System.out.println(connection.sell(id, price) ? "Listed." : "Failed.");
    }

    private static int readInteger() {
        while (true) {
            try {
                return Integer.parseInt(scanner.nextLine());
            } catch (NumberFormatException e) {
                System.out.print("Please enter a number: ");
            }
        }
    }
}
