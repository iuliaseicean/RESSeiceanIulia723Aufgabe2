package model;

public class Produkt {
    private String name;
    private int preis;
    private String herkunftsregion;

    public Produkt(String name, int preis, String herkunftsort) {
        this.name = name;
        this.preis = preis;
        this.herkunftsregion = herkunftsort;
    }

    public String getName() { return name; }
    public void setName(String name) { this.name = name; }

    public int getPreis() { return preis; }
    public void setPreis(int preis) { this.preis = preis; }

    public String getHerkunftsregion() { return herkunftsregion; }
    public void setHerkunftsregion(String herkunftsregion) { this.herkunftsregion = herkunftsregion; }

    @Override
    public String toString() {
        return "Produkt{name='" + name + "', preis=" + preis + ", herkunftsregion='" + herkunftsregion +  '}';
    }
}
