package repository;

import model.Produkt;
import model.Charakter;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

public class CharakterRepository {
    private List<Charakter> charaktere = new ArrayList<>();

    public void addCharakter(Charakter charakter) {
        charaktere.add(charakter);
    }

    public void updateCharakter(Charakter charakter) {
        for (int i = 0; i < charaktere.size(); i++) {
            if (charaktere.get(i).getId() == charakter.getId()) {
                charaktere.set(i, charakter);
                break;
            }
        }
    }

    public void deleteCharakter(int charakterId) {
        charaktere.removeIf(charakter -> charakter.getId() == charakterId);
    }

    public List<Charakter> getAllCharakters() {
        return charaktere;
    }

    public List<Charakter> filterCharaktersByHerkunftsort(String stadt) {
        return charaktere.stream()
                .filter(c -> c.getHerkunftsdorf().equalsIgnoreCase(stadt))
                .collect(Collectors.toList());
    }

    public List<Charakter> filterCharaktersByProduktHerkunftsort(String krankheit) {
        return charaktere.stream()
                .filter(c -> c.getProdukts().stream().anyMatch(p -> p.getHerkunftsregion().equalsIgnoreCase(krankheit)))
                .collect(Collectors.toList());
    }


    public Charakter getCharakterById(int id) {
        Optional<Charakter> charakter = charaktere.stream()
                .filter(c -> c.getId() == id)
                .findFirst();
        return charakter.orElse(null);
    }
}