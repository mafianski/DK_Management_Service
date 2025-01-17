package BADA_dom_kultury.SpringApplication.Tables;

import java.util.Objects;

public class Poczty {
    private int nr_poczty;
    private String poczta;
    private String kod_pocztowy;

    public Poczty() {
    }

    public Poczty(int nr_poczty, String poczta, String kod_pocztowy) {
        super();
        this.nr_poczty = nr_poczty;
        this.poczta = poczta;
        this.kod_pocztowy = kod_pocztowy;
    }

    public int getNr_poczty() {
        return nr_poczty;
    }

    public void setNr_poczty(int nr_poczty) {
        this.nr_poczty = nr_poczty;
    }

    public String getPoczta() {
        return poczta;
    }

    public void setPoczta(String poczta) {
        this.poczta = poczta;
    }

    public String getKod_pocztowy() {
        return kod_pocztowy;
    }

    public void setKod_pocztowy(String kod_pocztowy) {
        this.kod_pocztowy = kod_pocztowy;
    }

    @Override
    public String toString() {
        return "Poczty{" +
                "nrPoczty=" + nr_poczty +
                ", poczta='" + poczta + '\'' +
                ", kodPocztowy='" + kod_pocztowy + '\'' +
                '}';
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Poczty poczty = (Poczty) o;
        return nr_poczty == poczty.nr_poczty && Objects.equals(poczta, poczty.poczta) && Objects.equals(kod_pocztowy, poczty.kod_pocztowy);
    }

    @Override
    public int hashCode() {
        return Objects.hash(nr_poczty, poczta, kod_pocztowy);
    }
}
