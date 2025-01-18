package BADA_dom_kultury.SpringApplication.Tables;

import java.util.Objects;

public class Sale {
    private int nr_sali;
    private String nazwa;
    private String typ_sali;
    private int liczba_miejsc;
    private String opis;
    private int nr_domu_kultury;

    public Sale() {
    }

    public Sale(int nr_sali, String nazwa, String typ_sali, int liczba_miejsc, String opis, int nr_domu_kultury) {
        super();
        this.nr_sali = nr_sali;
        this.nazwa = nazwa;
        this.typ_sali = typ_sali;
        this.liczba_miejsc = liczba_miejsc;
        this.opis = opis;
        this.nr_domu_kultury = nr_domu_kultury;
    }

    public int getNr_sali() {
        return nr_sali;
    }

    public void setNr_sali(int nr_sali) {
        this.nr_sali = nr_sali;
    }

    public String getNazwa() {
        return nazwa;
    }

    public void setNazwa(String nazwa) {
        this.nazwa = nazwa;
    }

    public String getTyp_sali() {
        return typ_sali;
    }

    public void setTyp_sali(String typ_sali) {
        this.typ_sali = typ_sali;
    }

    public int getLiczba_miejsc() {
        return liczba_miejsc;
    }

    public void setLiczba_miejsc(int liczba_miejsc) {
        this.liczba_miejsc = liczba_miejsc;
    }

    public String getOpis() {
        return opis;
    }

    public void setOpis(String opis) {
        this.opis = opis;
    }

    public int getNr_domu_kultury() {
        return nr_domu_kultury;
    }

    public void setNr_domu_kultury(int nr_domu_kultury) {
        this.nr_domu_kultury = nr_domu_kultury;
    }

    @Override
    public String toString() {
        return "Sale{" +
                "nr_sali=" + nr_sali +
                ", nazwa='" + nazwa + '\'' +
                ", typ_sali='" + typ_sali + '\'' +
                ", liczba_miejsc=" + liczba_miejsc +
                ", opis='" + opis + '\'' +
                ", nr_domu_kultury=" + nr_domu_kultury +
                '}';
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Sale sale = (Sale) o;
        return nr_sali == sale.nr_sali && liczba_miejsc == sale.liczba_miejsc && nr_domu_kultury == sale.nr_domu_kultury && Objects.equals(nazwa, sale.nazwa) && Objects.equals(typ_sali, sale.typ_sali) && Objects.equals(opis, sale.opis);
    }

    @Override
    public int hashCode() {
        return Objects.hash(nr_sali, nazwa, typ_sali, liczba_miejsc, opis, nr_domu_kultury);
    }
}
