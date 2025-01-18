package BADA_dom_kultury.SpringApplication.Tables;

import java.util.Objects;

public class Wydarzenie_Atrakcja {
    private int nr_wydarzenia;
    private int nr_atrakcji;

    public Wydarzenie_Atrakcja() {
    }

    public Wydarzenie_Atrakcja(int nr_wydarzenia, int nr_atrakcji) {
        super();
        this.nr_wydarzenia = nr_wydarzenia;
        this.nr_atrakcji = nr_atrakcji;
    }

    public int getNr_wydarzenia() {
        return nr_wydarzenia;
    }

    public void setNr_wydarzenia(int nr_wydarzenia) {
        this.nr_wydarzenia = nr_wydarzenia;
    }

    public int getNr_atrakcji() {
        return nr_atrakcji;
    }

    public void setNr_atrakcji(int nr_atrakcji) {
        this.nr_atrakcji = nr_atrakcji;
    }

    @Override
    public String toString() {
        return "Wydarzenie_Atrakcja{" +
                "nr_wydarzenia=" + nr_wydarzenia +
                ", nr_atrakcji=" + nr_atrakcji +
                '}';
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Wydarzenie_Atrakcja that = (Wydarzenie_Atrakcja) o;
        return nr_wydarzenia == that.nr_wydarzenia && nr_atrakcji == that.nr_atrakcji;
    }

    @Override
    public int hashCode() {
        return Objects.hash(nr_wydarzenia, nr_atrakcji);
    }


}
