package BADA_dom_kultury.SpringApplication.Tables;

import java.util.Objects;

public class Sprzety {
    private int nr_sprzetu;
    private String nazwa;
    private String marka;
    private String stan;
    private String opis;
    private int nr_domu_kultury;
    private int nr_typu_sprzetu;

    public Sprzety() {
    }

    public Sprzety(int nr_sprzetu, String nazwa, String marka, String stan, String opis, int nr_domu_kultury, int nr_typu_sprzetu) {
        super();
        this.nr_sprzetu = nr_sprzetu;
        this.nazwa = nazwa;
        this.marka = marka;
        this.stan = stan;
        this.opis = opis;
        this.nr_domu_kultury = nr_domu_kultury;
        this.nr_typu_sprzetu = nr_typu_sprzetu;
    }

    public int getNr_sprzetu() {
        return nr_sprzetu;
    }

    public void setNr_sprzetu(int nr_sprzetu) {
        this.nr_sprzetu = nr_sprzetu;
    }

    public String getNazwa() {
        return nazwa;
    }

    public void setNazwa(String nazwa) {
        this.nazwa = nazwa;
    }

    public String getMarka() {
        return marka;
    }

    public void setMarka(String marka) {
        this.marka = marka;
    }

    public String getStan() {
        return stan;
    }

    public void setStan(String stan) {
        this.stan = stan;
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

    public int getNr_typu_sprzetu() {
        return nr_typu_sprzetu;
    }

    public void setNr_typu_sprzetu(int nr_typu_sprzetu) {
        this.nr_typu_sprzetu = nr_typu_sprzetu;
    }

    @Override
    public String toString() {
        return "Sprzety{" +
                "nr_sprzetu=" + nr_sprzetu +
                ", nazwa='" + nazwa + '\'' +
                ", marka='" + marka + '\'' +
                ", stan='" + stan + '\'' +
                ", opis='" + opis + '\'' +
                ", nr_domu_kultury=" + nr_domu_kultury +
                ", nr_typu_sprzetu=" + nr_typu_sprzetu +
                '}';
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Sprzety sprzety = (Sprzety) o;
        return nr_sprzetu == sprzety.nr_sprzetu && nr_domu_kultury == sprzety.nr_domu_kultury && nr_typu_sprzetu == sprzety.nr_typu_sprzetu && Objects.equals(nazwa, sprzety.nazwa) && Objects.equals(marka, sprzety.marka) && Objects.equals(stan, sprzety.stan) && Objects.equals(opis, sprzety.opis);
    }

    @Override
    public int hashCode() {
        return Objects.hash(nr_sprzetu, nazwa, marka, stan, opis, nr_domu_kultury, nr_typu_sprzetu);
    }
}
