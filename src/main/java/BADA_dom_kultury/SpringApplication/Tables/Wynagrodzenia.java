package BADA_dom_kultury.SpringApplication.Tables;

import java.math.BigDecimal;
import java.util.Date;
import java.util.Objects;

public class Wynagrodzenia {
    private int nr_wynagrodzenia;
    private Date data;
    private BigDecimal kwota_brutto;
    private BigDecimal kwota_netto;
    private int nr_pracownika;

    public Wynagrodzenia() {
    }

    public Wynagrodzenia(int nr_wynagrodzenia, Date data, BigDecimal kwota_brutto, BigDecimal kwota_netto, int nr_pracownika) {
        super();
        this.nr_wynagrodzenia = nr_wynagrodzenia;
        this.data = data;
        this.kwota_brutto = kwota_brutto;
        this.kwota_netto = kwota_netto;
        this.nr_pracownika = nr_pracownika;
    }

    public int getNr_wynagrodzenia() {
        return nr_wynagrodzenia;
    }

    public void setNr_wynagrodzenia(int nr_wynagrodzenia) {
        this.nr_wynagrodzenia = nr_wynagrodzenia;
    }

    public Date getData() {
        return data;
    }

    public void setData(Date data) {
        this.data = data;
    }

    public BigDecimal getKwota_brutto() {
        return kwota_brutto;
    }

    public void setKwota_brutto(BigDecimal kwota_brutto) {
        this.kwota_brutto = kwota_brutto;
    }

    public BigDecimal getKwota_netto() {
        return kwota_netto;
    }

    public void setKwota_netto(BigDecimal kwota_netto) {
        this.kwota_netto = kwota_netto;
    }

    public int getNr_pracownika() {
        return nr_pracownika;
    }

    public void setNr_pracownika(int nr_pracownika) {
        this.nr_pracownika = nr_pracownika;
    }

    @Override
    public String toString() {
        return "Wynagrodzenia{" +
                "nr_wynagrodzenia=" + nr_wynagrodzenia +
                ", data=" + data +
                ", kwota_brutto=" + kwota_brutto +
                ", kwota_netto=" + kwota_netto +
                ", nr_pracownika=" + nr_pracownika +
                '}';
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Wynagrodzenia that = (Wynagrodzenia) o;
        return nr_wynagrodzenia == that.nr_wynagrodzenia && nr_pracownika == that.nr_pracownika && Objects.equals(data, that.data) && Objects.equals(kwota_brutto, that.kwota_brutto) && Objects.equals(kwota_netto, that.kwota_netto);
    }

    @Override
    public int hashCode() {
        return Objects.hash(nr_wynagrodzenia, data, kwota_brutto, kwota_netto, nr_pracownika);
    }

}
