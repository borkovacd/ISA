/*** KORISNICI ****/
insert into korisnik (id, email, grad, ime, korisnicko_ime, lozinka, prezime, prvo_logovanje, telefon, verifikovan, uloga, status_korisnika) 
	values (10, "sistem@gmail.com", "Beograd", "Petar",  "sistem", "$2a$10$bFoT0UWjOFAIQoFRYCIicO2hwNwZy6qhWYq4eXmWqJevf7b2TWpae", "Petrovic", false, "065748235", true, "ADMINISTRATOR_SISTEMA", "ok");
insert into korisnik (id, email, grad, ime, korisnicko_ime, lozinka, prezime, prvo_logovanje, telefon, verifikovan, uloga, status_korisnika) 
	values (11, "marko@gmail.com", "Novi Sad", "Marko",  "marko", "$2a$10$bFoT0UWjOFAIQoFRYCIicO2hwNwZy6qhWYq4eXmWqJevf7b2TWpae", "Markovic", false, "0693456789", true, "ADMINISTRATOR_HOTELA", "ok");
insert into korisnik (id, email, grad, ime, korisnicko_ime, lozinka, prezime, prvo_logovanje, telefon, verifikovan, uloga, status_korisnika) 
	values (12, "maja@gmail.com", "Novi Sad", "Maja",  "maja", "$2a$10$bFoT0UWjOFAIQoFRYCIicO2hwNwZy6qhWYq4eXmWqJevf7b2TWpae", "Markovic", false, "06345624543", true, "ADMINISTRATOR_RENT_A_CAR", "ok");
insert into korisnik (id, email, grad, ime, korisnicko_ime, lozinka, prezime, prvo_logovanje, telefon, verifikovan, uloga, status_korisnika) 
	values (13, "sloba@gmail.com", "Novi Sad", "Sloba",  "sloba", "$2a$10$bFoT0UWjOFAIQoFRYCIicO2hwNwZy6qhWYq4eXmWqJevf7b2TWpae", "Slobic", false, "0657456879", true, "OBICAN_KORISNIK", "ok");
	
/**** HOTELI ****/
insert into hotel (id, adresa, naziv, opis, administrator_id) 
	values (10, "Uspenska 1, Novi Sad", "Hotel Centar", "Odlican hotel", 11);
insert into hotel (id, adresa, naziv, opis, administrator_id) 
	values (11, "Svetog Save 9, Beograd", "Hotel Slavija", "Najbolji hotel u gradu", 11);
	
**** SOBE ****/
insert into soba (id, ima_balkon, kapacitet, sprat, tip_sobe, hotel_id, cena, na_popustu) 
	values (10, 0, 4, 2, "APARTMAN", 10, 0, 0);
insert into soba (id, ima_balkon, kapacitet, sprat, tip_sobe, hotel_id, cena, na_popustu) 
	values (11, 1, 1, 5, "JEDNOKREVETNA_SOBA", 10, 0, 0);
insert into soba (id, ima_balkon, kapacitet, sprat, tip_sobe, hotel_id, cena, na_popustu) 
	values (12, 1, 2, 1, "DVOKREVETNA_SOBA", 10, 0, 0);
insert into soba (id, ima_balkon, kapacitet, sprat, tip_sobe, hotel_id, cena, na_popustu) 
	values (13, 1, 6, 3, "FAMILY_ROOM", 10, 0, 1);
insert into soba (id, ima_balkon, kapacitet, sprat, tip_sobe, hotel_id, cena, na_popustu) 
	values (14, 1, 4, 5, "DVOKREVETNA_SOBA", 10, 0, 1);
	
/**** DODATNE USLUGE ****/
insert into dodatna_usluga (id, tip_dodatne_usluge, hotel_id, cena) 
	values (10, "TRANSFER", 10, 0);
insert into dodatna_usluga (id, tip_dodatne_usluge, hotel_id, cena) 
	values (11, "PARKING", 10, 0);
	
/**** CENOVNICI ****/
insert into cenovnik_hotela (id, pocetak_vazenja, prestanak_vazenja, hotel_id) 
	values (10, DATE '2019-09-01', DATE '2019-11-30', 10);	
insert into cenovnik_hotela (id, pocetak_vazenja, prestanak_vazenja, hotel_id) 
	values (11, DATE '2019-12-01', DATE '2020-02-28', 10);

/**** STAVKE CENOVNIKA ****/	
insert into isa_project.stavka_cenovnika_hotela (id, cena, tip_dodatne_usluge, tip_sobe, cenovnik_id) 
	values (10, 1250, null,'JEDNOKREVETNA_SOBA', 10);
insert into isa_project.stavka_cenovnika_hotela (id, cena, tip_dodatne_usluge, tip_sobe, cenovnik_id) 
	values (11, 5500, null,'APARTMAN', 10);
insert into isa_project.stavka_cenovnika_hotela (id, cena, tip_dodatne_usluge, tip_sobe, cenovnik_id) 
	values (12, 2500, null,'DVOKREVETNA_SOBA', 10);
insert into isa_project.stavka_cenovnika_hotela (id, cena, tip_dodatne_usluge, tip_sobe, cenovnik_id) 
	values (13, 500, "TRANSFER",null, 10);
insert into isa_project.stavka_cenovnika_hotela (id, cena, tip_dodatne_usluge, tip_sobe, cenovnik_id) 
	values (14, 750, "PARKING",null, 10);
	
/**** REZERVACIJA HOTELA ****/
insert into isa_project.rezervacija_hotela (id, cena, datum_pocetka, datum_kraja, korisnik_id, tip_rezervacije, broj_gostiju)
	values (10, 25000, DATE '2019-09-01', DATE '2019-09-10', 13, 0, 4);
insert into isa_project.rezervacija_hotela (id, cena, datum_pocetka, datum_kraja, korisnik_id, tip_rezervacije, broj_gostiju)
	values (11, 18000, DATE '2019-12-25', DATE '2019-12-28', 13, 0, 2);
insert into isa_project.rezervacija_hotela (id, cena, datum_pocetka, datum_kraja, korisnik_id, tip_rezervacije, broj_gostiju)
	values (12, 10000, DATE '2019-09-15', DATE '2019-09-20', 13, 1, 2);
insert into isa_project.rezervacija_hotela_sobe (rezervacija_hotela_id, sobe_id)
	values (10, 11);
insert into isa_project.rezervacija_hotela_sobe (rezervacija_hotela_id, sobe_id)
	values (10, 12);
insert into isa_project.rezervacija_hotela_sobe (rezervacija_hotela_id, sobe_id)
	values (11, 10);
insert into isa_project.rezervacija_hotela_sobe (rezervacija_hotela_id, sobe_id)
	values (12, 14);
insert into isa_project.rezervacija_hotela_dodatne_usluge (rezervacija_hotela_id, dodatne_usluge_id)
	values (10, 13);
insert into isa_project.rezervacija_hotela_dodatne_usluge (rezervacija_hotela_id, dodatne_usluge_id)
	values (11, 14);
	

	
