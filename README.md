# Fotoğrafçı Randevu Otomasyonu

Bu proje, fotoğrafçılar için geliştirilmiş bir randevu otomasyon sistemidir. Kullanıcılar sistem üzerinden randevu oluşturabilir ve veriler veritabanına kaydedilir. Uygulama Java Spring ve web teknolojileriyle geliştirilmiştir.

## Kullanılan Teknolojiler

- Backend: Java 17 + Spring Boot
- Frontend: HTML, CSS, JavaScript
- Veritabanı: MySQL/MariaDB (HeidiSQL ile yönetilebilir)
- Proje Yapısı: Maven

## Başlatma Talimatları

### 1. Veritabanı Kurulumu

HeidiSQL kullanarak aşağıdaki bilgileri içeren bir veritabanı oluştur:

- Veritabanı adı: `fotografci_randevu`
- Gerekli SQL örneği proje içerisinde yer almaktadır.

### 2. Uygulamayı Çalıştır

`application.properties` dosyasını aşağıdaki gibi yapılandır:

```
spring.datasource.url=jdbc:mysql://localhost:3306/fotografci_randevu
spring.datasource.username=root
spring.datasource.password=şifren
```

Uygulamayı başlatmak için:

```
./mvnw spring-boot:run
```

veya `.jar` dosyasını çalıştırmak için:

```
java -jar hedef/uygulama-adi.jar
```

### 3. Uygulamaya Erişim

Tarayıcı üzerinden aşağıdaki adrese erişerek uygulamayı kullanabilirsin:

```
http://localhost:8080
```

## Özellikler

```
Randevu oluşturma ve listeleme
Veritabanına kayıt
Web arayüzü üzerinden erişim
```

## Geliştirici

```
Developer: Ebrar Özçelik
Mail: ebrarozcelikk@gmail.com
```

## Lisans

```
Bu proje açık kaynaklıdır ve eğitim/demonstrasyon amaçlı kullanılabilir.
```
