# ISA

Projekat iz predmeta Internet softverske arhitekture, na cetvrtoj godini smera Racunarstvo i automatika, FTN, 
predstavlja aplikaciju namenjenu za rezervaciju avionskih karata, hotelskih smestaja i vozila.

Koriscene biblioteke: *JRE System Libraries *Maven Dependencies

Neophodne instalacije za pokretanje projekta: 
1. Node.js i angular-cli
2. jdk 1.8
3. MySql baza sa odgovarajucim konektorima

Pokretanje projekta u Eclpse razvojnom okruzenju: Projekat ISA se importuje kao Existing Maven Project u ovom okruzenju, 
a klasa IsaProjectApplication.java se pokrece kao Spring Boot aplikacija. Front aplikacije se otvara kao postojeci projekat u Web Storm
okruzenju, pokrece se pomocu komande ng serve. Nakon uspesnog pokretanja Front-a i Back-a, otvara se browser na http://localhost:4200 na 
kome se nalazi prikaz pocetne stranice aplikacije. Koriscena baza je MySql, a prilikom pokretanja se ona puni podacima, pomocu skripta
data.sql koji se nalazi u resources paketu.

Clanovi tima: Student 1: Masa Djurkovic, Student 2: Dragan Borkovac, Student 3: Olga Savic

