
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
        for(var i=0; i<hoteli.length; i++)
        	if((hoteli.length == 0) || (hoteli==null)) {
        		html += '<tr>'+
					'<td align="center" style="font-size:18px;" >Ne postoje dostupni hoteli</td>'+
					'</tr>';
        	} else {
        		html += '<tr><td>' + hoteli[i].nazivHotela + '</td><td>' + hoteli[i].adresaHotela + '</td><td>' + hoteli[i].opisHotela + '</td><td><button style="background-color: #19b9e7;" class="btn btn-link-2" id="'+hoteli[i].id+'">Izmeni</td><td><button class="btn btn-link-2" id="'+hoteli[i].id+'">Obrisi</td></tr>';
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
    
 
});