package BADA_dom_kultury.SpringApplication.Tables;

import java.util.Objects;

public class Domy_Kultury {
    private int nr_domu_kultury;
    private String nazwa;
    private String wlasciciel;
    private int nr_adresu;

    public Domy_Kultury() {
    }

    public Domy_Kultury(int nr_domu_kultury, String nazwa, String wlasciciel, int nr_adresu) {
        super();
        this.nr_domu_kultury = nr_domu_kultury;
        this.nazwa = nazwa;
        this.wlasciciel = wlasciciel;
        this.nr_adresu = nr_adresu;
    }

    public int getNr_domu_kultury() {
        return nr_domu_kultury;
    }

    public void setNr_domu_kultury(int nr_domu_kultury) {
        this.nr_domu_kultury = nr_domu_kultury;
    }

    public String getNazwa() {
        return nazwa;
    }

    public void setNazwa(String nazwa) {
        this.nazwa = nazwa;
    }

    public String getWlasciciel() {
        return wlasciciel;
    }

    public void setWlasciciel(String wlasciciel) {
        this.wlasciciel = wlasciciel;
    }

    public int getNr_adresu() {
        return nr_adresu;
    }

    public void setNr_adresu(int nr_adresu) {
        this.nr_adresu = nr_adresu;
    }

    @Override
    public String toString() {
        return "Domy_Kultury{" +
                "nr_domu_kultury=" + nr_domu_kultury +
                ", nazwa='" + nazwa + '\'' +
                ", wlasciciel='" + wlasciciel + '\'' +
                ", nr_adresu=" + nr_adresu +
                '}';
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Domy_Kultury that = (Domy_Kultury) o;
        return nr_domu_kultury == that.nr_domu_kultury && nr_adresu == that.nr_adresu && Objects.equals(nazwa, that.nazwa) && Objects.equals(wlasciciel, that.wlasciciel);
    }

    @Override
    public int hashCode() {
        return Objects.hash(nr_domu_kultury, nazwa, wlasciciel, nr_adresu);
    }
}
