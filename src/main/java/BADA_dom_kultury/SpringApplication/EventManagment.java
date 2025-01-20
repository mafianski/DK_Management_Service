package BADA_dom_kultury.SpringApplication;

public class EventManagment {
    private int nr_wydarzenia;
    private String nazwa;
    private int liczba_miejsc;
    private String data_start;
    private String data_koniec;
    private String nazwa_sali;
    private int nr_domu_kultury;
    private int liczba_zapisanych;

    public EventManagment() {
    }

    public EventManagment(int nr_wydarzenia, String nazwa, int liczba_miejsc, String data_start, String data_koniec, String nazwa_sali, int nr_domu_kultury, int liczba_zapisanych) {
        super();
        this.nr_wydarzenia = nr_wydarzenia;
        this.nazwa = nazwa;
        this.liczba_miejsc = liczba_miejsc;
        this.data_start = data_start;
        this.data_koniec = data_koniec;
        this.nazwa_sali = nazwa_sali;
        this.nr_domu_kultury = nr_domu_kultury;
        this.liczba_zapisanych = liczba_zapisanych;
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

    public String getNazwa_sali() {
        return nazwa_sali;
    }

    public void setNazwa_sali(String nazwa_sali) {
        this.nazwa_sali = nazwa_sali;
    }

    public int getNr_domu_kultury() {
        return nr_domu_kultury;
    }

    public void setNr_domu_kultury(int nr_domu_kultury) {
        this.nr_domu_kultury = nr_domu_kultury;
    }

    public int getLiczba_zapisanych() {
        return liczba_zapisanych;
    }

    public void setLiczba_zapisanych(int liczba_zapisanych) {
        this.liczba_zapisanych = liczba_zapisanych;
    }
}
