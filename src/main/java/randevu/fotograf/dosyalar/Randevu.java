package randevu.fotograf.dosyalar;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import randevu.fotograf.db.DbHelper;

import java.sql.*;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

@RestController
@CrossOrigin(origins = "*")
@Component
public class Randevu {

    @Autowired
    DbHelper dbHelper;



    @GetMapping("/randevular")
    public List<Randevular> randevulariGetir() throws SQLException {


        List<Randevular> randevular = new ArrayList<>();
        try {

            Connection connection = dbHelper.getConnection(); //dbhelper methodu bağlantı yapmamızı sağlıyoruz
            PreparedStatement stmt = null; //SQL ifadesini veritabanına göndermeden önce oluşturulması ve önceden derlenmesi için kullanılan bir arayüzdür.
            String sql = "SELECT * FROM randevu";
            stmt = connection.prepareStatement(sql);
            ResultSet rs = stmt.executeQuery();


            while (rs.next()) {

                Randevular rand = new Randevular();
                rand.setId(rs.getInt("id"));
                rand.setPaket(rs.getInt("paket"));
                rand.setMekan(rs.getString("mekan"));
                rand.setKullaniciAdi(rs.getString("kullanici_adi"));
                rand.setTarih(rs.getDate("tarih"));
                randevular.add(rand);
            }

            // Kaynakları temizle
            rs.close();
            stmt.close();
            connection.close();
        } catch (SQLException e) {
            e.printStackTrace();
        }

        return randevular;
    }

    @GetMapping("/randevu-ekle")
    public Boolean randevuEkle(@RequestParam Integer paket, @RequestParam LocalDate tarih, @RequestParam String mekan, @RequestParam String kullaniciAdi) throws SQLException {


        Connection connection = dbHelper.getConnection(); // dbHelper ile veritabanı bağlantısını alıyoruz
        PreparedStatement stmt = null; // SQL ifadelerini önceden derlemek için kullanılan bir arayüz
        String sql = "INSERT INTO randevu (paket, tarih, mekan, kullanici_adi) VALUES (?,?,?,?)"; // INSERT SQL ifadesi
        int sonuc = 0;
        try {
            stmt = connection.prepareStatement(sql); // SQL ifadesini hazırlıyoruz
            stmt.setInt(1, paket); // 1. parametreyi ayarla
            stmt.setDate(2, Date.valueOf(tarih)); // 2. parametreyi ayarla
            stmt.setString(3, mekan);
            stmt.setString(4, kullaniciAdi);

            sonuc = stmt.executeUpdate(); // INSERT işlemini gerçekleştiriyoruz

        } catch (SQLException e) {
            e.printStackTrace(); // Hata yakalama ve hata mesajını görüntüleme
        } finally {
            if (stmt != null) {
                try {
                    stmt.close(); // PreparedStatement'i kapatma
                } catch (SQLException e) {
                    e.printStackTrace();
                }
            }
            if (connection != null) {
                try {
                    connection.close(); // Bağlantıyı kapatma
                } catch (SQLException e) {
                    e.printStackTrace();
                }
            }
        }

        return sonuc > 0;
    }


    @GetMapping("/randevu-guncelle")
    public Boolean randevuGuncelle(@RequestParam Integer id, @RequestParam Integer paket, @RequestParam LocalDate tarih, @RequestParam String mekan) throws SQLException {


        Connection connection = dbHelper.getConnection(); // dbHelper ile veritabanı bağlantısını alıyoruz
        PreparedStatement stmt = null; // SQL ifadelerini önceden derlemek için kullanılan bir arayüz
        String sql = "UPDATE randevu  SET paket = ?, tarih= ?, mekan = ? where id = ?"; // INSERT SQL ifadesi
        int sonuc = 0;
        try {
            stmt = connection.prepareStatement(sql); // SQL ifadesini hazırlıyoruz
            stmt.setInt(1, paket); // 1. parametreyi ayarla
            stmt.setDate(2, Date.valueOf(tarih)); // 2. parametreyi ayarla
            stmt.setString(3, mekan);
            stmt.setInt(4, id);

            sonuc = stmt.executeUpdate(); // update işlemini gerçekleştiriyoruz

        } catch (SQLException e) {
            e.printStackTrace(); // Hata yakalama ve hata mesajını görüntüleme
        } finally {
            if (stmt != null) {
                try {
                    stmt.close(); // PreparedStatement'i kapatma
                } catch (SQLException e) {
                    e.printStackTrace();
                }
            }
            if (connection != null) {
                try {
                    connection.close(); // Bağlantıyı kapatma
                } catch (SQLException e) {
                    e.printStackTrace();
                }
            }
        }

        return sonuc > 0;
    }


    @GetMapping("/randevu-sil")
    public Boolean randevuSil(@RequestParam Integer paket, @RequestParam String kullaniciAdi) throws SQLException {


        Connection connection = dbHelper.getConnection(); // dbHelper ile veritabanı bağlantısını alıyoruz
        PreparedStatement stmt = null; // SQL ifadelerini önceden derlemek için kullanılan bir arayüz
        String sql = "DELETE FROM randevu WHERE kullanici_adi = ? AND paket = ?"; // INSERT SQL ifadesi
        int sonuc = 0;
        try {
            stmt = connection.prepareStatement(sql); // SQL ifadesini hazırlıyoruz
            stmt.setString(1, kullaniciAdi); // 1. parametreyi ayarla
            stmt.setInt(2, paket);

            sonuc = stmt.executeUpdate(); // update işlemini gerçekleştiriyoruz

        } catch (SQLException e) {
            e.printStackTrace(); // Hata yakalama ve hata mesajını görüntüleme
        } finally {
            if (stmt != null) {
                try {
                    stmt.close(); // PreparedStatement'i kapatma
                } catch (SQLException e) {
                    e.printStackTrace();
                }
            }
            if (connection != null) {
                try {
                    connection.close(); // Bağlantıyı kapatma
                } catch (SQLException e) {
                    e.printStackTrace();
                }
            }
        }

        return sonuc> 0;
    }
}
