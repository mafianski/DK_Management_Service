package BADA_dom_kultury.SpringApplication.Tables;

import java.util.Objects;

public class Stanowiska {
    private int nr_stanowiska;
    private String nazwa;
    private String opis;

    public Stanowiska() {
    }

    public Stanowiska(int nr_stanowiska, String nazwa, String opis) {
        super();
        this.nr_stanowiska = nr_stanowiska;
        this.nazwa = nazwa;
        this.opis = opis;
    }

    public int getNr_stanowiska() {
        return nr_stanowiska;
    }

    public void setNr_stanowiska(int nr_stanowiska) {
        this.nr_stanowiska = nr_stanowiska;
    }

    public String getNazwa() {
        return nazwa;
    }

    public void setNazwa(String nazwa) {
        this.nazwa = nazwa;
    }

    public String getOpis() {
        return opis;
    }

    public void setOpis(String opis) {
        this.opis = opis;
    }

    @Override
    public String toString() {
        return "Stanowiska{" +
                "nr_stanowiska=" + nr_stanowiska +
                ", nazwa='" + nazwa + '\'' +
                ", opis='" + opis + '\'' +
                '}';
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Stanowiska that = (Stanowiska) o;
        return nr_stanowiska == that.nr_stanowiska && Objects.equals(nazwa, that.nazwa) && Objects.equals(opis, that.opis);
    }

    @Override
    public int hashCode() {
        return Objects.hash(nr_stanowiska, nazwa, opis);
    }
}
