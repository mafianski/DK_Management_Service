package BADA_dom_kultury.SpringApplication.Tables;

import java.util.Objects;

public class Atrakcja_Sprzet {
    private int nr_atrakcji;
    private int nr_sprzetu;
    private int ilosc;

    public Atrakcja_Sprzet() {
    }

    public Atrakcja_Sprzet(int nr_atrakcji, int nr_sprzetu, int ilosc) {
        super();
        this.nr_atrakcji = nr_atrakcji;
        this.nr_sprzetu = nr_sprzetu;
        this.ilosc = ilosc;
    }

    public int getNr_atrakcji() {
        return nr_atrakcji;
    }

    public void setNr_atrakcji(int nr_atrakcji) {
        this.nr_atrakcji = nr_atrakcji;
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
        return "Atrakcja_Sprzet{" +
                "nr_atrakcji=" + nr_atrakcji +
                ", nr_sprzetu=" + nr_sprzetu +
                ", ilosc=" + ilosc +
                '}';
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Atrakcja_Sprzet that = (Atrakcja_Sprzet) o;
        return nr_atrakcji == that.nr_atrakcji && nr_sprzetu == that.nr_sprzetu && ilosc == that.ilosc;
    }

    @Override
    public int hashCode() {
        return Objects.hash(nr_atrakcji, nr_sprzetu, ilosc);
    }
}
