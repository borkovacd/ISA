/*** KORISNICI ****/
insert into korisnik (id, email, grad, ime, korisnicko_ime, lozinka, prezime, prvo_logovanje, telefon, verifikovan, uloga, status_korisnika) 
	values (10, 'sistem@gmail.com', 'Beograd', 'Petar',  'sistem', '$2a$10$bFoT0UWjOFAIQoFRYCIicO2hwNwZy6qhWYq4eXmWqJevf7b2TWpae', 'Petrovic', 0, '065748235', 1, 'ADMINISTRATOR_SISTEMA', 'ok');
insert into korisnik (id, email, grad, ime, korisnicko_ime, lozinka, prezime, prvo_logovanje, telefon, verifikovan, uloga, status_korisnika) 
	values (11, 'marko@gmail.com', 'Novi Sad', 'Marko',  'marko', '$2a$10$bFoT0UWjOFAIQoFRYCIicO2hwNwZy6qhWYq4eXmWqJevf7b2TWpae', 'Markovic', 0, '0693456789', 1, 'ADMINISTRATOR_HOTELA', 'ok');
insert into korisnik (id, email, grad, ime, korisnicko_ime, lozinka, prezime, prvo_logovanje, telefon, verifikovan, uloga, status_korisnika) 
	values (12, 'maja@gmail.com', 'Novi Sad', 'Maja',  'maja', '$2a$10$bFoT0UWjOFAIQoFRYCIicO2hwNwZy6qhWYq4eXmWqJevf7b2TWpae', 'Markovic', 0, '06345624543', 1, 'ADMINISTRATOR_RENT_A_CAR', 'ok');
insert into korisnik (id, email, grad, ime, korisnicko_ime, lozinka, prezime, prvo_logovanje, telefon, verifikovan, uloga, status_korisnika) 
	values (13, 'sloba@gmail.com', 'Novi Sad', 'Sloba',  'sloba', '$2a$10$bFoT0UWjOFAIQoFRYCIicO2hwNwZy6qhWYq4eXmWqJevf7b2TWpae', 'Slobic', 0, '0657456879', 1, 'OBICAN_KORISNIK', 'ok');
	
/**** HOTELI ****/
insert into hotel (id, adresa, naziv, opis, administrator_id) 
	values (10, 'Uspenska 1, Novi Sad', 'Hotel Centar', 'Odlican hotel', 11);
insert into hotel (id, adresa, naziv, opis, administrator_id) 
	values (11, 'Svetog Save 9, Beograd', 'Hotel Slavija', 'Najbolji hotel u gradu', 11);
	
/**** SOBE ****/
insert into soba (id, ima_balkon, kapacitet, sprat, tip_sobe, hotel_id, cena, na_popustu) 
	values (10, 0, 4, 2, 'APARTMAN', 10, 0, 0);
insert into soba (id, ima_balkon, kapacitet, sprat, tip_sobe, hotel_id, cena, na_popustu) 
	values (11, 1, 1, 5, 'JEDNOKREVETNA_SOBA', 10, 0, 0);
insert into soba (id, ima_balkon, kapacitet, sprat, tip_sobe, hotel_id, cena, na_popustu) 
	values (12, 1, 2, 1, 'DVOKREVETNA_SOBA', 10, 0, 0);
insert into soba (id, ima_balkon, kapacitet, sprat, tip_sobe, hotel_id, cena, na_popustu) 
	values (13, 1, 6, 3, 'FAMILY_ROOM', 10, 0, 1);
insert into soba (id, ima_balkon, kapacitet, sprat, tip_sobe, hotel_id, cena, na_popustu) 
	values (14, 1, 4, 5, 'DVOKREVETNA_SOBA', 10, 0, 1);
	
/**** DODATNE USLUGE ****/
insert into dodatna_usluga (id, tip_dodatne_usluge, hotel_id, cena) 
	values (10, 'TRANSFER', 10, 0);
insert into dodatna_usluga (id, tip_dodatne_usluge, hotel_id, cena) 
	values (11, 'PARKING', 10, 0);
	
/**** CENOVNICI ****/
insert into cenovnik_hotela (id, pocetak_vazenja, prestanak_vazenja, hotel_id) 
	values (10, DATE '2019-09-01', DATE '2019-11-30', 10);	
insert into cenovnik_hotela (id, pocetak_vazenja, prestanak_vazenja, hotel_id) 
	values (11, DATE '2019-12-01', DATE '2020-02-28', 10);

/**** STAVKE CENOVNIKA ****/	
insert into stavka_cenovnika_hotela (id, cena, tip_dodatne_usluge, tip_sobe, cenovnik_id) 
	values (10, 1250, null,'JEDNOKREVETNA_SOBA', 10);
insert into stavka_cenovnika_hotela (id, cena, tip_dodatne_usluge, tip_sobe, cenovnik_id) 
	values (11, 5500, null,'APARTMAN', 10);
insert into stavka_cenovnika_hotela (id, cena, tip_dodatne_usluge, tip_sobe, cenovnik_id) 
	values (12, 2500, null,'DVOKREVETNA_SOBA', 10);
insert into stavka_cenovnika_hotela (id, cena, tip_dodatne_usluge, tip_sobe, cenovnik_id) 
	values (13, 500, 'TRANSFER',null, 10);
insert into stavka_cenovnika_hotela (id, cena, tip_dodatne_usluge, tip_sobe, cenovnik_id) 
	values (14, 750, 'PARKING',null, 10);
	
/**** REZERVACIJA HOTELA ****/
insert into rezervacija_hotela (id, cena, datum_pocetka, datum_kraja, korisnik_id, tip_rezervacije, broj_gostiju)
	values (10, 25000, DATE '2019-09-01', DATE '2019-09-10', 13, 0, 4);
insert into rezervacija_hotela (id, cena, datum_pocetka, datum_kraja, korisnik_id, tip_rezervacije, broj_gostiju)
	values (11, 18000, DATE '2019-12-25', DATE '2019-12-28', 13, 0, 2);
insert into rezervacija_hotela (id, cena, datum_pocetka, datum_kraja, korisnik_id, tip_rezervacije, broj_gostiju)
	values (12, 10000, DATE '2019-09-21', DATE '2019-09-26', 13, 1, 2);
insert into rezervacija_hotela_sobe (rezervacija_hotela_id, sobe_id)
	values (10, 11);
insert into rezervacija_hotela_sobe (rezervacija_hotela_id, sobe_id)
	values (10, 12);
insert into rezervacija_hotela_sobe (rezervacija_hotela_id, sobe_id)
	values (11, 10);
insert into rezervacija_hotela_sobe (rezervacija_hotela_id, sobe_id)
	values (12, 14);
insert into rezervacija_hotela_dodatne_usluge (rezervacija_hotela_id, dodatne_usluge_id)
	values (10, 10);
insert into rezervacija_hotela_dodatne_usluge (rezervacija_hotela_id, dodatne_usluge_id)
	values (11, 11);
	
	
/**** RENT-CAR SERVISI ****/
insert into rentacar(rentacar_id, adresa, naziv, opis, administrator_id) values 
	(10, 'Adresa 10', 'Rent trans 1', 'Vrlo kvalitetno i povoljno.', 12);
insert into rentacar(rentacar_id, adresa, naziv, opis, administrator_id) values 
	(11, 'Adresa 11', 'Rent trans 2', 'Vrlo skupo.', 12);
insert into rentacar(rentacar_id, adresa, naziv, opis, administrator_id) values 
	(12, 'Adresa 16, Dobrinci', 'Rent trans 15', 'Uvek ici ovde.', 12);
insert into rentacar(rentacar_id, adresa, naziv, opis, administrator_id) values 
	(13, 'Zeleznicka 28', 'Very good trans', 'Najbolje.', 12);
	
/**** VOZILA ****/	
insert into vozilo(vozilo_id, broj_sedista, cena, godina_proizvodnje, marka, model, naziv, rezervisano, tip, rentacar_id, na_popustu)
	values (10, 5, 1000, 2019, 'BMW', 'X5', 'BMW X5', 0, 'LIMUZINA', 10, 0);
insert into vozilo(vozilo_id, broj_sedista, cena, godina_proizvodnje, marka, model, naziv, rezervisano, tip, rentacar_id, na_popustu)
	values (11, 5, 200, 2015, 'Mercedes', 'Jeep', 'Mercedes dzip', 0, 'KARAVAN', 10, 0);
insert into vozilo(vozilo_id, broj_sedista, cena, godina_proizvodnje, marka, model, naziv, rezervisano, tip, rentacar_id, na_popustu)
	values (12, 5, 400, 2015, 'Opel', 'Astra', 'Opel Astra', 0, 'KABRIOLET', 11, 0);

/**** LOKACIJA ****/	
insert into lokacija(id, adresa, drzava, grad, latitude, longitude, rentacar_id)
	values (10, 'Brace Dronjak 10', 'Srbija', 'Novi Sad', 19, 46, 10);	
insert into lokacija(id, adresa, drzava, grad, latitude, longitude, rentacar_id)
	values (11, 'Brace Dronjak 12', 'Srbija', 'Novi Sad', 19, 46, 10);	
	
/**** CENOVNIK ****/	
insert into cenovnik_rentacar(id, pocetak_vazenja, prestanak_vazenja, rentacar_rentacar_id)
values (10, '2019-06-01', '2019-08-01', 10);
insert into cenovnik_rentacar(id, pocetak_vazenja, prestanak_vazenja, rentacar_rentacar_id)
values (11, '2020-06-01', '2020-08-01', 10);

/**** STAVKA CENOVNIKA ****/
insert into stavka_cenovnika_rent (id, cena, cenovnik_id, tip_vozila) 
	values (10, 500, 10,'LIMUZINA');
insert into stavka_cenovnika_rent (id, cena, cenovnik_id, tip_vozila)  
	values (11, 600, 10,'KARAVAN');
insert into stavka_cenovnika_rent (id, cena, cenovnik_id, tip_vozila)  
	values (12, 700, 10,'KUPE');
insert into stavka_cenovnika_rent (id, cena, cenovnik_id, tip_vozila)  
	values (13, 800, 11,'PICKUP');

/**** REZERVACIJA ****/
insert into rezervacija_vozila(id,  datum_preuzimanja, datum_vracanja, korisnik_id, mesto_preuzimanja_id, mesto_vracanja_id, vozilo_vozilo_id, cena, broj_putnika, tip_rezervacije)
	values (10, '2019-08-25', '2019-09-25', 13, 10, 10, 10, 500, 5, 0);
insert into rezervacija_vozila(id, datum_preuzimanja, datum_vracanja, korisnik_id, mesto_preuzimanja_id, mesto_vracanja_id, vozilo_vozilo_id, cena, broj_putnika, tip_rezervacije)
	values (11, '2020-08-25', '2020-09-25', 13, 10, 10, 11, 200, 5, 0);

/**** OCENE ****/
insert into ocena_vozilo(id, ocena, user_id, vozilo_vozilo_id)
	values (10, 5, 13, 10);
insert into ocena_vozilo(id, ocena, user_id, vozilo_vozilo_id)
	values (11, 3, 13, 10);	
	