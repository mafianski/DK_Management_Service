package BADA_dom_kultury.SpringApplication.Tables;

import java.util.Objects;

public class Adresy {
    private int nr_adresu;
    private String miasto;
    private String ulica;
    private String nr_budynku;
    private String nr_lokalu;
    private int nr_poczty;

    public Adresy() {
    }

    public Adresy(int nr_adresu, String miasto, String ulica, String nr_budynku, String nr_lokalu, int nr_poczty) {
        super();
        this.nr_adresu = nr_adresu;
        this.miasto = miasto;
        this.ulica = ulica;
        this.nr_budynku = nr_budynku;
        this.nr_lokalu = nr_lokalu;
        this.nr_poczty = nr_poczty;
    }

    public int getNr_adresu() {
        return nr_adresu;
    }

    public void setNr_adresu(int nr_adresu) {
        this.nr_adresu = nr_adresu;
    }

    public String getMiasto() {
        return miasto;
    }

    public void setMiasto(String miasto) {
        this.miasto = miasto;
    }

    public String getUlica() {
        return ulica;
    }

    public void setUlica(String ulica) {
        this.ulica = ulica;
    }

    public String getNr_budynku() {
        return nr_budynku;
    }

    public void setNr_budynku(String nr_budynku) {
        this.nr_budynku = nr_budynku;
    }

    public String getNr_lokalu() {
        return nr_lokalu;
    }

    public void setNr_lokalu(String nr_lokalu) {
        this.nr_lokalu = nr_lokalu;
    }

    public int getNr_poczty() {
        return nr_poczty;
    }

    public void setNr_poczty(int nr_poczty) {
        this.nr_poczty = nr_poczty;
    }

    @Override
    public String toString() {
        return "Adresy{" +
                "nr_adresu=" + nr_adresu +
                ", miasto='" + miasto + '\'' +
                ", ulica='" + ulica + '\'' +
                ", nr_budynku='" + nr_budynku + '\'' +
                ", nr_lokalu='" + nr_lokalu + '\'' +
                ", nr_poczty=" + nr_poczty +
                '}';
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Adresy adresy = (Adresy) o;
        return nr_adresu == adresy.nr_adresu && nr_poczty == adresy.nr_poczty && Objects.equals(miasto, adresy.miasto) && Objects.equals(ulica, adresy.ulica) && Objects.equals(nr_budynku, adresy.nr_budynku) && Objects.equals(nr_lokalu, adresy.nr_lokalu);
    }

    @Override
    public int hashCode() {
        return Objects.hash(nr_adresu, miasto, ulica, nr_budynku, nr_lokalu, nr_poczty);
    }
}
