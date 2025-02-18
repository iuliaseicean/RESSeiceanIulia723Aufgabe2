package model;

import java.util.ArrayList;
import java.util.List;

public class Charakter {
    private int id;
    private String name;
    private String herkunftsdorf;
    private List<Produkt> produkte;


    public Charakter(int id, String name, String herkunftsdorf, List<Produkt> produkte) {
        this.id = id;
        this.name = name;
        this.herkunftsdorf = herkunftsdorf;
        this.produkte = produkte != null ? produkte : new ArrayList<>();
    }

    // Getter și setter
    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getHerkunftsdorf() {
        return herkunftsdorf;
    }

    public void setHerkunftsdorf(String herkunftsdorf) {
        this.herkunftsdorf = herkunftsdorf;
    }

    public List<Produkt> getProdukts() {
        return produkte;
    }

    public void setProdukts(List<Produkt> produkte) {
        this.produkte = produkte;
    }

    @Override
    public String toString() {
        return "Charakter{id=" + id + ", name='" + name + "' +, herkunftsdorf='" + herkunftsdorf + "', produkte=" + produkte + "}";
    }
}
