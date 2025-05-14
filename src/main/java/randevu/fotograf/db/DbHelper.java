package randevu.fotograf.db;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Configuration;
import org.springframework.stereotype.Component;

import javax.sql.DataSource;
import java.sql.Connection;
import java.sql.SQLException;

@Component
@Configuration
public class DbHelper { //database bağlantı işlemleri yapılıyor
    @Autowired
    private DataSource dataSource; //application.properties tanımladığımız özellikleri çağırır

    public Connection getConnection() {
        try {
            return dataSource.getConnection();
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }


}
