Bu proje 3 farklı component'tan oluşmaktadır.

   1- config-reader
        Client'larin kendi projelerine ekleyebilecekleri kütüphanenin olduğu proje.
        Java-8, Spring-boot, Maven, MongoDb, Docker, Maven, Lombok,
   2- config-manager
        Konfigurasyon ekle/çıkar/güncelle işlemlerinin yapıldığı proje.
        Java-8, Spring-boot, MongoDb, Maven, Docker, Lombok, Freemarker template, Jquery, Bootstrap
   3- config-client
        Config-reader kullanarak konfigurasyonların okunması.
        Java-8, Spring-boot, MongoDb, Maven, Docker, Lombok kullanıldı.
        

_Nasıl çalıştırılır:_
       
Bilgisayarda java-8, maven ve docker kurulu olması gerekiyor.
       
1. config-reader projesi altından `mvn clean package` komutu ile local mvn repository'ye yüklenir.
2. config-client projesi altından `./build.sh` çalıştırılıp jar dosyası oluşturulur ve docker'a yüklenir.
3. config-manager projesi altından `./build.sh` çalıştırılıp jar dosyası oluşturulur ve docker'a yüklenir.
4. config-manager projesi altından `docker-compose up` ile config-client ve config-manager beraber ayağa kaldırılabilir.

 docker-compose.yml deki default ayarlara göre: 
 config-manager url: `http://localhost:8080`
 config-client örnek request: `http://localhost:8082/string/key1`