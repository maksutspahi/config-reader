Bu proje 3 farklı component'tan oluşmaktadır.

   1- config-reader -> https://github.com/maksutspahi/config-reader
        Client'larin kendi projelerine ekleyebilecekleri kütüphanenin olduğu proje.
        Java-8, Spring-boot, Maven, MongoDb, Docker, Maven, Lombok kullanıldı.
   
   2- config-manager -> https://github.com/maksutspahi/config-manager
        Konfigurasyon ekle/çıkar/güncelle işlemlerinin yapıldığı proje.
        Java-8, Spring-boot, MongoDb, Maven, Docker, Lombok, Freemarker template, Jquery, Bootstrap
   
   3- config-client -> https://github.com/maksutspahi/config-client
        Config-reader kullanarak konfigurasyonların okunması.
        Reader'i kullanacak olan proje application.properties dosyasından ayarları verebilir:
                `configuration.reader.mongo.enabled:true` -> Açık veya kapalı olması
                `configuration.reader.mongo.refreshTimerIntervalInMs:1000` -> Yenileme aralığı
                `application.name:ty-client` -> Konfigurasyonun çekileceği uygulama ismi
                `spring.data.mongodb.database` -> Mongo db database ismi
                `spring.data.mongodb.uri` -> Mongo db bağlantı url'i.
        Java-8, Spring-boot, MongoDb, Maven, Docker, Lombok kullanıldı.
        

_Nasıl çalıştırılır:_
       
Proje çok basit bir şekilde birkaç saniyede çalıştırılabilir. Bilgisayarda java-8, maven ve docker kurulu olması gerekiyor.
       
1. config-reader projesi altından `mvn clean install` komutu ile local mvn repository'ye yüklenir.
2. config-client projesi altından `./build.sh` çalıştırılıp jar dosyası oluşturulur ve docker'a yüklenir.
3. config-manager projesi altından `./build.sh` çalıştırılıp jar dosyası oluşturulur ve docker'a yüklenir.
4. config-manager projesi altından `docker-compose up` ile config-client ve config-manager ve mongo database beraber ayağa kaldırılır.

 docker-compose.yml deki default ayarlara göre: 
 config-manager url: `http://localhost:8080`
 config-client örnek request: `http://localhost:8082/string/key1`
