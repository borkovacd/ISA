
$(document).ready(function() {
	
	$("small").each(function(index, el) {
        $(this).hide();
    });
	
	//tabovi
    $(".nav-link").click(function(event) {

       $(".container-fluid").each(function(index, el) {
            $(this).hide();
        });
        var id = $(this).attr("id");
        var selektor;
        if(id === "tabLinkH") {
                selektor = "#hoteliTab";
                $("#tabLinkH").addClass('active');
                $("#tabLinkAK").removeClass('active');
                $("#tabLinkR").removeClass('active');
                $("#tabLinkK").removeClass('active');
                $(selektor).show();
        } else if (id === "tabLinkAK"){
             selektor = "#aviokompanijeTab"
             $("#tabLinkH").removeClass('active');
             $("#tabLinkAK").addClass('active');
             $("#tabLinkR").removeClass('active');
             $("#tabLinkK").removeClass('active');
             $(selektor).show();
        }  else if (id === "tabLinkK"){
             selektor = "#korisniciTab"
             $("#tabLinkH").removeClass('active');
             $("#tabLinkAK").removeClass('active');
             $("#tabLinkR").removeClass('active');
             $("#tabLinkK").addClass('active');
             $(selektor).show();
        }   else if (id === "tabLinkR"){
            selektor = "#rentservisiTab"
                $("#tabLinkH").removeClass('active');
                $("#tabLinkAK").removeClass('active');
                $("#tabLinkK").removeClass('active');
                $("#tabLinkR").addClass('active');
                $(selektor).show();
           }   
    });  
    
    //otvaranje podrazumevanog taba
    $("#tabLinkAK").trigger('click');
    
    /**********************************RAD SA HOTELIMA ************************/
    
    //preuzimanje hotela i dodavanje u tabelu   
    $.get({
          url:'http://localhost:5050/hoteli',
            contentType: 'application/json',
            success: function(data) {
                if(data){
                    upisHotela(data);
                }
            },
        });
    
    //upis hotela u tabelu
    function upisHotela(hoteli){
    	$('#tabelaHoteli tr').not(':first').remove();
    	var html = '';
    	var pom = '';
        for(var i=0; i<hoteli.length; i++)
        	if((hoteli.length == 0) || (hoteli==null)) {
        		html += '<tr>'+
					'<td align="center" style="font-size:18px;" >Ne postoje dostupni hoteli</td>'+
					'</tr>';
        	} else {
        		if(hoteli[i].adminHotela == null) {
        			pom = '<td><button class="btn btn-link-2" id="'+hoteli[i].id+'">Registruj admina</td>'
        		} else {
        			pom = '<td>' + hoteli[i].adminHotela + '</td>';
        		}
        		
        		html += '<tr><td>' + hoteli[i].nazivHotela + '</td><td>' + hoteli[i].adresaHotela + '</td><td>' + hoteli[i].opisHotela + '</td>' + 
        				pom + 
        		      	'<td><button style="background-color: #19b9e7;" class="btn btn-link-2" id="'+hoteli[i].id+'">Izmeni</td><td><button class="btn btn-link-2" id="'+hoteli[i].id+'">Obrisi</td></tr>';
        	}
        $('#tabelaHoteli tr:first').after(html);  
        }
    
    //klik na dugme za dodavanje novog hotela
    $("#dodajHotelButton").click(function(event) {
		/* Act on the event */
    	event.preventDefault();
		var naziv = $("#nazivHotela");
		var adresa = $('#adresaHotela');
		var opis = $('#opisHotela');
		
		if(!naziv.val().trim() || !adresa.val().trim() || !opis.val().trim()){

				if(!naziv.val().trim()){
					naziv.addClass('bg-danger');
					
				}
				if(!adresa.val().trim()){
					adresa.addClass('bg-danger');
				}
				if(!opis.val().trim()){
					opis.addClass('bg-danger');
				}
		} else {
			
			var hotel = new Object();
			hotel.nazivHotela = naziv.val();
			hotel.adresaHotela = adresa.val();
			hotel.opisHotela = opis.val();
			
			 $.post({
                 url:'http://localhost:5050/dodajHotel',
                 data: JSON.stringify(hotel),
                 contentType: 'application/json',
                 success: function(data){
                     $.get({
                         url:'http://localhost:5050/hoteli',
                         contentType: 'application/json',
                         success: function(data) {
                         if(data){
                             upisHotela(data);
                             ocistiDodavanjeHotela();
                             
                         }
                      },
                  });
                 },
                 error: function(data) {
                     $('#greskaUnosHotela').text("Ime i adresa hotela već postoje").show().delay(3000).fadeOut();
                	 
                 }
             }); 
		}
    });
    
    //klik na dugme za brisanje ili izmenu hotela u tabeli
    $("#tabelaHoteli").on('click', 'button', function(event) {
        var text = $(event.target).text();
        var id = $(this).attr("id");
        if(text === "Izmeni"){
            $.get({
                url:'http://localhost:5050/preuzmiHotel/'+id,
                contentType: 'application/json',
                success: function(data) {
                    if(data){
                    	popuniFormuZaIzmenuHotela(data);
                    }
                }
            });
        } else if(text == "Registruj admina") {
        	$.get({
                url:'http://localhost:5050/preuzmiHotel/'+id,
                contentType: 'application/json',
                success: function(data) {
                    if(data){
                    	popuniFormuZaRegistrovanjeAdminaHotela(data);
                    	
                    }
                }
            });
        } else if(text === "Obrisi"){
        	$.ajax({
            	url:'http://localhost:5050/obrisiHotel/'+id,
            	type: 'DELETE',
                async:'true',
                contentType: 'application/json',
                success: function(data) {
		                    $.get({
		                    	url:'http://localhost:5050/hoteli',
		                        contentType: 'application/json',
		                        success: function(data) {
		                        		if(data){
		                        			upisHotela(data);
		                        		}
		                        },
		                   });
                }  
            });
        }
    });
    
    //klik na dugme za izmenu postojeceg hotela
    $("#izmeniHotelButton").click(function(event) {
		/* Act on the event */
    	event.preventDefault();
		var naziv = $("#nazivHotelaPH");
		var adresa = $('#adresaHotelaPH');
		var opis = $('#opisHotelaPH');
		var id = $('#idHotelaPH');
		
		if(!naziv.val().trim() || !adresa.val().trim() || !opis.val().trim()){

				if(!naziv.val().trim()){
					nazivHotelaPH.addClass('bg-danger');
				}
				if(!adresa.val().trim()){
					adresaHotelaPH.addClass('bg-danger');
				}
				if(!opis.val().trim()){
					opisHotelaPH.addClass('bg-danger');
				}
		} else {
			
			var restoran = new Object();
			var hotel = new Object();
			hotel.nazivHotela = naziv.val();
			hotel.adresaHotela = adresa.val();
			hotel.opisHotela = opis.val();
			hotel.id = id.val();
			
			
			$.ajax({
				url:'http://localhost:5050/izmeniHotel',
				type: 'PUT',
                data: JSON.stringify(hotel),
                contentType: 'application/json',
                success: function(data){
                    
                    $.get({
                    	url:'http://localhost:5050/hoteli',
                        contentType: 'application/json',
                        success: function(data) {
                        if(data){
                            upisHotela(data);
                            ocistiIzmenaHotela();
                        }
                      },
                   });
                },
                error: function(data) {
                	$('#greskaIzmenaHotela').text("Ime i adresa hotela već postoje").show().delay(3000).fadeOut();                    }
            });
		}
    });
    
    function popuniFormuZaIzmenuHotela(data) {
    	$('#nazivHotelaPH').val(data.nazivHotela);
    	$('#adresaHotelaPH').val(data.adresaHotela);
    	$('#idHotelaPH').val(data.id);
    	$('#opisHotelaPH').val(data.opisHotela);
    	
    }
    
    function popuniFormuZaRegistrovanjeAdminaHotela(data) {
    	$('#nazivHotelaAdmina').val(data.nazivHotela);
    	$('#idHotelaAdmina').val(data.id);
    	
    }
    
    //ciscenje polja za dodavanje hotela
    function ocistiDodavanjeHotela(){
        $("#nazivHotela").val("");
        $("#adresaHotela").val("");
        $("#opisHotela").val("");
    }
    
    //ciscenje polja za izmenu hotela
    function ocistiIzmenaHotela(){
        $("#nazivHotelaPH").val("");
        $("#adresaHotelaPH").val("");
        $("#opisHotelaPH").val("");
    }
    
    /********************************** KRAJ RAD SA HOTELIMA ************************/
    
    /***************************** RAD SA KORISNICIMA *******************************/
    
    //preuzimanje korisnika
    $.get({
    	url:'http://localhost:5050/korisnik/preuzmiSve',
    	contentType: 'application/json',
    	success: function(data) {
    		if(data) {
    			upisKorisnika(data);
    		}
    	},
    });
    
    
    //funkcija za upis korisnika u tabelu
    function upisKorisnika(korisnici) {
    	$('#tabelaKorisnici tr').not(':first').remove();
    	var html = '';
    	for(var i=0; i<korisnici.length; i++) {
    		html += 
    			'<tr>'+
    			'<td>'+ korisnici[i].korisnickoIme +'</td>'+
    			'<td>'+ korisnici[i].ime +'</td>'+
    			'<td>'+ korisnici[i].prezime+'</td>'+
    			'<td>'+ korisnici[i].telefon+'</td>'+
    			'<td>'+ korisnici[i].email+'</td>'+
    			'<td>'+ korisnici[i].uloga+'</td>'+
    			'<td><button class="btn btn-link2" id="'+korisnici[i].idKorisnika+'">Promeni tip</button></td>'+
    			'</tr>';
    	}
    	$('#tabelaKorisnici tr:first').after(html);
    }
    
    //klik na dugme 'Promeni tip' u tabeli 
    $("#tabelaKorisnici").on('click','button',function(event) {
    	var id = $(this).attr("id");
    	$("#idK").val(id);
    	var red = $(this).parent().parent();
    	var ime = red.children('td').first().text();
    	$("#imeK").val(ime);
    	var uloga = red.children('td').first().next().next().next().next().next().text();
    	$("#tipK").val(uloga);
    	
    });
    
    
    //klik na dugme 'Promeni' u formi za izmenu
    $("#promenaTipaButton").on('click',function(event) {
    	event.preventDefault();
    	var idKorisnika = $("#idK").val();
    	var korisnickoIme = $("#imeK").val();
    	var tip = $("#tipK").val();
    	if(korisnickoIme) {
    		var k = new Object();
    		k.idKorisnika = idKorisnika;
    		k.korisnickoIme = korisnickoIme;
    		k.uloga = tip;
    		
    		$.post({
    			url:'http://localhost:5050/korisnik/promeniTipKorisnika',
    			contentType: 'application/json',
    			data: JSON.stringify(k),
    			success: function(data) {
    				$.get({
    					url:'http://localhost:5050/korisnik/preuzmiSve',
    					contentType: 'application/json',
    					success: function(data) {
    						if(data) {
    							upisKorisnika(data);
    						}
    						$("#idK").val("");
    						$("#imeK").val("");
	    					$("#tipK").val("Korisnik");
    					}
    				}); 
    			}
    		});
    	}
    });
    
    /*************************** KRAJ RAD SA KORISNICIMA ****************************/
    
 
});