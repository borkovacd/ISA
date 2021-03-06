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
insert into isa_project.hotel (id, ocena, adresa, naziv, opis, administrator_id) 
	values (5, 0, "Novosadskog Sajma 35, Novi Sad", "Hotel Park", "Hotel Park je smešten na obodu velikog parka u Novom Sadu. Ovaj potpuno klimatizovan hotel u ponudi ima elegantno opremljene smeštajne jedinice sa besplatnim internetom i TV-om sa kablovskim kanalima.", 2);
insert into isa_project.hotel (id, ocena, adresa, naziv, opis, administrator_id) 
	values (6, 0,"Bulevar Nikole Tesle 3, Beograd", "Jugoslavija", "Hotel „Jugoslavija”, otvoren 1969. godine, bio je jedan od prva tri značajna objekta koji su planirani u Novom Beogradu, pored zgrade CK KPJ i zgrade Predsedništva vlade FNRJ.", 2);
insert into isa_project.hotel (id, ocena, adresa, naziv, opis, administrator_id) 
	values (7, 0, "Orlovićeva BB, Ruma", "Hotel Borkovac", "Na 50 km zapadno od Beograda i svega 35 km od Novog Sada, u Rumskom izletištu Borkovac nalazi se hotel u borovima udaljen 3km od centra grada Ruma.", 2);

/**** SOBE ****/
insert into isa_project.soba (id, ocena, ima_balkon, kapacitet, sprat, tip_sobe, hotel_id, cena, na_popustu) 
	values (1, 0, 0, 4, 2, "APARTMAN", 6, 0, 0);
insert into isa_project.soba (id, ocena,  ima_balkon, kapacitet, sprat, tip_sobe, hotel_id, cena, na_popustu) 
	values (2, 0, 1, 1, 5, "JEDNOKREVETNA_SOBA", 6, 0, 0);
insert into isa_project.soba (id, ocena, ima_balkon, kapacitet, sprat, tip_sobe, hotel_id, cena, na_popustu) 
	values (3, 0, 1, 4, 5, "DVOKREVETNA_SOBA", 6, 0, 0);
insert into isa_project.soba (id, ocena, ima_balkon, kapacitet, sprat, tip_sobe, hotel_id, cena, na_popustu) 
	values (4, 0, 1, 1, 5, "JEDNOKREVETNA_SOBA", 6, 0, 0);
insert into isa_project.soba (id, ocena, ima_balkon, kapacitet, sprat, tip_sobe, hotel_id, cena, na_popustu) 
	values (5, 0, 0, 2, 3, "JEDNOKREVETNA_SOBA", 6, 0, 1);
insert into isa_project.soba (id, ocena, ima_balkon, kapacitet, sprat, tip_sobe, hotel_id, cena, na_popustu) 
	values (6, 0, 1, 3, 5, "DVOKREVETNA_SOBA", 6, 0, 1);
insert into isa_project.soba (id, ocena, ima_balkon, kapacitet, sprat, tip_sobe, hotel_id, cena, na_popustu) 
	values (7, 0, 1, 6, 1, "FAMILY_ROOM", 6, 0, 1);
insert into isa_project.soba (id, ocena, ima_balkon, kapacitet, sprat, tip_sobe, hotel_id, cena, na_popustu) 
	values (8, 0, 1, 1, 5, "JEDNOKREVETNA_SOBA", 5, 0, 0);
insert into isa_project.soba (id, ocena, ima_balkon, kapacitet, sprat, tip_sobe, hotel_id, cena, na_popustu) 
	values (9, 0, 1, 3, 5, "DVOKREVETNA_SOBA", 5, 0, 0);
insert into isa_project.soba (id, ocena, ima_balkon, kapacitet, sprat, tip_sobe, hotel_id, cena, na_popustu) 
	values (10, 0, 1, 6, 1, "FAMILY_ROOM", 5, 0, 1);
	
/**** DODATNE USLUGE ****/
insert into isa_project.dodatna_usluga (id, tip_dodatne_usluge, hotel_id, cena) 
	values (31, "TERETANA", 6, 0);
insert into isa_project.dodatna_usluga (id, tip_dodatne_usluge, hotel_id, cena) 
	values (32, "PARKING", 6, 0);
insert into isa_project.dodatna_usluga (id, tip_dodatne_usluge, hotel_id, cena) 
	values (33, "BAZEN", 5, 0);
insert into isa_project.dodatna_usluga (id, tip_dodatne_usluge, hotel_id, cena) 
	values (34, "SOBNI_SERVIS", 5, 0);
	
/**** CENOVNICI ****/
insert into isa_project.cenovnik_hotela (id, pocetak_vazenja, prestanak_vazenja, hotel_id) 
	values (10, DATE '2019-05-01', DATE '2019-07-31', 6);
insert into isa_project.cenovnik_hotela (id, pocetak_vazenja, prestanak_vazenja, hotel_id) 
	values (11, DATE '2019-08-01', DATE '2019-10-31', 6);
insert into isa_project.cenovnik_hotela (id, pocetak_vazenja, prestanak_vazenja, hotel_id) 
	values (12, DATE '2019-07-01', DATE '2019-09-30', 5);
	
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
insert into isa_project.stavka_cenovnika_hotela (id, cena, tip_dodatne_usluge, tip_sobe, cenovnik_id) 
	values (25, 750, "PARKING",null, 11);
insert into isa_project.stavka_cenovnika_hotela (id, cena, tip_dodatne_usluge, tip_sobe, cenovnik_id) 
	values (26, 3000, null,'JEDNOKREVETNA_SOBA', 12);
insert into isa_project.stavka_cenovnika_hotela (id, cena, tip_dodatne_usluge, tip_sobe, cenovnik_id) 
	values (27, 5200, null,'DVOKREVETNA_SOBA', 12);
insert into isa_project.stavka_cenovnika_hotela (id, cena, tip_dodatne_usluge, tip_sobe, cenovnik_id) 
	values (28, 11000, null,'FAMILY_ROOM', 12);
insert into isa_project.stavka_cenovnika_hotela (id, cena, tip_dodatne_usluge, tip_sobe, cenovnik_id) 
	values (29, 300, "BAZEN",null, 12);
insert into isa_project.stavka_cenovnika_hotela (id, cena, tip_dodatne_usluge, tip_sobe, cenovnik_id) 
	values (30, 900, "SOBNI_SERVIS",null, 12);

	
/**** REZERVACIJA HOTELA ****/
insert into isa_project.rezervacija_hotela (id, cena, datum_pocetka, datum_kraja, korisnik_id, tip_rezervacije, broj_gostiju)
	values (1, 12500, DATE '2019-08-01', DATE '2019-08-12', 4, 0, 4);
insert into isa_project.rezervacija_hotela (id, cena, datum_pocetka, datum_kraja, korisnik_id, tip_rezervacije, broj_gostiju)
	values (2, 18000, DATE '2019-12-20', DATE '2019-12-25', 4, 0, 1);
insert into isa_project.rezervacija_hotela (id, cena, datum_pocetka, datum_kraja, korisnik_id, tip_rezervacije, broj_gostiju)
	values (3, 9000, DATE '2019-07-05', DATE '2019-07-15', 4, 0, 2);
insert into isa_project.rezervacija_hotela_sobe (rezervacija_hotela_id, sobe_id)
	values (1, 1);
insert into isa_project.rezervacija_hotela_sobe (rezervacija_hotela_id, sobe_id)
	values (1, 2);
insert into isa_project.rezervacija_hotela_sobe (rezervacija_hotela_id, sobe_id)
	values (2, 2);
insert into isa_project.rezervacija_hotela_sobe (rezervacija_hotela_id, sobe_id)
	values (3, 8);
insert into isa_project.rezervacija_hotela_dodatne_usluge (rezervacija_hotela_id, dodatne_usluge_id)
	values (1, 31);
insert into isa_project.rezervacija_hotela_dodatne_usluge (rezervacija_hotela_id, dodatne_usluge_id)
	values (1, 32);
insert into isa_project.rezervacija_hotela_dodatne_usluge (rezervacija_hotela_id, dodatne_usluge_id)
	values (3, 34);
	
/**** RENT-A-CAR ****/
insert into isa_project.rentacar(rentacar_id, adresa, naziv, opis, administrator_id, ocena) values 
	(1, "Bulevar Kneza Milosa, Beograd", "Fast Trans", "Vrlo kvalitetno i povoljno, prijatno osoblje, uvek na usluzi.", 3, 1);
insert into isa_project.rentacar(rentacar_id, adresa, naziv, opis, administrator_id, ocena) values 
	(2, "Partizanska 46, Beograd", "Savic Trans", "Vrlo skupo, novija vozila.", 3, 2);
insert into isa_project.rentacar(rentacar_id, adresa, naziv, opis, administrator_id, ocena) values 
	(3, "Brace Dronjak 10, Novi Sad", "Borkovac trans", "Veoma jeftino, mali izbor vozila, niskog kvaliteta.", 3, 3);
insert into isa_project.rentacar(rentacar_id, adresa, naziv, opis, administrator_id, ocena) values 
	(4, "Gogoljeva 15, Novi Sad", "Big Savic trans", "Najbolji odnos cene i kvaliteta, sirok asortiman vozila.", 3, 4);

/**** VOZILA ****/	
insert into isa_project.vozilo(vozilo_id, ocena, broj_sedista, cena, godina_proizvodnje, marka, model, naziv, rezervisano, tip, rentacar_id, na_popustu)
	values (1, 1, 4, 0, 2019, "BMW", "X5", "BMW X5", false, "LIMUZINA", 1, false);
insert into isa_project.vozilo(vozilo_id, ocena, broj_sedista, cena, godina_proizvodnje, marka, model, naziv, rezervisano, tip, rentacar_id, na_popustu)
	values (2, 0, 4, 0, 2015, "Mercedes", "Jeep", "Mercedes dzip", false, "KARAVAN", 1, true);
insert into isa_project.vozilo(vozilo_id, ocena, broj_sedista, cena, godina_proizvodnje, marka, model, naziv, rezervisano, tip, rentacar_id, na_popustu)
	values (3, 0, 5, 0, 2015, "Opel", "Astra", "Opel Astra", false, "KABRIOLET", 1, false);

insert into isa_project.vozilo(vozilo_id, ocena, broj_sedista, cena, godina_proizvodnje, marka, model, naziv, rezervisano, tip, rentacar_id, na_popustu)
	values (4, 2, 4, 0, 2019, "Siroko", "P12", "Siroko P12", false, "PICKUP", 2, false);
insert into isa_project.vozilo(vozilo_id, ocena, broj_sedista, cena, godina_proizvodnje, marka, model, naziv, rezervisano, tip, rentacar_id, na_popustu)
	values (5, 0, 4, 0, 2015, "Opel", "Insignia", "Opel Insignia", false, "KARAVAN", 2, true);

insert into isa_project.vozilo(vozilo_id, ocena, broj_sedista, cena, godina_proizvodnje, marka, model, naziv, rezervisano, tip, rentacar_id, na_popustu)
	values (6, 3, 5, 400, 2015, "Fica", "500", "Fica Fiat 5", false, "MINIVEN", 3, false);	

insert into isa_project.vozilo(vozilo_id, ocena, broj_sedista, cena, godina_proizvodnje, marka, model, naziv, rezervisano, tip, rentacar_id, na_popustu)
	values (7, 4, 5, 400, 2015, "Opel", "Insignia", "Opel Insignia", false, "KUPE", 4, false);	
	
/**** LOKACIJA ****/	
insert into isa_project.lokacija(id, adresa, drzava, grad, latitude, longitude, rentacar_id)
	values (1, "Pupinova 12, Beograd", "Srbija", "Beograd", 19, 46, 1);	
insert into isa_project.lokacija(id, adresa, drzava, grad, latitude, longitude, rentacar_id)
	values (2, "Gogoljeva 15, Beograd", "Srbija", "Beograd", 19, 46, 2);
insert into isa_project.lokacija(id, adresa, drzava, grad, latitude, longitude, rentacar_id)
	values (3, "Micurinova 16, Novi Sad", "Srbija", "Novi Sad", 19, 46, 3);	
insert into isa_project.lokacija(id, adresa, drzava, grad, latitude, longitude, rentacar_id)
	values (4, "Avramova 12, Novi Sad", "Srbija", "Novi Sad", 19, 46, 4);		
	
/**** CENOVNICI ****/	
insert into isa_project.cenovnik_rentacar(id, pocetak_vazenja, prestanak_vazenja, rentacar_rentacar_id)
	values (1, DATE'2019-01-01', DATE'2019-12-12', 1);
insert into isa_project.cenovnik_rentacar(id, pocetak_vazenja, prestanak_vazenja, rentacar_rentacar_id)
	values (2, DATE'2020-01-01', DATE'2020-12-12', 1);
insert into isa_project.cenovnik_rentacar(id, pocetak_vazenja, prestanak_vazenja, rentacar_rentacar_id)
	values (3, DATE'2019-01-01', DATE'2020-12-12', 2);
insert into isa_project.cenovnik_rentacar(id, pocetak_vazenja, prestanak_vazenja, rentacar_rentacar_id)
	values (4, DATE'2019-01-01', DATE'2020-12-12', 3);
insert into isa_project.cenovnik_rentacar(id, pocetak_vazenja, prestanak_vazenja, rentacar_rentacar_id)
	values (5, DATE'2019-01-01', DATE'2020-12-12', 4);

/**** STAVKE CENOVNIKA ****/
insert into isa_project.stavka_cenovnika_rent (id, cena, cenovnik_id, tip_vozila) 
	values (1, 500, 1,'LIMUZINA');
insert into isa_project.stavka_cenovnika_rent (id, cena, cenovnik_id, tip_vozila)  
	values (2, 600, 1,'KARAVAN');
insert into isa_project.stavka_cenovnika_rent (id, cena, cenovnik_id, tip_vozila)  
	values (3, 700, 1,'KABRIOLET');
	
insert into isa_project.stavka_cenovnika_rent (id, cena, cenovnik_id, tip_vozila) 
	values (4, 600, 2,'LIMUZINA');
insert into isa_project.stavka_cenovnika_rent (id, cena, cenovnik_id, tip_vozila)  
	values (5, 700, 2,'KARAVAN');
insert into isa_project.stavka_cenovnika_rent (id, cena, cenovnik_id, tip_vozila)  
	values (6, 800, 2,'KABRIOLET');	
	
insert into isa_project.stavka_cenovnika_rent (id, cena, cenovnik_id, tip_vozila)  
	values (7, 800, 3,'PICKUP');
insert into isa_project.stavka_cenovnika_rent (id, cena, cenovnik_id, tip_vozila)  
	values (8, 900, 3,'KARAVAN');

insert into isa_project.stavka_cenovnika_rent (id, cena, cenovnik_id, tip_vozila)  
	values (9, 200, 4,'MINIVEN');	
	
insert into isa_project.stavka_cenovnika_rent (id, cena, cenovnik_id, tip_vozila)  
	values (10, 1000, 5,'KUPE');

/**** REZERVACIJE VOZILA ****/
/**** VOZILA 1 - limuzina, 4 - pickup, 6 - miniven, 7 MASA ****/
insert into isa_project.rezervacija_vozila(id,  datum_preuzimanja, datum_vracanja, korisnik_id, mesto_preuzimanja_id, mesto_vracanja_id, vozilo_vozilo_id, cena, broj_putnika, tip_rezervacije)
	values (1, DATE'2019-09-25', DATE'2019-09-30', 4, 1, 1, 1, 3000, 4, 0);

insert into isa_project.rezervacija_vozila(id, datum_preuzimanja, datum_vracanja, korisnik_id, mesto_preuzimanja_id, mesto_vracanja_id, vozilo_vozilo_id, cena, broj_putnika, tip_rezervacije)
	values (2, DATE'2020-08-25', DATE'2020-08-30', 4, 2, 2, 4, 4800, 4, 0);
	
insert into isa_project.rezervacija_vozila(id, datum_preuzimanja, datum_vracanja, korisnik_id, mesto_preuzimanja_id, mesto_vracanja_id, vozilo_vozilo_id, cena, broj_putnika, tip_rezervacije)
	values (3, DATE'2019-08-25', DATE'2019-08-30', 4, 3, 3, 6, 1200, 4, 0);	

insert into isa_project.rezervacija_vozila(id, datum_preuzimanja, datum_vracanja, korisnik_id, mesto_preuzimanja_id, mesto_vracanja_id, vozilo_vozilo_id, cena, broj_putnika, tip_rezervacije)
	values (4, DATE'2019-07-25', DATE'2019-07-30', 4, 4, 4, 7, 6000, 5, 0);	

/**** VOZILA 1 - limuzina, 4, 6, 7 ANA****/	
insert into isa_project.rezervacija_vozila(id, datum_preuzimanja, datum_vracanja, korisnik_id, mesto_preuzimanja_id, mesto_vracanja_id, vozilo_vozilo_id, cena, broj_putnika, tip_rezervacije)
	values (5, '2019-01-01', '2019-01-05', 7, 1, 1, 1, 2500, 4, 0);	
insert into isa_project.rezervacija_vozila(id, datum_preuzimanja, datum_vracanja, korisnik_id, mesto_preuzimanja_id, mesto_vracanja_id, vozilo_vozilo_id, cena, broj_putnika, tip_rezervacije)
	values (6, '2019-03-01', '2019-03-07', 7, 2, 2, 4, 5600, 4, 0);	
insert into isa_project.rezervacija_vozila(id, datum_preuzimanja, datum_vracanja, korisnik_id, mesto_preuzimanja_id, mesto_vracanja_id, vozilo_vozilo_id, cena, broj_putnika, tip_rezervacije)
	values (7, '2019-02-01', '2019-02-05', 7, 3, 3, 6, 1000, 4, 0);		
insert into isa_project.rezervacija_vozila(id, datum_preuzimanja, datum_vracanja, korisnik_id, mesto_preuzimanja_id, mesto_vracanja_id, vozilo_vozilo_id, cena, broj_putnika, tip_rezervacije)
	values (8, '2019-01-10', '2019-01-15', 7, 4, 4, 7, 6000, 5, 0);	

/**** OCENA HOTEL ****/
--insert into isa_project.ocena_hotel(id, ocena, user_id, hotel_id)
--	values (1, 1, 6, 5);
--insert into isa_project.ocena_hotel(id, ocena, user_id, hotel_id)
--	values (2, 2, 6, 6);
--insert into isa_project.ocena_hotel(id, ocena, user_id, hotel_id)
--	values (3, 3, 6, 7);
	
/**** OCENA SOBA ****/
--insert into isa_project.ocena_soba(id, ocena, user_id, soba_id)
--	values(1, 1, 6, 1);
--insert into isa_project.ocena_soba(id, ocena, user_id, soba_id)
--	values(2, 2, 6, 2);
--insert into isa_project.ocena_soba(id, ocena, user_id, soba_id)
--	values(3, 3, 6, 3);
--insert into isa_project.ocena_soba(id, ocena, user_id, soba_id)
--	values(4, 4, 6, 4);
--insert into isa_project.ocena_soba(id, ocena, user_id, soba_id)
--	values(5, 5, 6, 5);
--insert into isa_project.ocena_soba(id, ocena, user_id, soba_id)
--	values(6, 1, 6, 6);	
--insert into isa_project.ocena_soba(id, ocena, user_id, soba_id)
--	values(7, 2, 6, 7);
--insert into isa_project.ocena_soba(id, ocena, user_id, soba_id)
--	values(8, 3, 6, 8);
--insert into isa_project.ocena_soba(id, ocena, user_id, soba_id)
--	values(9, 4, 6, 9);
--insert into isa_project.ocena_soba(id, ocena, user_id, soba_id)
--	values(10, 5, 6, 10);	
	
/**** OCENA RENT ****/	
insert into isa_project.ocena_rentacar(id, ocena, user_id, rent_rentacar_id)
	values (1, 1, 7, 1);
insert into isa_project.ocena_rentacar(id, ocena, user_id, rent_rentacar_id)
	values (2, 2, 7, 2);
insert into isa_project.ocena_rentacar(id, ocena, user_id, rent_rentacar_id)
	values (3, 3, 7, 3);
insert into isa_project.ocena_rentacar(id, ocena, user_id, rent_rentacar_id)
	values (4, 4, 7, 4);
	
/**** OCENA VOZILO ****/
insert into isa_project.ocena_vozilo(id, ocena, user_id, vozilo_vozilo_id)
	values (1, 1, 7, 1);
insert into isa_project.ocena_vozilo(id, ocena, user_id, vozilo_vozilo_id)
	values (2, 2, 7, 4);
insert into isa_project.ocena_vozilo(id, ocena, user_id, vozilo_vozilo_id)
	values (3, 3, 7, 6);
insert into isa_project.ocena_vozilo(id, ocena, user_id, vozilo_vozilo_id)
	values (4, 4, 7, 7);


