package view;

import controller.ProduktController;
import controller.CharakterController;
import model.Produkt;
import model.Charakter;
import repository.ProduktRepository;

import java.util.Scanner;

public class ProduktConsoleView {
    private ProduktController produktController;
    private Scanner scanner;

    public ProduktConsoleView(ProduktRepository produktRepo) {
        this.produktController = new ProduktController(produktRepo); // Apel constructor cu 1 parametru
        this.scanner = new Scanner(System.in);
    }

    public void showMenu() {
        boolean running = true;
        while (running) {
            System.out.println("\nProdukt Management:");
            System.out.println("1. Add Produkt");
            System.out.println("2. Update Produkt");
            System.out.println("3. Delete Produkt");
            System.out.println("4. List All Produkts");
            System.out.println("0. Exit");

            System.out.print("Enter your choice: ");
            int choice = scanner.nextInt();
            scanner.nextLine();

            switch (choice) {
                case 1 -> produktController.addProdukt();
                case 2 -> produktController.updateProdukt();
                case 3 -> produktController.deleteProdukt();
                case 4 -> produktController.listAllProdukts();
                case 0 -> running = false;
                default -> System.out.println("Invalid choice. Please try again.");
            }
        }
    }
}