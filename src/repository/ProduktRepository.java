package repository;

import model.Produkt;

import java.util.ArrayList;
import java.util.List;

public class ProduktRepository {
    private List<Produkt> produkte = new ArrayList<>();
    public void addProdukt(Produkt produkt) {
        produkte.add(produkt);
    }

    public void updateProdukt(Produkt produkt) {
        for (int i = 0; i < produkte.size(); i++) {
            if (produkte.get(i).getName().equals(produkt.getName())) {
                produkte.set(i, produkt);
                break;
            }
        }
    }

    public void deleteProdukt(String produktName) {
        produkte.removeIf(produkt -> produkt.getName().equals(produktName));
    }

    public List<Produkt> getAllProdukte() {
        return new ArrayList<>(produkte);
    }
}
