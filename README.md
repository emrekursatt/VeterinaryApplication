# VeterinaryApplication

**User Kullanıcı sistemde neler yapabilir ?**

•Sisteme üye olabilir ve sonrasında giriş yapabilir.
•Sisteme hayvan sahibi ve hayvan ekleme,  güncelleme gibi işlemleri yapabilir.
•Hayvan adı veya hayvan sahibi adı ile arama yapabilir ve kayıtları görüntüleyebilir.
•Sisteme kayıtlı hayvan sahibi sayısını ve hayvan sayısını görebilir.</p>
        

**Admin kullanıcı sistemde neler yapabilir** 

•User kullanıcının tüm yetkilerine sahiptir. Ancak sistemde silme rolü sadece admin kullanıcısına verilmiştir.

**Kullanılan teknolojiler:** 

Spring Boot + Spring MVC + Spring Security + JPA/Hibernate + MySQL + Maven + HTML + Thymeleaf

### UML DiAGRAMI

![UML](https://user-images.githubusercontent.com/104613938/188704952-d5bcaf47-a0b5-4783-982b-0d6ba38f1d58.PNG)

### Projeyi Çalıştırma

JPA/Hibernate ve maven ile geliştirilmiş Spring Boot projemizde herhangi bir tablo oluşturmanıza gerek yok.

Bir schema oluşturmalısınız ve oluştuduğunuz veritabanı ismini   "application.properties" dosyasındaki ##Database Configuration altında bulunan 

    spring.datasource.url=jdbc:mysql://localhost:3306/veterinary?serverTimezone=Turkey
    
    spring.datasource.username=root
    
    spring.datasource.password=12345`


**veterinary** = kısmını kendi veritabanı isminizi girmelisiniz. (?serverTimezone=Turkey bazı bilgisayalrda problem olduğu için eklenmiştir değiştirmeyiniz.)
**username** =   veritabanı username
**password** =   veritabanı şifre bilgilerinide güncelledikten sonra herşey hazır demektir.

Projeyi isterseniz herhangi bir IDE yardımıyla çalıştırabilirsiniz.

IDE üzerinden çalıştırmak istemiyorsanız eğer consol üzerinden çalıştırabilirsinz. Konsol üzerinden çalıştırmak için;

İlk olarak maven kurulumunu yapmanız gerekiyor.

[https://maven.apache.org/download.cgi ](https://maven.apache.org/download.cgi) sitesinden indirerek kurulumu gerçekleştiriniz.

Gerekli maven kurulumlarını yaptıktan sonra konsolu açınız ve 

    cd C:\***\VeterinaryApplication 

cd komutu ile projenin olduğu dosyaya erişin.Daha sonra sırasıyla aşşağıdaki komutu çalıştırarak doğru dizinde olduğunuzu doğrulayın.

    dir 

Daha sonra projeyi çalıştırmak için  önce
    
    mvn clean install

**BUILD SUCCESS** ifadesini gördükten sonra
    
    mvn spring-boot:run 

komutunu çalıştırınız ve localhost:8080 adresine giderek projeye erişebilirsiniz.


### Sistemdeki Paketler
- Config

- Controller

- Models

- Repository

- Services

- Utils
#### Modeller
- AnimalOwners ---> Hayvan Sahipelerinin Bilgileri
- Animals      ---> Hayvanların Bilgileri
- User         ---> Giriş yapabilmel için kullanıcı adı ve password bilgileri
- Role         ---> Rolname bilgisi

User ve role arasında MantyToMany ilişkisi vardır çünkü kullanıcı birden fala role sahip olabilir.
AnimalOwners ve Animals arasında OneToMany ve MantyToOne ilişkisi vardır çünkü bir hayvan sahibi n adet hayvana sahip olabilir
ama hayvanlar sadece tek bir kullanıcıya ait olabilir.

## Proje Hakkında

- Proje başlatıldıktan sonra admin yetkisine sahip admin kullanıcısı ve 
user yetkisine sahip user kullanıcısı oluşur ve sisteme yapılan kayıtlar veritabanında algoritme
ile saklanır.
    
resim ekle-userler
    
    username = admin username = user
    password = admin password = user
User sistemdeki tüm işlemleri yapabilirken ( Ekleme, çıkarma,arama,düzenleme) silme işlemini yapamamaktadır.
Admin user kullanıcısına ek olarak silme işlemide yapabilmekte.

- Sisteme default olarak Hayvan sahibi ve hayvanlar eklenmiştir.

resim ekle-ownerler
resim ekle-animallar
