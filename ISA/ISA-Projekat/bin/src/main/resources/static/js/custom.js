
$(document).ready(function () {

	idKor = 1;
	$.ajax({
		async: false,
		url: "http://localhost:5050/korisnik/getById/"+idKor,
        type: "GET",
        dataType: "json",
        success: function (data) {
        	var p = document.getElementById("idPar");
        	p.innerHTML = "CAO " + data.ime;
        }
   });

});