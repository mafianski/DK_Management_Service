package BADA_dom_kultury.SpringApplication.Tables;

import java.util.Date;
import java.util.Objects;

public class Uczestnicy {
    private int nr_uczestnika;
    private String imie;
    private String nazwisko;
    private String data_urodzenia;
    private String telefon;
    private String email;
    private int nr_domu_kultury;

    public Uczestnicy() {
    }

    public Uczestnicy(int nr_uczestnika, String imie, String nazwisko, String data_urodzenia, String telefon, String email, int nr_domu_kultury) {
        super();
        this.nr_uczestnika = nr_uczestnika;
        this.imie = imie;
        this.nazwisko = nazwisko;
        this.data_urodzenia = data_urodzenia;
        this.telefon = telefon;
        this.email = email;
        this.nr_domu_kultury = nr_domu_kultury;
    }

    public int getNr_uczestnika() {
        return nr_uczestnika;
    }

    public void setNr_uczestnika(int nr_uczestnika) {
        this.nr_uczestnika = nr_uczestnika;
    }

    public String getImie() {
        return imie;
    }

    public void setImie(String imie) {
        this.imie = imie;
    }

    public String getNazwisko() {
        return nazwisko;
    }

    public void setNazwisko(String nazwisko) {
        this.nazwisko = nazwisko;
    }

    public String getData_urodzenia() {
        return data_urodzenia;
    }

    public void setData_urodzenia(String data_urodzenia) {
        this.data_urodzenia = data_urodzenia;
    }

    public String getTelefon() {
        return telefon;
    }

    public void setTelefon(String telefon) {
        this.telefon = telefon;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public int getNr_domu_kultury() {
        return nr_domu_kultury;
    }

    public void setNr_domu_kultury(int nr_domu_kultury) {
        this.nr_domu_kultury = nr_domu_kultury;
    }

    @Override
    public String toString() {
        return "Uczestnicy{" +
                "nr_uczestnika=" + nr_uczestnika +
                ", imie='" + imie + '\'' +
                ", nazwisko='" + nazwisko + '\'' +
                ", data_urodzenia=" + data_urodzenia +
                ", telefon='" + telefon + '\'' +
                ", email='" + email + '\'' +
                ", nr_domu_kultury=" + nr_domu_kultury +
                '}';
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Uczestnicy that = (Uczestnicy) o;
        return nr_uczestnika == that.nr_uczestnika && nr_domu_kultury == that.nr_domu_kultury && Objects.equals(imie, that.imie) && Objects.equals(nazwisko, that.nazwisko) && Objects.equals(data_urodzenia, that.data_urodzenia) && Objects.equals(telefon, that.telefon) && Objects.equals(email, that.email);
    }

    @Override
    public int hashCode() {
        return Objects.hash(nr_uczestnika, imie, nazwisko, data_urodzenia, telefon, email, nr_domu_kultury);
    }
}
