
$(document).ready(function () {

	idKor = 1;
	$.ajax({
		async: false,
		url: "http://localhost:5050/korisnik/getById/"+idKor,
        type: "GET",
        dataType: "json",
        success: function (data) {
        	var tabelaKorisnik = $("#tabelaKorisnik");
        	var redIme = "<tr><td>Ime</td><td><input type=\"text\" id=\"ime"+data.idKorisnika+"\" value = \""+data.ime+"\"/></td></tr>";
        	var redPrezime = "<tr><td>Prezime</td><td><input type=\"text\" id=\"prezime"+data.idKorisnika+"\" value = \""+data.prezime+"\"/></td></tr>";
        	var redKorisnickoIme = "<tr><td>Korisnicko ime</td><td><input type=\"text\" id=\"korisnickoIme"+data.idKorisnika+"\" value = \""+data.korisnickoIme+"\"/></td></tr>";
        	var dugmeZaSubmit = "<tr><td colspan=\"2\"><button class=\"btn btn-primary\" onclick = \"potvrdiIzmene(\'"+data.idKorisnika+"\')\">Potvrdi</button></td></tr>";
        	tabelaKorisnik.append(redIme);
        	tabelaKorisnik.append(redPrezime);
        	tabelaKorisnik.append(redKorisnickoIme);
        	tabelaKorisnik.append(dugmeZaSubmit);
        }
   });

});

function potvrdiIzmene(idKorisnik){
	var imeKorisnika = $("#ime"+idKorisnik).val();
	var prezimeKorisnika = $("#prezime"+idKorisnik).val();
	var korisnickoIme = $("#korisnickoIme"+idKorisnik).val();
	
	var data = JSON.stringify({
		"idKorisnika" : idKorisnik,
		"ime" : imeKorisnika,
		"prezime" : prezimeKorisnika,
		"korisnickoIme" : korisnickoIme
	});
	
	$.ajax({
		async: false,
		url: "http://localhost:5050/korisnik/izmeniPodatke",
        type: "POST",
        dataType: "json",
        data: data,
        contentType: "application/json",						
        success: function (data) {
        	location.reload();
        }
   });
}