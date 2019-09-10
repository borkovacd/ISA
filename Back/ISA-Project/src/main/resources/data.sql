insert into isa_project.korisnik (id, email, grad, ime, korisnicko_ime, lozinka, prezime, prvo_logovanje, telefon, verifikovan, uloga, status_korisnika) 
	values (1, "admin@gmail.com", "Novi Sad", "Admin",  "admin", "$2a$10$bFoT0UWjOFAIQoFRYCIicO2hwNwZy6qhWYq4eXmWqJevf7b2TWpae", "Admin", false, "0600000000", true, "ADMINISTRATOR_SISTEMA", "ok");
insert into isa_project.korisnik (id, email, grad, ime, korisnicko_ime, lozinka, prezime, prvo_logovanje, telefon, verifikovan, uloga, status_korisnika) 
	values (2, "borkovac.dragan@gmail.com", "Novi Sad", "Dragan",  "dragan", "$2a$10$bFoT0UWjOFAIQoFRYCIicO2hwNwZy6qhWYq4eXmWqJevf7b2TWpae", "Borkovac", false, "0605959769", true, "ADMINISTRATOR_HOTELA", "ok");
insert into isa_project.korisnik (id, email, grad, ime, korisnicko_ime, lozinka, prezime, prvo_logovanje, telefon, verifikovan, uloga, status_korisnika) 
	values (3, "savic.olga@gmail.com", "Novi Sad", "Olga",  "olga", "$2a$10$bFoT0UWjOFAIQoFRYCIicO2hwNwZy6qhWYq4eXmWqJevf7b2TWpae", "Savic", false, "06345624543", true, "ADMINISTRATOR_RENT_A_CAR", "ok");
insert into isa_project.korisnik (id, email, grad, ime, korisnicko_ime, lozinka, prezime, prvo_logovanje, telefon, verifikovan, uloga, status_korisnika) 
	values (4, "djurkovic.masa@gmail.com", "Novi Sad", "Masa",  "masa", "$2a$10$bFoT0UWjOFAIQoFRYCIicO2hwNwZy6qhWYq4eXmWqJevf7b2TWpae", "Djurkovic", false, "0604561231", true, "OBICAN_KORISNIK", "ok");
insert into isa_project.korisnik (id, email, grad, ime, korisnicko_ime, lozinka, prezime, prvo_logovanje, telefon, verifikovan, uloga, status_korisnika) 
	values (5, "petrovic.petar@gmail.com", "Beograd", "Petar",  "petar", "$2a$10$bFoT0UWjOFAIQoFRYCIicO2hwNwZy6qhWYq4eXmWqJevf7b2TWpae", "Petrovic", false, "0604561231", true, "ADMINISTRATOR_HOTELA", "ok");
insert into isa_project.korisnik (id, email, grad, ime, korisnicko_ime, lozinka, prezime, prvo_logovanje, telefon, verifikovan, uloga, status_korisnika) 
	values (6, "pekic.peki@gmail.com", "Novi Sad", "Peki",  "peki", "$2a$10$bFoT0UWjOFAIQoFRYCIicO2hwNwZy6qhWYq4eXmWqJevf7b2TWpae", "Pekic", false, "0668811810", true, "ADMINISTRATOR_RENT_A_CAR", "ok");
insert into isa_project.korisnik (id, email, grad, ime, korisnicko_ime, lozinka, prezime, prvo_logovanje, telefon, verifikovan, uloga, status_korisnika) 
	values (7, "anic.ana@gmai.com", "Sremska Mitrovica", "Ana",  "ana", "$2a$10$bFoT0UWjOFAIQoFRYCIicO2hwNwZy6qhWYq4eXmWqJevf7b2TWpae", "Anic", false, "0604561231", true, "OBICAN_KORISNIK", "ok");



/**************************** HOTELI ***************************************/
/****  HOTELI  ****/
insert into isa_project.hotel (id, adresa, naziv, opis, administrator_id) 
	values (5, "Novosadskog Sajma 35, Novi Sad", "Hotel Park", "Hotel Park je smešten na obodu velikog parka u Novom Sadu. Ovaj potpuno klimatizovan hotel u ponudi ima elegantno opremljene smeštajne jedinice sa besplatnim internetom i TV-om sa kablovskim kanalima.", 2);
insert into isa_project.hotel (id, adresa, naziv, opis, administrator_id) 
	values (6, "Bulevar Nikole Tesle 3, Beograd", "Jugoslavija", "Hotel „Jugoslavija”, otvoren 1969. godine, bio je jedan od prva tri značajna objekta koji su planirani u Novom Beogradu, pored zgrade CK KPJ i zgrade Predsedništva vlade FNRJ.", 2);
insert into isa_project.hotel (id, adresa, naziv, opis, administrator_id) 
	values (7, "Orlovićeva BB, Ruma", "Hotel Borkovac", "Na 50 km zapadno od Beograda i svega 35 km od Novog Sada, u Rumskom izletištu Borkovac nalazi se hotel u borovima udaljen 3km od centra grada Ruma.", 5);
/**** SOBE ****/
insert into isa_project.soba (id, ima_balkon, kapacitet, sprat, tip_sobe, hotel_id, cena, na_popustu) 
	values (1, 0, 4, 2, "APARTMAN", 6, 0, 0);
insert into isa_project.soba (id, ima_balkon, kapacitet, sprat, tip_sobe, hotel_id, cena, na_popustu) 
	values (2, 1, 1, 5, "JEDNOKREVETNA_SOBA", 6, 0, 0);
insert into isa_project.soba (id, ima_balkon, kapacitet, sprat, tip_sobe, hotel_id, cena, na_popustu) 
	values (3, 1, 4, 5, "DVOKREVETNA_SOBA", 6, 0, 0);
insert into isa_project.soba (id, ima_balkon, kapacitet, sprat, tip_sobe, hotel_id, cena, na_popustu) 
	values (4, 1, 1, 5, "JEDNOKREVETNA_SOBA", 6, 0, 0);
insert into isa_project.soba (id, ima_balkon, kapacitet, sprat, tip_sobe, hotel_id, cena, na_popustu) 
	values (5, 0, 2, 3, "JEDNOKREVETNA_SOBA", 6, 0, 1);
insert into isa_project.soba (id, ima_balkon, kapacitet, sprat, tip_sobe, hotel_id, cena, na_popustu) 
	values (6, 1, 4, 5, "DVOKREVETNA_SOBA", 6, 0, 1);
insert into isa_project.soba (id, ima_balkon, kapacitet, sprat, tip_sobe, hotel_id, cena, na_popustu) 
	values (7, 1, 6, 1, "FAMILY_ROOM", 6, 0, 1);
/**** DODATNE USLUGE ****/
insert into isa_project.dodatna_usluga (id, tip_dodatne_usluge, hotel_id, cena) 
	values (31, "TERETANA", 6, 0);
insert into isa_project.dodatna_usluga (id, tip_dodatne_usluge, hotel_id, cena) 
	values (32, "PARKING", 6, 0);
/**** CENOVNICI ****/
insert into isa_project.cenovnik_hotela (id, pocetak_vazenja, prestanak_vazenja, hotel_id) 
	values (9, DATE '2019-06-01', DATE '2019-08-31', 6);
insert into isa_project.cenovnik_hotela (id, pocetak_vazenja, prestanak_vazenja, hotel_id) 
	values (10, DATE '2019-03-01', DATE '2019-05-31', 6);
insert into isa_project.cenovnik_hotela (id, pocetak_vazenja, prestanak_vazenja, hotel_id) 
	values (11, DATE '2019-09-01', DATE '2019-11-30', 6);
/**** STAVKE CENOVNIKA ****/	
insert into isa_project.stavka_cenovnika_hotela (id, cena, tip_dodatne_usluge, tip_sobe, cenovnik_id) 
	values (20, 2500, null,'JEDNOKREVETNA_SOBA', 11);
insert into isa_project.stavka_cenovnika_hotela (id, cena, tip_dodatne_usluge, tip_sobe, cenovnik_id) 
	values (21, 5600, null,'APARTMAN', 11);
insert into isa_project.stavka_cenovnika_hotela (id, cena, tip_dodatne_usluge, tip_sobe, cenovnik_id) 
	values (22, 3600, null,'DVOKREVETNA_SOBA', 11);
insert into isa_project.stavka_cenovnika_hotela (id, cena, tip_dodatne_usluge, tip_sobe, cenovnik_id) 
	values (23, 8900, null,'FAMILY_ROOM', 11);
insert into isa_project.stavka_cenovnika_hotela (id, cena, tip_dodatne_usluge, tip_sobe, cenovnik_id) 
	values (24, 500, "TERETANA",null, 11);
/**** REZERVACIJA HOTELA ****/
insert into isa_project.rezervacija_hotela (id, cena, datum_pocetka, datum_kraja, korisnik_id, tip_rezervacije, broj_gostiju)
	values (1, 12500, DATE '2019-09-01', DATE '2019-09-12', 4, 0, 4);
insert into isa_project.rezervacija_hotela (id, cena, datum_pocetka, datum_kraja, korisnik_id, tip_rezervacije, broj_gostiju)
	values (2, 18000, DATE '2019-12-20', DATE '2020-01-05', 4, 0, 1);
insert into isa_project.rezervacija_hotela_sobe (rezervacija_hotela_id, sobe_id)
	values (1, 1);
insert into isa_project.rezervacija_hotela_sobe (rezervacija_hotela_id, sobe_id)
	values (1, 2);
insert into isa_project.rezervacija_hotela_sobe (rezervacija_hotela_id, sobe_id)
	values (2, 2);
insert into isa_project.rezervacija_hotela_dodatne_usluge (rezervacija_hotela_id, dodatne_usluge_id)
	values (1, 31);
insert into isa_project.rezervacija_hotela_dodatne_usluge (rezervacija_hotela_id, dodatne_usluge_id)
	values (1, 32);
	



insert into isa_project.rentacar(rentacar_id, adresa, naziv, opis, administrator_id) values 
	(1, "Brace Dronjak 10, Beograd", "Savic trans", "Vrlo kvalitetno i povoljno.", 3);
insert into isa_project.rentacar(rentacar_id, adresa, naziv, opis, administrator_id) values 
	(2, "Bulevar Oslobodjenja 46, Beograd", "Vujovic trans", "Vrlo skupo.", 3);
insert into isa_project.rentacar(rentacar_id, adresa, naziv, opis, administrator_id) values 
	(3, "Grobljanska 16, Dobrinci", "Borkovac trans", "Ne ici ovde.", 3);
insert into isa_project.rentacar(rentacar_id, adresa, naziv, opis, administrator_id) values 
	(4, "Zeleznicka 15", "Big Savic trans", "Najbolje.", 3);

insert into isa_project.vozilo(vozilo_id, broj_sedista, cena, godina_proizvodnje, marka, model, naziv, rezervisano, tip, rentacar_id, na_popustu)
	values (1, 5, 1000, 2019, "BMW", "X5", "BMW X5", 0, "LIMUZINA", 1, 0);
insert into isa_project.vozilo(vozilo_id, broj_sedista, cena, godina_proizvodnje, marka, model, naziv, rezervisano, tip, rentacar_id, na_popustu)
	values (2, 5, 200, 2015, "Mercedes", "Jeep", "Mercedes dzip", 0, "KARAVAN", 1, 1);
insert into isa_project.vozilo(vozilo_id, broj_sedista, cena, godina_proizvodnje, marka, model, naziv, rezervisano, tip, rentacar_id, na_popustu)
	values (3, 5, 400, 2015, "Opel", "Astra", "Opel Astra", 0, "KABRIOLET", 2, 1);
	
insert into isa_project.lokacija(id, adresa, drzava, grad, latitude, longitude, rentacar_id)
	values (1, "Brace Dronjak 10", "Srbija", "Novi Sad", 19, 46, 1);	
insert into isa_project.lokacija(id, adresa, drzava, grad, latitude, longitude, rentacar_id)
	values (2, "Brace Dronjak 12", "Srbija", "Novi Sad", 19, 46, 1);	
	
insert into isa_project.cenovnik_rentacar(id, pocetak_vazenja, prestanak_vazenja, rentacar_rentacar_id)
values (1, '2019-06-01', '2019-08-01', 1);
insert into isa_project.cenovnik_rentacar(id, pocetak_vazenja, prestanak_vazenja, rentacar_rentacar_id)
values (2, '2020-06-01', '2020-08-01', 1);

insert into isa_project.stavka_cenovnika_rent (id, cena, cenovnik_id, tip_vozila) 
	values (1, 500, 1,'LIMUZINA');
insert into isa_project.stavka_cenovnika_rent (id, cena, cenovnik_id, tip_vozila)  
	values (2, 600, 1,'KARAVAN');
insert into isa_project.stavka_cenovnika_rent (id, cena, cenovnik_id, tip_vozila)  
	values (3, 700, 1,'KUPE');
insert into isa_project.stavka_cenovnika_rent (id, cena, cenovnik_id, tip_vozila)  
	values (4, 800, 2,'PICKUP');


insert into isa_project.rezervacija_vozila(id,  datum_preuzimanja, datum_vracanja, korisnik_id, mesto_preuzimanja_id, mesto_vracanja_id, vozilo_vozilo_id, cena, broj_putnika, tip_rezervacije)
values (1, '2019-08-25', '2019-09-25', 4, 1, 1, 1, 500, 5, 0);
insert into isa_project.rezervacija_vozila(id, datum_preuzimanja, datum_vracanja, korisnik_id, mesto_preuzimanja_id, mesto_vracanja_id, vozilo_vozilo_id, cena, broj_putnika, tip_rezervacije)
values (2, '2020-08-25', '2020-09-25', 4, 1, 1, 2, 200, 5, 0);

