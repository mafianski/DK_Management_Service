package BADA_dom_kultury.SpringApplication.Tables;

public class Wydarzenie {
    private int Nr_wydarzenia;
    private String Nazwa;
    private String imageUrl;
    private String Data_start;
    private String Data_koniec;
    private int Liczba_miejsc;

    // Konstruktor
    public Wydarzenie(int Nr_wydarzenia, String Nazwa, String imageUrl, String Data_start, String Data_koniec, int Liczba_miejsc) {
        this.Nr_wydarzenia = Nr_wydarzenia;
        this.Nazwa = Nazwa;
        this.imageUrl = imageUrl;
        this.Data_start = Data_start;
        this.Data_koniec = Data_koniec;
        this.Liczba_miejsc = Liczba_miejsc;
    }

    // Gettery
    public int getNr_wydarzenia() {
        return Nr_wydarzenia;
    }

    public String getNazwa() {
        return Nazwa;
    }

    public String getImageUrl() {
        return imageUrl;
    }

    public String getData_start() {
        return Data_start;
    }

    public String getData_koniec() {
        return Data_koniec;
    }

    public int getLiczba_miejsc() {
        return Liczba_miejsc;
    }

    // Settery
    public void setNr_wydarzenia(int Nr_wydarzenia) {
        this.Nr_wydarzenia = Nr_wydarzenia;
    }

    public void setNazwa(String Nazwa) {
        this.Nazwa = Nazwa;
    }

    public void setImageUrl(String imageUrl) {
        this.imageUrl = imageUrl;
    }

    public void setData_start(String Data_start) {
        this.Data_start = Data_start;
    }

    public void setData_koniec(String Data_koniec) {
        this.Data_koniec = Data_koniec;
    }

    public void setLiczba_miejsc(int Liczba_miejsc) {
        this.Liczba_miejsc = Liczba_miejsc;
    }
}
