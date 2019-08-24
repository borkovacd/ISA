insert into isa_project.korisnik (id, email, grad, ime, korisnicko_ime, lozinka, prezime, prvo_logovanje, telefon, verifikovan, uloga) 
	values (1, "admin@gmail.com", "Novi Sad", "Admin",  "admin", "8c6976e5b5410415bde908bd4dee15dfb167a9c873fc4bb8a81f6f2ab448a918", "Admin", false, "0600000000", true, "ADMINISTRATOR_SISTEMA");
insert into isa_project.korisnik (id, email, grad, ime, korisnicko_ime, lozinka, prezime, prvo_logovanje, telefon, verifikovan, uloga) 
	values (2, "borkovac.dragan@gmail.com", "Novi Sad", "Dragan",  "dragan", "8c6976e5b5410415bde908bd4dee15dfb167a9c873fc4bb8a81f6f2ab448a918", "Borkovac", false, "0605959769", true, "ADMINISTRATOR_HOTELA");
insert into isa_project.korisnik (id, email, grad, ime, korisnicko_ime, lozinka, prezime, prvo_logovanje, telefon, verifikovan, uloga) 
	values (3, "savic.olga@gmail.com", "Novi Sad", "Olga",  "olga", "8c6976e5b5410415bde908bd4dee15dfb167a9c873fc4bb8a81f6f2ab448a918", "Savic", false, "06345624543", true, "OBICAN_KORISNIK");
insert into isa_project.korisnik (id, email, grad, ime, korisnicko_ime, lozinka, prezime, prvo_logovanje, telefon, verifikovan, uloga) 
	values (4, "djurkovic.masa@gmail.com", "Novi Sad", "Masa",  "masa", "8c6976e5b5410415bde908bd4dee15dfb167a9c873fc4bb8a81f6f2ab448a918", "Djurkovic", false, "0604561231", true, "OBICAN_KORISNIK");

insert into isa_project.hotel (id, adresa, naziv, opis, administrator_id) 
	values (1, "Novosadskog Sajma 35, Novi Sad", "Hotel Park", "Hotel Park je smešten na obodu velikog parka u Novom Sadu. Ovaj potpuno klimatizovan hotel u ponudi ima elegantno opremljene smeštajne jedinice sa besplatnim internetom i TV-om sa kablovskim kanalima.", 2);
insert into isa_project.hotel (id, adresa, naziv, opis, administrator_id) 
	values (2, "Bulevar Nikole Tesle 3, Beograd", "Jugoslavija", "Hotel „Jugoslavija”, otvoren 1969. godine, bio je jedan od prva tri značajna objekta koji su planirani u Novom Beogradu, pored zgrade CK KPJ i zgrade Predsedništva vlade FNRJ.", 2);

insert into isa_project.soba (id, ima_balkon, kapacitet, rezervisana, sprat, tip_sobe, hotel_id) 
	values (1, 0, 4, 0, 2, "APARTMAN", 1);
insert into isa_project.soba (id, ima_balkon, kapacitet, rezervisana, sprat, tip_sobe, hotel_id) 
	values (2, 1, 2, 0, 5, "JEDNOKREVETNA_SOBA", 1);
	
insert into isa_project.cenovnik_hotela (id, pocetak_vazenja, prestanak_vazenja, hotel_id) 
	values (1, DATE '2019-06-01', DATE '2019-09-01', 1);


