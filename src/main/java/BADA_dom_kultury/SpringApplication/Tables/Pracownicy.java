package BADA_dom_kultury.SpringApplication.Tables;

import java.util.Date;
import java.util.Objects;

public class Pracownicy {
    private int nr_pracownika;
    private String imie;
    private String nazwisko;
    private String data_urodzenia;
    private String email;
    private String telefon;
    private String pesel;
    private String plec;
    private int nr_domu_kultury;
    private int nr_adresu;
    private int nr_stanowiska;

    public Pracownicy() {
    }

    public Pracownicy(int nr_pracownika, String imie, String nazwisko, String data_urodzenia, String email, String telefon, String pesel, String plec, int nr_domu_kultury, int nr_adresu, int nr_stanowiska) {
        super();
        this.nr_pracownika = nr_pracownika;
        this.imie = imie;
        this.nazwisko = nazwisko;
        this.data_urodzenia = data_urodzenia;
        this.email = email;
        this.telefon = telefon;
        this.pesel = pesel;
        this.plec = plec;
        this.nr_domu_kultury = nr_domu_kultury;
        this.nr_adresu = nr_adresu;
        this.nr_stanowiska = nr_stanowiska;
    }

    public int getNr_pracownika() {
        return nr_pracownika;
    }

    public void setNr_pracownika(int nr_pracownika) {
        this.nr_pracownika = nr_pracownika;
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

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getTelefon() {
        return telefon;
    }

    public void setTelefon(String telefon) {
        this.telefon = telefon;
    }

    public String getPesel() {
        return pesel;
    }

    public void setPesel(String pesel) {
        this.pesel = pesel;
    }

    public String getPlec() {
        return plec;
    }

    public void setPlec(String plec) {
        this.plec = plec;
    }

    public int getNr_domu_kultury() {
        return nr_domu_kultury;
    }

    public void setNr_domu_kultury(int nr_domu_kultury) {
        this.nr_domu_kultury = nr_domu_kultury;
    }

    public int getNr_adresu() {
        return nr_adresu;
    }

    public void setNr_adresu(int nr_adresu) {
        this.nr_adresu = nr_adresu;
    }

    public int getNr_stanowiska() {
        return nr_stanowiska;
    }

    public void setNr_stanowiska(int nr_stanowiska) {
        this.nr_stanowiska = nr_stanowiska;
    }

    @Override
    public String toString() {
        return "Pracownicy{" +
                "nr_pracownika=" + nr_pracownika +
                ", imie='" + imie + '\'' +
                ", nazwisko='" + nazwisko + '\'' +
                ", data_urodzenia=" + data_urodzenia +
                ", email='" + email + '\'' +
                ", telefon='" + telefon + '\'' +
                ", pesel='" + pesel + '\'' +
                ", plec=" + plec +
                ", nr_domu_kultury=" + nr_domu_kultury +
                ", nr_adresu=" + nr_adresu +
                ", nr_stanowiska=" + nr_stanowiska +
                '}';
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Pracownicy that = (Pracownicy) o;
        return nr_pracownika == that.nr_pracownika && plec == that.plec && nr_domu_kultury == that.nr_domu_kultury && nr_adresu == that.nr_adresu && nr_stanowiska == that.nr_stanowiska && Objects.equals(imie, that.imie) && Objects.equals(nazwisko, that.nazwisko) && Objects.equals(data_urodzenia, that.data_urodzenia) && Objects.equals(email, that.email) && Objects.equals(telefon, that.telefon) && Objects.equals(pesel, that.pesel);
    }

    @Override
    public int hashCode() {
        return Objects.hash(nr_pracownika, imie, nazwisko, data_urodzenia, email, telefon, pesel, plec, nr_domu_kultury, nr_adresu, nr_stanowiska);
    }
}
