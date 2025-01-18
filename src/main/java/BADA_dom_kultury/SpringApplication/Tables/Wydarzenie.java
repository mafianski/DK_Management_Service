package BADA_dom_kultury.SpringApplication.Tables;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.Date;
import java.util.Objects;

public class Wydarzenie {
    private int nr_wydarzenia;
    private String nazwa;
    private int liczba_miejsc;
    private String data_start;
    private String data_koniec;
    private int nr_sali;
    private int nr_domu_kultury;
    private String imageUrl;

    // Konstruktor
    public Wydarzenie() {
    }

    public Wydarzenie(int nr_wydarzenia, String nazwa, int liczba_miejsc, String data_start, String data_koniec, int nr_sali, int nr_domu_kultury) {
        this.nr_wydarzenia = nr_wydarzenia;
        this.nazwa = nazwa;
        this.liczba_miejsc = liczba_miejsc;
        this.data_start = data_start;
        this.data_koniec = data_koniec;
        this.nr_sali = nr_sali;
        this.nr_domu_kultury = nr_domu_kultury;
        this.imageUrl = addImageUrl(this.nazwa);
    }

    public String addImageUrl(String nazwa) {
        if(nazwa.toLowerCase().contains("koncert")) {
            return "/images/koncert.jpg";
        } else if(nazwa.toLowerCase().contains("wystawa")) {
            return "/images/wystawa.jpg";
        } else if(nazwa.toLowerCase().contains("spektakl")) {
            return "/images/spektakl.jpg";
        } else if(nazwa.toLowerCase().contains("warsztaty")) {
            return "/images/warsztaty.jpg";
        } else if(nazwa.toLowerCase().contains("spotkanie")) {
            return "/images/spotkanie.jpg";
        } else {
            return "/images/brak_zdjecia.jpg";
        }
    }

    public int getNr_wydarzenia() {
        return nr_wydarzenia;
    }

    public void setNr_wydarzenia(int nr_wydarzenia) {
        this.nr_wydarzenia = nr_wydarzenia;
    }

    public String getNazwa() {
        return nazwa;
    }

    public void setNazwa(String nazwa) {
        this.nazwa = nazwa;
    }

    public int getLiczba_miejsc() {
        return liczba_miejsc;
    }

    public void setLiczba_miejsc(int liczba_miejsc) {
        this.liczba_miejsc = liczba_miejsc;
    }

    public String getData_start() {
        return data_start;
    }

    public void setData_start(String data_start) {
        this.data_start = data_start;
    }

    public String getData_koniec() {
        return data_koniec;
    }

    public void setData_koniec(String data_koniec) {
        this.data_koniec = data_koniec;
    }

    public int getNr_sali() {
        return nr_sali;
    }

    public void setNr_sali(int nr_sali) {
        this.nr_sali = nr_sali;
    }

    public int getNr_domu_kultury() {
        return nr_domu_kultury;
    }

    public void setNr_domu_kultury(int nr_domu_kultury) {
        this.nr_domu_kultury = nr_domu_kultury;
    }

    public String getImageUrl() {
        return imageUrl;
    }

    public void setImageUrl(String imageUrl) {
        this.imageUrl = imageUrl;
    }

    @Override
    public String toString() {
        return "Wydarzenia{" +
                "nr_wydarzenia=" + nr_wydarzenia +
                ", nazwa='" + nazwa + '\'' +
                ", liczba_miejsc=" + liczba_miejsc +
                ", data_start='" + data_start + '\'' +
                ", data_koniec='" + data_koniec + '\'' +
                ", nr_sali=" + nr_sali +
                ", nr_domu_kultury=" + nr_domu_kultury +
                ", imageUrl='" + imageUrl + '\'' +
                '}';
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Wydarzenie that = (Wydarzenie) o;
        return nr_wydarzenia == that.nr_wydarzenia && liczba_miejsc == that.liczba_miejsc && nr_sali == that.nr_sali && nr_domu_kultury == that.nr_domu_kultury && Objects.equals(nazwa, that.nazwa) && Objects.equals(data_start, that.data_start) && Objects.equals(data_koniec, that.data_koniec) && Objects.equals(imageUrl, that.imageUrl);
    }

    @Override
    public int hashCode() {
        return Objects.hash(nr_wydarzenia, nazwa, liczba_miejsc, data_start, data_koniec, nr_sali, nr_domu_kultury, imageUrl);
    }
}
