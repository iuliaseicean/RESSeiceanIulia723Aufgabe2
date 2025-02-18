package controller;

import model.Produkt;
import model.Charakter;
import repository.ProduktRepository;
import repository.CharakterRepository;

import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;
import java.util.Scanner;
import java.util.stream.Collectors;

public class CharakterController {
    private CharakterRepository charakterRepo;
    private ProduktRepository produktRepo;
    private Scanner scanner = new Scanner(System.in);

    public CharakterController(CharakterRepository charakterRepo, ProduktRepository produktRepo) {
        this.charakterRepo = charakterRepo;
        this.produktRepo = produktRepo;
        this.scanner = new Scanner(System.in);
    }

    
    public void addCharakter() {
        System.out.print("Enter charakter ID: ");
        int id = scanner.nextInt();
        scanner.nextLine();
        System.out.print("Enter charakter name: ");
        String name = scanner.nextLine();
        System.out.print("Enter charakter herkunftsdorf: ");
        String herkunftsdorf = scanner.nextLine();

        List<Produkt> produkte = new ArrayList<>();

        List<Produkt> availableProdukts = produktRepo.getAllProdukte();

        if (!availableProdukts.isEmpty()) {
            System.out.println("Available produkte:");
            for (int i = 0; i < availableProdukts.size(); i++) {
                System.out.println((i + 1) + ". " + availableProdukts.get(i).getName() + " - " + availableProdukts.get(i).getPreis() + "€");
            }

            while (true) {
                System.out.print("Enter produkt number to add (or 0 to stop): ");
                int produktIndex = scanner.nextInt();
                scanner.nextLine(); // Consumăm newline-ul rămas

                if (produktIndex == 0) {
                    break;
                }

                if (produktIndex > 0 && produktIndex <= availableProdukts.size()) {
                    Produkt selectedProdukt = availableProdukts.get(produktIndex - 1);
                    produkte.add(selectedProdukt);
                    System.out.println(selectedProdukt.getName() + " added to charakter.");
                } else {
                    System.out.println("Invalid selection. Try again.");
                }
            }
        } else {
            System.out.println("No produkte available.");
        }

        Charakter charakter = new Charakter(id, name, herkunftsdorf, produkte);
        charakterRepo.addCharakter(charakter);
        System.out.println("Charakter added successfully!");
    }



    public void updateCharakter() {
        System.out.print("Enter charakter ID to update: ");
        int id = scanner.nextInt();
        scanner.nextLine(); // Consume the newline

        Charakter charakter = charakterRepo.getCharakterById(id);
        if (charakter != null) {
            System.out.print("Enter new charakter name: ");
            String name = scanner.nextLine();
            System.out.print("Enter new charakter herkunftsdorf: ");
            String herkunftsdorf = scanner.nextLine();

            charakter.setName(name);
            charakter.setHerkunftsdorf(herkunftsdorf);
            charakterRepo.updateCharakter(charakter);
            System.out.println("Charakter updated successfully!");
        } else {
            System.out.println("Charakter not found.");
        }
    }

    public void deleteCharakter() {
        System.out.print("Enter charakter ID to delete: ");
        int id = scanner.nextInt();
        scanner.nextLine(); // Consume the newline

        charakterRepo.deleteCharakter(id);
        System.out.println("Charakter deleted successfully!");
    }

    public void listAllCharakters() {
        List<Charakter> charakters = charakterRepo.getAllCharakters();
        if (charakters.isEmpty()) {
            System.out.println("No charakters available.");
        } else {
            charakters.forEach(System.out::println);
        }
    }

    public void filterCharaktersByHerkunftsdorf() {
        System.out.print("Enter herkunftsdorf to filter charakters: ");
        String herkunftsdorf = scanner.nextLine();

        List<Charakter> filteredCharakters = charakterRepo.getAllCharakters().stream()
                .filter(charakter -> charakter.getHerkunftsdorf().equalsIgnoreCase(herkunftsdorf))
                .collect(Collectors.toList());

        if (filteredCharakters.isEmpty()) {
            System.out.println("No charakters found in this herkunftsdorf.");
        } else {
            filteredCharakters.forEach(System.out::println);
        }
    }

    public void sortProduktsByCharakter() {
        System.out.print("Enter charakter name: ");
        String charakterName = scanner.nextLine();
        Charakter charakter = charakterRepo.getAllCharakters().stream()
                .filter(v -> v.getName().equalsIgnoreCase(charakterName))
                .findFirst()
                .orElse(null);

        if (charakter == null) {
            System.out.println("Charakter not found!");
            return;
        }

        System.out.print("Sort by produkt (asc/desc): ");
        String sortOrder = scanner.nextLine();

        List<Produkt> sortedProdukts = new ArrayList<>(charakter.getProdukts());
        sortedProdukts.sort(sortOrder.equalsIgnoreCase("asc") ?
                Comparator.comparing(Produkt::getHerkunftsregion) :
                Comparator.comparing(Produkt::getHerkunftsregion).reversed());

        sortedProdukts.forEach(System.out::println);
    }


    public void sortProduktsByCharakter(int charakterId, boolean ascending) {
        Charakter charakter = charakterRepo.getCharakterById(charakterId); // Obține clientul după ID
        if (charakter != null) {
            List<Produkt> produkte = charakter.getProdukts(); // Obține lista de produse ale clientului

            // Sortarea listei de produse
            if (ascending) {
                produkte.sort(Comparator.comparing(Produkt::getName)); // Sortează în ordine crescătoare
            } else {
                produkte.sort(Comparator.comparing(Produkt::getName).reversed()); // Sortează în ordine descrescătoare
            }
            System.out.println("Produkts sorted successfully!");
        } else {
            System.out.println("Charakter not found!");
        }
    }

    public void listCharaktersByProduktHerkunftsort() {
        System.out.print("Enter the herlunftsort to filter charakters by produkt: ");
        String krankheit = scanner.nextLine();

        List<Charakter> filteredCharakters = charakterRepo.getAllCharakters().stream()
                .filter(charakter -> charakter.getProdukts().stream()
                        .anyMatch(produkt -> produkt.getHerkunftsregion().equalsIgnoreCase(krankheit)))
                .collect(Collectors.toList());

        if (filteredCharakters.isEmpty()) {
            System.out.println("No charakters found with produkt for this herkunftsdorf.");
        } else {
            filteredCharakters.forEach(System.out::println);
        }
    }

    public void sortProduktsByCharakterPrice() {
        System.out.print("Enter charakter name: ");
        String charakterName = scanner.nextLine();

        Charakter charakter = charakterRepo.getAllCharakters().stream()
                .filter(p -> p.getName().equalsIgnoreCase(charakterName))
                .findFirst()
                .orElse(null);

        if (charakter == null) {
            System.out.println("Charakter not found!");
            return;
        }

        System.out.print("Sort by price (asc/desc): ");
        String sortOrder = scanner.nextLine();

        List<Produkt> sortedProdukts = new ArrayList<>(charakter.getProdukts());
        sortedProdukts.sort(sortOrder.equalsIgnoreCase("asc") ?
                Comparator.comparing(Produkt::getPreis) :
                Comparator.comparing(Produkt::getPreis).reversed());

        sortedProdukts.forEach(System.out::println);
    }


    public void run() {
        int choice;
        do {
            System.out.println("\nCharakter Management:");
            System.out.println("1. Add Charakter");
            System.out.println("2. Update Charakter");
            System.out.println("3. Delete Charakter");
            System.out.println("4. List All Charakters");
            System.out.println("5. Filter Charakters by Herkunftsdorf");
            System.out.println("6. List Charakters by Produkt Herkunftsregion");
            System.out.println("7. Sort Charakter's Products by Price");
            System.out.println("0. Exit");
            System.out.print("Enter your choice: ");
            choice = scanner.nextInt();
            scanner.nextLine(); // Consume the newline

            switch (choice) {
                case 1 -> addCharakter();
                case 2 -> updateCharakter();
                case 3 -> deleteCharakter();
                case 4 -> listAllCharakters();
                case 5 -> filterCharaktersByHerkunftsdorf();
                case 6 -> listCharaktersByProduktHerkunftsort();
                case 7 -> sortProduktsByCharakterPrice();
                case 0 -> System.out.println("Exiting Charakter Management.");
                default -> System.out.println("Invalid choice.");
            }
        } while (choice != 0);
    }

}
