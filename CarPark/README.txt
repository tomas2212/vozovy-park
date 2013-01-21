Navod na spustenie:
1. Vytvorte databazu s nasledovnymi parametrami:
	url: jdbc:derby://localhost:1527/pa165
	username: pa165
	password: pa165
2. Z adresara hlavneho projektu(rovnaky adresar, kde je ulozeny tento navod) z konzoly spustite prikaz mvn clean install
3. Deploy na embed server pre jednotlive projekty treba spravit zvlast a z priecinka projektu
   
   CarParkREST:
	 mvn tomcat7:run
	 deployuje sa na 	
	 http://localhost:8090/pa165/resources/ 
   CarParkWeb:
	mvn tomcat7:run
	deployuje sa na 	
	http://localhost:8085/pa165/web/
   CarParkClient:
	mvn tomcat7:run
	deployuje sa na 	
	http://localhost:8096/pa165/client/

	klient vyuziva proxy servlet pre ajaxovu komunikaciu mimo svojho kontextu.
	defaultne je nastaveny na http://localhost:8090/pa165/resources/ V pripade poterby
	je mozne zmenit vo web.xml projektu.
