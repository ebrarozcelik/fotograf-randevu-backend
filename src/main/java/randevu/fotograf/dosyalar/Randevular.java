package randevu.fotograf.dosyalar;

import java.sql.Date;

public class Randevular {

    Integer id;

    Integer paket;

    Date tarih;

    String mekan;

    String kullaniciAdi;


    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public Integer getPaket() {
        return paket;
    }

    public void setPaket(Integer paket) {
        this.paket = paket;
    }

    public Date getTarih() {
        return tarih;
    }

    public void setTarih(Date tarih) {
        this.tarih = tarih;
    }

    public String getMekan() {
        return mekan;
    }

    public void setMekan(String mekan) {
        this.mekan = mekan;
    }

    public String getKullaniciAdi() {
        return kullaniciAdi;
    }

    public void setKullaniciAdi(String kullaniciAdi) {
        this.kullaniciAdi = kullaniciAdi;
    }
}
