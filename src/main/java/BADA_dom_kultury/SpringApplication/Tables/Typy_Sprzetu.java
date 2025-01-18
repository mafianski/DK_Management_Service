package BADA_dom_kultury.SpringApplication.Tables;

import java.util.Objects;

public class Typy_Sprzetu {
    private int nr_typu_sprzetu;
    private String typ_sprzetu;
    private String opis;

    public Typy_Sprzetu() {
    }

    public Typy_Sprzetu(int nr_typu_sprzetu, String typ_sprzetu, String opis) {
        super();
        this.nr_typu_sprzetu = nr_typu_sprzetu;
        this.typ_sprzetu = typ_sprzetu;
        this.opis = opis;
    }

    public int getNr_typu_sprzetu() {
        return nr_typu_sprzetu;
    }

    public void setNr_typu_sprzetu(int nr_typu_sprzetu) {
        this.nr_typu_sprzetu = nr_typu_sprzetu;
    }

    public String getTyp_sprzetu() {
        return typ_sprzetu;
    }

    public void setTyp_sprzetu(String typ_sprzetu) {
        this.typ_sprzetu = typ_sprzetu;
    }

    public String getOpis() {
        return opis;
    }

    public void setOpis(String opis) {
        this.opis = opis;
    }

    @Override
    public String toString() {
        return "Typy_Sprzetu{" +
                "nr_typu_sprzetu=" + nr_typu_sprzetu +
                ", typ_sprzetu='" + typ_sprzetu + '\'' +
                ", opis='" + opis + '\'' +
                '}';
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Typy_Sprzetu that = (Typy_Sprzetu) o;
        return nr_typu_sprzetu == that.nr_typu_sprzetu && Objects.equals(typ_sprzetu, that.typ_sprzetu) && Objects.equals(opis, that.opis);
    }

    @Override
    public int hashCode() {
        return Objects.hash(nr_typu_sprzetu, typ_sprzetu, opis);
    }
}
