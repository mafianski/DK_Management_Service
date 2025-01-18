package BADA_dom_kultury.SpringApplication.Tables;

import java.util.Objects;

public class Uczestnik_Wydarzenie {
    private int nr_uczestnika;
    private int nr_wydarzenia;

    public Uczestnik_Wydarzenie() {
    }

    public Uczestnik_Wydarzenie(int nr_uczestnika, int nr_wydarzenia) {
        super();
        this.nr_uczestnika = nr_uczestnika;
        this.nr_wydarzenia = nr_wydarzenia;
    }

    public int getNr_uczestnika() {
        return nr_uczestnika;
    }

    public void setNr_uczestnika(int nr_uczestnika) {
        this.nr_uczestnika = nr_uczestnika;
    }

    public int getNr_wydarzenia() {
        return nr_wydarzenia;
    }

    public void setNr_wydarzenia(int nr_wydarzenia) {
        this.nr_wydarzenia = nr_wydarzenia;
    }

    @Override
    public String toString() {
        return "Uczestnik_Wydarzenie{" +
                "nr_uczestnika=" + nr_uczestnika +
                ", nr_wydarzenia=" + nr_wydarzenia +
                '}';
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Uczestnik_Wydarzenie that = (Uczestnik_Wydarzenie) o;
        return nr_uczestnika == that.nr_uczestnika && nr_wydarzenia == that.nr_wydarzenia;
    }

    @Override
    public int hashCode() {
        return Objects.hash(nr_uczestnika, nr_wydarzenia);
    }
}
