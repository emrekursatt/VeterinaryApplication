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

JPA/Hibernate ve Maven ile geliştirilmiş Spring Boot projemizde herhangi bir tablo oluşturmanıza gerek yok.

Bir schema oluşturmalısınız ve oluştuduğunuz veritabanı ismini "application.properties" dosyasındaki ##Database Configuration altında bulunan;

    spring.datasource.url=jdbc:mysql://localhost:3306/veterinary?serverTimezone=Turkey
    
    spring.datasource.username=root
    
    spring.datasource.password=12345`


**veterinary** = kısmına kendi veritabanı isminizi girmelisiniz. (?serverTimezone=Turkey bazı bilgisayalarda problem olduğu için eklenmiştir,  değiştirmeyiniz.)
**username** =   veritabanı username
**password** =   veritabanı şifre bilgilerini de güncelledikten sonra her şey hazır demektir.

Projeyi isterseniz herhangi bir IDE yardımıyla çalıştırabilirsiniz.

IDE üzerinden çalıştırmak istemiyorsanız, consol üzerinden de çalıştırabilirsinz. Konsol üzerinden çalıştırmak için;

İlk olarak Maven kurulumunu yapmanız gerekiyor.

[https://maven.apache.org/download.cgi ](https://maven.apache.org/download.cgi) sitesinden indirerek kurulumu gerçekleştiriniz.

Gerekli Maven kurulumlarını yaptıktan sonra konsolu açınız ve 

    cd C:\***\VeterinaryApplication 

cd komutu ile projenin olduğu dosyaya erişin. Daha sonra sırasıyla aşağıdaki komutu çalıştırarak doğru dizinde olduğunuzu kontrol edin.

    dir 

Daha sonra projeyi çalıştırmak için  önce aşağıdaki komutu çalıştırınız
    
    mvn clean install

**BUILD SUCCESS** ifadesini gördükten sonra;
    
    mvn spring-boot:run 

komutunu çalıştırınız. localhost:8080 adresine giderek projeye erişebilirsiniz.


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
AnimalOwners ve Animals arasında OneToMany ve MantyToOne ilişkisi vardır çünkü bir hayvan sahibi n adet hayvana sahip olabilir, 
ama hayvanlar sadece tek bir kullanıcıya ait olabilir.

## Proje Hakkında
- Sisteme giriş yapmadan diğer sayfalara ulaşılamamaktadır, direkt olarak login sayfasına yönlendirir.
- Proje başlatıldıktan sonra admin yetkisine sahip admin kullanıcısı ve 
user yetkisine sahip user kullanıcısı oluşur ve sisteme yapılan tüm kayıtlar veritabanında algoritma
ile saklanır.

Bu bilgileri DataLoader'de bulabilirsiniz -->

[https://github.com/emrekursatt/VeterinaryApplication/blob/main/src/main/java/com/example/veterinaryapp/Config/DataLoader.java](url)
    
![Userler](https://user-images.githubusercontent.com/104613938/188705156-05d08853-75c8-4938-ab1c-f6022bee5826.PNG)
    
    username = admin username = user
    password = admin password = user

User sistemdeki tüm işlemleri yapabilirken ( Ekleme, çıkarma, arama, düzenleme), silme işlemini yapamamaktadır.
Admin user kullanıcısına ek olarak silme işlemini de yapabilmekte.

- Sisteme default olarak hayvan sahibi ve hayvanlar eklenmiştir.

![ownerlar](https://user-images.githubusercontent.com/104613938/188705188-abd0ba1b-9c9a-401e-8788-32abc65fe5b4.PNG)

![Animalalr](https://user-images.githubusercontent.com/104613938/188705218-ecb7852d-5766-4038-9fd3-a38ccb1839f6.PNG)

### Sistemden Ekran Görüntüleri


![AnimalList](https://user-images.githubusercontent.com/104613938/188706629-827623ea-24fd-4199-8b1d-05b203b79487.PNG)
![İndex](https://user-images.githubusercontent.com/104613938/188706633-7ee912fe-e34c-4625-8c25-97a3b714e508.PNG)
![Register](https://user-images.githubusercontent.com/104613938/188706635-fe816ec0-7940-4dad-8437-8924024da3ea.PNG)
![Login](https://user-images.githubusercontent.com/104613938/188706636-bfe71759-1d37-439a-a9ae-0c4b80efb9e3.PNG)
![Dashboard](https://user-images.githubusercontent.com/104613938/188706639-f8d49ee1-0717-46ce-8479-034f14a32f40.PNG)
![CrateOwner](https://user-images.githubusercontent.com/104613938/188706641-a1fc2472-12dd-4d28-9e2c-627ab3ef6185.PNG)
![OwnerList](https://user-images.githubusercontent.com/104613938/188706643-8ae350cd-499a-4146-997f-36e42408e27f.PNG)
