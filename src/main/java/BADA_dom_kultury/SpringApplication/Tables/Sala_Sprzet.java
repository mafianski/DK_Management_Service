package BADA_dom_kultury.SpringApplication.Tables;

import java.util.Objects;

public class Sala_Sprzet {
    private int nr_sali;
    private int nr_sprzetu;
    private int ilosc;

    public Sala_Sprzet() {
    }

    public Sala_Sprzet(int nr_sali, int nr_sprzetu, int ilosc) {
        super();
        this.nr_sali = nr_sali;
        this.nr_sprzetu = nr_sprzetu;
        this.ilosc = ilosc;
    }

    public int getNr_sali() {
        return nr_sali;
    }

    public void setNr_sali(int nr_sali) {
        this.nr_sali = nr_sali;
    }

    public int getNr_sprzetu() {
        return nr_sprzetu;
    }

    public void setNr_sprzetu(int nr_sprzetu) {
        this.nr_sprzetu = nr_sprzetu;
    }

    public int getIlosc() {
        return ilosc;
    }

    public void setIlosc(int ilosc) {
        this.ilosc = ilosc;
    }

    @Override
    public String toString() {
        return "Sala_Sprzet{" +
                "nr_sali=" + nr_sali +
                ", nr_sprzetu=" + nr_sprzetu +
                ", ilosc=" + ilosc +
                '}';
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Sala_Sprzet that = (Sala_Sprzet) o;
        return nr_sali == that.nr_sali && nr_sprzetu == that.nr_sprzetu && ilosc == that.ilosc;
    }

    @Override
    public int hashCode() {
        return Objects.hash(nr_sali, nr_sprzetu, ilosc);
    }
}
