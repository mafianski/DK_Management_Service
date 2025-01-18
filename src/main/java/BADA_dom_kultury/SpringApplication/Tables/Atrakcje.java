package BADA_dom_kultury.SpringApplication.Tables;

import java.util.Objects;

public class Atrakcje {
    private int nr_atrakcji;
    private String nazwa;
    private int min_wiek;
    private int czas_trwania;
    private String opis;

    public Atrakcje() {
    }

    public Atrakcje(int nr_atrakcji, String nazwa, int min_wiek, int czas_trwania, String opis) {
        super();
        this.nr_atrakcji = nr_atrakcji;
        this.nazwa = nazwa;
        this.min_wiek = min_wiek;
        this.czas_trwania = czas_trwania;
        this.opis = opis;
    }

    public int getNr_atrakcji() {
        return nr_atrakcji;
    }

    public void setNr_atrakcji(int nr_atrakcji) {
        this.nr_atrakcji = nr_atrakcji;
    }

    public String getNazwa() {
        return nazwa;
    }

    public void setNazwa(String nazwa) {
        this.nazwa = nazwa;
    }

    public int getMin_wiek() {
        return min_wiek;
    }

    public void setMin_wiek(int min_wiek) {
        this.min_wiek = min_wiek;
    }

    public int getCzas_trwania() {
        return czas_trwania;
    }

    public void setCzas_trwania(int czas_trwania) {
        this.czas_trwania = czas_trwania;
    }

    public String getOpis() {
        return opis;
    }

    public void setOpis(String opis) {
        this.opis = opis;
    }

    @Override
    public String toString() {
        return "Atrakcje{" +
                "nr_atrakcji=" + nr_atrakcji +
                ", nazwa='" + nazwa + '\'' +
                ", min_wiek=" + min_wiek +
                ", czas_trwania=" + czas_trwania +
                ", opis='" + opis + '\'' +
                '}';
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Atrakcje atrakcje = (Atrakcje) o;
        return nr_atrakcji == atrakcje.nr_atrakcji && min_wiek == atrakcje.min_wiek && czas_trwania == atrakcje.czas_trwania && Objects.equals(nazwa, atrakcje.nazwa) && Objects.equals(opis, atrakcje.opis);
    }

    @Override
    public int hashCode() {
        return Objects.hash(nr_atrakcji, nazwa, min_wiek, czas_trwania, opis);
    }
}
