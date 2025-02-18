import controller.CharakterController;
import controller.ProduktController;
import model.Produkt;
import repository.CharakterRepository;
import repository.ProduktRepository;

import java.util.List;
import java.util.Scanner;

public class Main {
    public static void main(String[] args) {
        ProduktRepository produktRepo = new ProduktRepository();
        CharakterRepository charakterRepo = new CharakterRepository();


        ProduktController produktController = new ProduktController(produktRepo); // Constructor cu argument
        CharakterController charakterController = new CharakterController(charakterRepo, produktRepo);

        Scanner scanner = new Scanner(System.in);
        int choice;

        do {
            System.out.println("\nMain Menu:");
            System.out.println("1. Manage Produkte");
            System.out.println("2. Manage Charakters");
            System.out.println("0. Exit");
            System.out.print("Enter your choice: ");
            choice = scanner.nextInt();

            switch (choice) {
                case 1 -> produktController.run();
                case 2 -> charakterController.run();
            }
        } while (choice != 0);
    }
}
