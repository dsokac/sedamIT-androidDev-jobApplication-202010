# sedamIT-androidDev-jobApplication-202010
The task for job application at company SedamIT. The position is Android developer. The task will be written in ReadMe file.

## Naziv zadatka:

Aplikacija za upravljanje osobnim podacima (**Bilješke nakon rješavanja su na dnu.**)

## Opis  zadatka:

Implementirati  Android aplikaciju za upravljanje osobnim podacima. Aplikacija kroz sučelje treba moći podržati  unos sljedećih osobnih  podataka:

- Ime, prezime
- Telefon, Mobitel
- Mail
- Adresa

Nadalje, aplikacija preko API sučelja mora pružiti mogućnost pregleda, izmjena i brisanja postojećih podataka te unos novih.

U tu svrhu je  potrebno kreirati mock API s inicijalnim testnim setom podataka. Kod dizajna API sučelja () koristiti standarde:
- Dohvat liste: GET [url]/osobe
- Dohvat osobe: GET [url]/osobe/123
- Kreiranje osobe/ažuriranje postojeće: POST (ili PUT) [url]/osobe
- Brisanje osobe: DELETE [url]/osobe/123,
itd

## Očekivanja:

- Isporuka koda treba biti upotpunjena uputama za pokretanje sustava, kao i načinom testiranja istog.

- Prilikom analize rješenja zadatka obratit ćemo pozornost na uloženi angažman te kvalitetu koda, naravno, u skladu sa stupnjem obrazovanja kandidata

- Prilikom razvoja  Android aplikacije koristiti Retrofit i JetPack library (Room, Data Binding… ). Navesti libove koji su se koristili u razvoju.

## Rok izrade:
6.10.2020.

----------------------------------------------------------------------------------------------------------------------------------------------------------------------------

# Nakon rješavanja
Zadatak sam krenuo riješavati u nedjelju navečer te uzimajući u obzir da imam još zaostataka iz posla za riješiti nisam u mogućnosti bio odmah se baciti na Android. Nadam se da će ovaj kod biti dovoljan za representaciju moje programerske vještine.
## Korištene biblioteke
U projektu sam koristio sljedeće Biblioteke:
- Retrofit
- Data Binding
- Butterknife
- Lombok
- Material (Google - GUI)
- RecyclerView
- Room
- (Hilt) - planirao sam implementirati dependency injection al sam odustao kada sam vidio da moram ažurirati Android Studio, tako da to nije korišteno u kodu
### Butterknife
Butterknife zahtjeva Java 8. Koristi se za dohvat "view"-ova bez dohvata po ID-u te omogućava lakše manipuliranje "view"-ovima.
```
sourceCompatibility JavaVersion.VERSION_1_8
targetCompatibility JavaVersion.VERSION_1_8
```
### Lombok 
Lombok se koristi za generiranje "getter"-a i "setter"-a.
Kako bi se osiguralo da Lombok radi u Gradle-u, potrebno je osigurati sljedeće:
- verzija gradle-a bi trebala biti veća ili jednaka od 0.4.3

**Da bi se moglo gledati projekt sa Lombokom** potrebno je otići na "File > Settings > Plugins", kliknuti na "Browse repositories...", potražiti "Lombok Plugin", instalirati ga i ponovno pokrenuti Android studio.

## Kako pokrenuti projekt
(1) Prije samog pokretanja projekta, zatvoriti postojeći i otići na "Configure > Settings > Build, Execution, Deployment > Compiler > Annotation Processors" i odabrati **Enable annotation processing** i potvrditi sa OK.
(2) Otvoriti projekt iz repozitorija i pričekati da se odrade pozadinski zadaci.
(3) Jedino je kritična biblioteka **Lombok** te bi trebalo popratiti upute koje sam naveo za pregledavanje projekta sa Lombok-om. Ako postoji verzija lomobooka potrebno ju je možda ažurirati. (Ja sam ažurirao i imam v0.32-2018.3). Indikacija da je problem ovaj plugin je popis grešaka koje ne mogu naći gettere i settere.
(4) Aplikacija trenutno ne podržava offline pristup. Samo online.

## Problemi koje sam imao u projektu
U projektu nisam mogao realizirati *two-way binding* koristeći Data Binding biblioteku. Uspio sam složiti samo da se učitava na layout, ali nisam uspio dohvatiti promijenjene podatke. Zbog raspoloživog vremena sam odlučio ostaviti samo bindanje u jednom smjeru. Data Binding se koristi u DijalogFragment-ima.

## Poboljšanja
U nastavku ću vam navesti sva poboljšanja koja sam planirao implementirati (i koja vjerojatno budem implementirao u bližoj budućnosti):
- Prikazati poruku da nema podataka kada nije učitan ni jedan podatak.
- Kada nema podataka i interneta, da nije moguće dohvatiti podatke sa interneta bez internet veze.
- Kada ima podataka u bazi i nema interneta kada korisnik dodaje, ažurira i briše, da se provjerava BroadcastReceiver koji će čekati da dođe internet i pokrenuti sinkronizaciju
- Kada korisnik dodaje, briše ili ažurira lokalno, svaka od tih operacija uključuje spremanje u bazu i nakon toga spremanje na API
- Kada korisnik dohvaća podatke, dohvaća ih isključivo lokalno iz baze podataka
- Ako nema podataka u bazi, podaci se dohvaćaju sa interneta i spremaju u bazu. Prikazuju se podaci dohvaćeni sa API, ali samo ovaj puta.
- Implementirati "select more" na način da dugi dodir na jednu osobu otvori mogućnost za označiti više njih. I onda se floating button bi pretvorio u "X" te bi služio brisanju označenih zapisa.
- Implementirati "select all" koji bi se složio kao dugi dodir na osobu na kojoj je već uključena mogućnost odabira više stavki. Otvorio bi se kao kontekstualni izbornik u kojem bi bila jedna opcija "Odaberi sve". 
- Implementacija paginacije za dohvat podataka
- Implementacija sinkronizacije sa sljedećim algoritmom:
```
Provjeravaju se vrijednosti ID  polja za prvi i zadnji element u listi
Ako su vrijednosti različite, onda:
          Uspoređuj redom elemente dok ne nađeš različiti
          Ako si našao različiti:
                Zapamti indeks na kojem su različiti
                Ako su različite vrijednosti ID-a:
                        Obriši sve zapise u listi gdje je manja vrijednost ID-a do veće vrijednosti ID-a iz druge liste (ako je jedna lista 1, 2, 3, 4, 5 i druga 1, 2, 7, 8,9 jasno je da će trebati listu gdje je 3 uređivati :)
                              Ako brišeš lokalne zapise, briši ih iz baze
                              Ako brišeš online zapise, briši ih iz API-ja
                        Dohvati onoliko elemenata koliko treba da obje liste budu iste
                 Ako su iste vrijednosti ID-a i razlikuju u sadržaju:
                      Ako je moguće spojiti sve promjene u jedno, inače prioritet ima API.
           Inače:
             Dohvati slijedeću skupinu elemenata za uspoređivanje
Inače:
          Dohvati slijedeću skupinu elemenata za uspoređivanje
Ako ima još elemenata za uspoređivanje, ponovi sve korake
Inače završi
          
```  
