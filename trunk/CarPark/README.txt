Navod na spustenie:
1. Vytvorte databazu s nasledovnymi parametrami:
	url: jdbc:derby://localhost:1527/pa165
	username: pa165
	password: pa165
2. Z adresara hlavneho projektu(rovnaky adresar kde je ulezeny tento navod) z konzoly spustite prikaz mvn clean install
3. Z adresara hlavneho projektu(rovnaky adresar kde je ulezeny tento navod) z konzoly spustite prikaz mvn cargo:start
   Nasledovny prikaz spusti jetty server spolu s nasledovnymi webovymi aplikaciami
   CarParkREST: http://localhost:8080/pa165/resources/ 
   CarParkWeb: http://localhost:8080/pa165/web/	
   CarParkClient: http://localhost:8080/pa165/client/ 
