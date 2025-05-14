package randevu.fotograf.dosyalar;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import randevu.fotograf.db.DbHelper;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

@RestController
@CrossOrigin(origins = "*")
@Component
public class Fotografci {
    @Autowired
    DbHelper dbHelper;


    @GetMapping("/fotografci/giris")
    public Integer giris(@RequestParam String kullaniciAdi, @RequestParam String sifre) throws SQLException {

        Connection connection = dbHelper.getConnection(); //dbhelper methodu bağlantı yapmamızı sağlıyoruz
        PreparedStatement stmt = null; //SQL ifadesini veritabanına göndermeden önce oluşturulması ve önceden derlenmesi için kullanılan bir arayüzdür.
        String sql = "SELECT id, kullanici_adi FROM fotografci where kullanici_adi = '" + kullaniciAdi + "' and sifre = '" + sifre + "'";
        stmt = connection.prepareStatement(sql);
        ResultSet rs = stmt.executeQuery();


        Integer kullaniciID = 0;
        while (rs.next()) {
           kullaniciID = rs.getInt("id");
        }

        return kullaniciID;
    }

    @GetMapping("/fotografci/kayit")
    public Boolean kayit(@RequestParam String kullaniciAdi, @RequestParam String sifre, @RequestParam String adSoyad, @RequestParam String telefon) throws SQLException {


        Connection connection = dbHelper.getConnection(); // dbHelper ile veritabanı bağlantısını alıyoruz
        PreparedStatement stmt = null; // SQL ifadelerini önceden derlemek için kullanılan bir arayüz
        String sql = "INSERT INTO fotografci (kullanici_adi, sifre, ad_soyad, telefon) VALUES (?,?,?,?)"; // INSERT SQL ifadesi
        int sonuc = 0;
        try {
            stmt = connection.prepareStatement(sql); // SQL ifadesini hazırlıyoruz
            stmt.setString(1, kullaniciAdi); // 1. parametreyi ayarla
            stmt.setString(2, sifre); // 2. parametreyi ayarla
            stmt.setString(3, adSoyad);
            stmt.setString(4, telefon);

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
}
