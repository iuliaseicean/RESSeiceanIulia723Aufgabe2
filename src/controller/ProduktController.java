package controller;

import model.Produkt;
import repository.ProduktRepository;
import java.util.List;
import java.util.Scanner;

public class ProduktController {
    private ProduktRepository produktRepo;
    private Scanner scanner = new Scanner(System.in);

    // Constructor care primește produktRepository ca parametru
    public ProduktController(ProduktRepository produktRepo) {
        this.produktRepo = produktRepo;
    }

    // CRUD Operations for produkts
    public void addProdukt() {
        System.out.print("Enter produkt name: ");
        String name = scanner.nextLine();
        System.out.print("Enter produkt preis: ");
        int preis = scanner.nextInt();
        scanner.nextLine(); // Consume the newline
        System.out.print("Enter produkt herkunftsregion: ");
        String herkunftsregion = scanner.nextLine();

        Produkt produkt = new Produkt(name, preis, herkunftsregion);
        produktRepo.addProdukt(produkt);
        System.out.println("produkt added successfully!");
    }

    public void updateProdukt() {
        System.out.print("Enter produkt name to update: ");
        String name = scanner.nextLine();

        System.out.print("Enter new preis: ");
        int preis = scanner.nextInt();
        scanner.nextLine(); // Consume the newline


        System.out.print("Enter new herkunftsregion: ");
        String herkunftsregion = scanner.nextLine();

        Produkt produkt = new Produkt(name, preis, herkunftsregion);
        produktRepo.updateProdukt(produkt);
        System.out.println("produkt updated successfully!");
    }

    public void deleteProdukt() {
        System.out.print("Enter produkt name to delete: ");
        String name = scanner.nextLine();

        produktRepo.deleteProdukt(name);
        System.out.println("produkt deleted successfully!");
    }

   /* public void listAllProdukts() {
        List<Produkt> produkts = produktRepo.getAllProdukte();
        if (produkts.isEmpty()) {
            System.out.println("No produkts available.");
        } else {
            produkts.forEach(System.out::println);
        }
    }

    // Metodă care rulează aplicația pentru gestionarea produselor
    public void run() {
        int choice;
        do {
            System.out.println("\nprodukt Management:");
            System.out.println("1. Add produkt");
            System.out.println("2. Update produkt");
            System.out.println("3. Delete produkt");
            System.out.println("4. List All produkte");
            System.out.println("0. Exit");
            System.out.print("Enter your choice: ");
            choice = scanner.nextInt();
            scanner.nextLine(); // Consume the newline

            switch (choice) {
                case 1 -> addProdukt();
                case 2 -> updateProdukt();
                case 3 -> deleteProdukt();
                case 4 -> listAllProdukts();
                case 0 -> System.out.println("Exiting produkt Management.");
                default -> System.out.println("Invalid choice.");
            }
        } while (choice != 0);
    }*/
}
