package BADA_dom_kultury.SpringApplication.Tables;

import java.util.Objects;

public class Pracownik_Wydarzenie {
    private int nr_pracownika;
    private int nr_wydarzenia;

    public Pracownik_Wydarzenie() {
    }

    public Pracownik_Wydarzenie(int nr_pracownika, int nr_wydarzenia) {
        super();
        this.nr_pracownika = nr_pracownika;
        this.nr_wydarzenia = nr_wydarzenia;
    }

    public int getNr_pracownika() {
        return nr_pracownika;
    }

    public void setNr_pracownika(int nr_pracownika) {
        this.nr_pracownika = nr_pracownika;
    }

    public int getNr_wydarzenia() {
        return nr_wydarzenia;
    }

    public void setNr_wydarzenia(int nr_wydarzenia) {
        this.nr_wydarzenia = nr_wydarzenia;
    }

    @Override
    public String toString() {
        return "Pracownik_Wydarzenie{" +
                "nr_pracownika=" + nr_pracownika +
                ", nr_wydarzenia=" + nr_wydarzenia +
                '}';
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Pracownik_Wydarzenie that = (Pracownik_Wydarzenie) o;
        return nr_pracownika == that.nr_pracownika && nr_wydarzenia == that.nr_wydarzenia;
    }

    @Override
    public int hashCode() {
        return Objects.hash(nr_pracownika, nr_wydarzenia);
    }
}
