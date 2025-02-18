package view;

import controller.CharakterController;
import model.Charakter;
import repository.CharakterRepository;
import repository.ProduktRepository;

import java.util.Scanner;

public class CharakterConsoleView {
    private CharakterController charakterController;
    private Scanner scanner;

    public CharakterConsoleView(CharakterRepository charakterRepo, ProduktRepository produktRepo) {
        this.charakterController = new CharakterController(charakterRepo, produktRepo); // Apel constructor cu 2 parametri
        this.scanner = new Scanner(System.in);
    }
    public void showMenu() {
        boolean running = true;
        while (running) {
            System.out.println("\nCharakter Management:");
            System.out.println("1. Add Charakter");
            System.out.println("2. Update Charakter");
            System.out.println("3. Delete Charakter");
            System.out.println("4. List All Charakters");
            System.out.println("5. Filter Charakters by Location");
            System.out.println("6. Filter Charakters by Produkt Season");
            System.out.println("7. Sort Produkts by Charakter");
            System.out.println("0. Exit");

            System.out.print("Enter your choice: ");
            int choice = scanner.nextInt();
            scanner.nextLine(); // Consume newline

            switch (choice) {
                case 1 -> charakterController.addCharakter();
                case 2 -> charakterController.updateCharakter();
                case 3 -> charakterController.deleteCharakter();
                case 4 -> charakterController.listAllCharakters();
                case 5 -> charakterController.filterCharaktersByStadt();
                case 6 -> charakterController.sortProduktsByCharakter();
                case 7 -> {
                    // Cere ID-ul clientului și ordinea de sortare
                    System.out.print("Enter charakter ID: ");
                    int charakterId = scanner.nextInt();

                    System.out.print("Enter sort order (1 for ascending, 0 for descending): ");
                    boolean ascending = scanner.nextInt() == 1;

                    charakterController.sortProduktsByCharakter(charakterId, ascending);
                }
                case 0 -> running = false;
                default -> System.out.println("Invalid choice. Please try again.");
            }
        }
    }

}
