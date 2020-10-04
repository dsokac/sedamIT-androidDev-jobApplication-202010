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
Kratak tekst
## Korištene biblioteke
TEXT
- Retrofit
- Data Binding
- Butterknife
- Lombok
- Hilt
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
Također je potrebno otići na "File > Settings > Plugins", kliknuti na "Browse repositories...", potražiti "Lombok Plugin", instalirati ga i ponovno pokrenuti Android studio.

### Hilt
Da bi se pokrenuo projekt koji uključuje "Hilt", potrebno je imati **Android Studio 3.6. ili noviji**.
Također zahtjeva Java 8. Za detaljne upute o postavljanju pogledajte [ovdje](https://developer.android.com/training/dependency-injection/hilt-android). 
