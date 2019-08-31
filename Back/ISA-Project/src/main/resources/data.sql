insert into isa_project.korisnik (id, email, grad, ime, korisnicko_ime, lozinka, prezime, prvo_logovanje, telefon, verifikovan, uloga) 
	values (1, "admin@gmail.com", "Novi Sad", "Admin",  "admin", "8c6976e5b5410415bde908bd4dee15dfb167a9c873fc4bb8a81f6f2ab448a918", "Admin", false, "0600000000", true, "ADMINISTRATOR_SISTEMA");
insert into isa_project.korisnik (id, email, grad, ime, korisnicko_ime, lozinka, prezime, prvo_logovanje, telefon, verifikovan, uloga) 
	values (2, "borkovac.dragan@gmail.com", "Novi Sad", "Dragan",  "dragan", "8c6976e5b5410415bde908bd4dee15dfb167a9c873fc4bb8a81f6f2ab448a918", "Borkovac", false, "0605959769", true, "ADMINISTRATOR_HOTELA");
insert into isa_project.korisnik (id, email, grad, ime, korisnicko_ime, lozinka, prezime, prvo_logovanje, telefon, verifikovan, uloga) 
	values (3, "savic.olga@gmail.com", "Novi Sad", "Olga",  "olga", "8c6976e5b5410415bde908bd4dee15dfb167a9c873fc4bb8a81f6f2ab448a918", "Savic", false, "06345624543", true, "ADMINISTRATOR_RENT_A_CAR");
insert into isa_project.korisnik (id, email, grad, ime, korisnicko_ime, lozinka, prezime, prvo_logovanje, telefon, verifikovan, uloga) 
	values (4, "djurkovic.masa@gmail.com", "Novi Sad", "Masa",  "masa", "8c6976e5b5410415bde908bd4dee15dfb167a9c873fc4bb8a81f6f2ab448a918", "Djurkovic", false, "0604561231", true, "OBICAN_KORISNIK");

insert into isa_project.hotel (id, adresa, naziv, opis, administrator_id) 
	values (5, "Novosadskog Sajma 35, Novi Sad", "Hotel Park", "Hotel Park je smešten na obodu velikog parka u Novom Sadu. Ovaj potpuno klimatizovan hotel u ponudi ima elegantno opremljene smeštajne jedinice sa besplatnim internetom i TV-om sa kablovskim kanalima.", 2);
insert into isa_project.hotel (id, adresa, naziv, opis, administrator_id) 
	values (6, "Bulevar Nikole Tesle 3, Beograd", "Jugoslavija", "Hotel „Jugoslavija”, otvoren 1969. godine, bio je jedan od prva tri značajna objekta koji su planirani u Novom Beogradu, pored zgrade CK KPJ i zgrade Predsedništva vlade FNRJ.", 2);

insert into isa_project.soba (id, ima_balkon, kapacitet, rezervisana, sprat, tip_sobe, hotel_id) 
	values (1, 0, 4, 0, 2, "APARTMAN", 5);
insert into isa_project.soba (id, ima_balkon, kapacitet, rezervisana, sprat, tip_sobe, hotel_id) 
	values (2, 1, 2, 0, 5, "JEDNOKREVETNA_SOBA", 6);

	
insert into isa_project.dodatna_usluga (id, tip_dodatne_usluge, hotel_id) 
	values (31, "TERETANA", 5);
insert into isa_project.dodatna_usluga (id, tip_dodatne_usluge, hotel_id) 
	values (32, "PARKING", 5);

insert into isa_project.cenovnik_hotela (id, pocetak_vazenja, prestanak_vazenja, hotel_id) 
	values (9, DATE '2019-06-01', DATE '2019-08-31', 6);
insert into isa_project.cenovnik_hotela (id, pocetak_vazenja, prestanak_vazenja, hotel_id) 
	values (10, DATE '2019-03-01', DATE '2019-05-31', 5);
insert into isa_project.cenovnik_hotela (id, pocetak_vazenja, prestanak_vazenja, hotel_id) 
	values (11, DATE '2019-09-01', DATE '2019-11-30', 5);
	
insert into isa_project.stavka_cenovnika_hotela (id, cena, tip_dodatne_usluge, tip_sobe, cenovnik_id) 
	values (20, 2500, null,'JEDNOKREVETNA_SOBA', 9);
	
insert into isa_project.rezervacija_hotela (id, cena, datum_kraja, datum_pocetka, korisnik_id)
	values (1, 2450, DATE '2019-08-25', DATE '2019-09-01', 2);
insert into isa_project.rezervacija_hotela_sobe (rezervacija_hotela_id, sobe_id)
	values (1, 1);
insert into isa_project.rezervacija_hotela_dodatne_usluge (rezervacija_hotela_id, dodatne_usluge_id)
	values (1, 31);
insert into isa_project.rezervacija_hotela_dodatne_usluge (rezervacija_hotela_id, dodatne_usluge_id)
	values (1, 32);
	
insert into isa_project.hotel (id, adresa, naziv, opis, administrator_id) 
	values (7, "Orlovićeva BB, Ruma", "Hotel Borkovac", "Na 50 km zapadno od Beograda i svega 35 km od Novog Sada, u Rumskom izletištu Borkovac nalazi se hotel u borovima udaljen 3km od centra grada Ruma.", 5);
insert into isa_project.korisnik (id, email, grad, ime, korisnicko_ime, lozinka, prezime, prvo_logovanje, telefon, verifikovan, uloga) 
	values (5, "petrovic.petar@gmail.com", "Beograd", "Petar",  "petar", "8c6976e5b5410415bde908bd4dee15dfb167a9c873fc4bb8a81f6f2ab448a918", "Petrovic", false, "0604561231", true, "ADMINISTRATOR_HOTELA");

insert into isa_project.rentacar(rentacar_id, adresa, naziv, opis, administrator_id) values 
	(1, "Brace Dronjak 10", "Savic trans", "Vrlo kvalitetno i povoljno.", 3);
insert into isa_project.rentacar(rentacar_id, adresa, naziv, opis, administrator_id) values 
	(2, "Bulevar Oslobodjenja 46", "Vujovic trans", "Vrlo skupo.", 3);
insert into isa_project.rentacar(rentacar_id, adresa, naziv, opis, administrator_id) values 
	(3, "Grobljanska 16", "Borkovac trans", "Ne ici ovde.", 3);
insert into isa_project.rentacar(rentacar_id, adresa, naziv, opis, administrator_id) values 
	(4, "Zeleznicka 15", "Big Savic trans", "Najbolje.", 3);

insert into isa_project.vozilo(vozilo_id, broj_sedista, cena, godina_proizvodnje, marka, model, naziv, rezervisano, tip, rentacar_id)
	values (1, 5, 1000, 2019, "BMW", "X5", "BMW X5", 0, "dzip", 1);
insert into isa_project.vozilo(vozilo_id, broj_sedista, cena, godina_proizvodnje, marka, model, naziv, rezervisano, tip, rentacar_id)
	values (2, 5, 200, 2015, "Mercedes", "Jeep", "Mercedes dzip", 0, "dzip", 1);
insert into isa_project.vozilo(vozilo_id, broj_sedista, cena, godina_proizvodnje, marka, model, naziv, rezervisano, tip, rentacar_id)
	values (3, 5, 400, 2015, "Opel", "Astra", "Opel Astra", 0, "karavan", 2);
	
insert into isa_project.lokacija(id, adresa, drzava, grad, latitude, longitude, rentacar_id)
	values (1, "Brace Dronjak 10", "Srbija", "Novi Sad", 19, 46, 1);	
insert into isa_project.lokacija(id, adresa, drzava, grad, latitude, longitude, rentacar_id)
	values (2, "Brace Dronjak 12", "Srbija", "Novi Sad", 19, 46, 1);	

