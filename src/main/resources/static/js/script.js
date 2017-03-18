/**
 *
 */
$(document).ready(function(){
	
	$("#tipoAnuncio").change(function(){          
	    var valor = $("#tipoAnuncio option:selected").val();
	    if(valor === 'outro'){
	    	$("#input-tipo").slideDown().removeClass("hidden-input");
		    $("#tipoAnuncio option:selected").attr('disabled','disabled');
	    }
	});

	//setInputSearchAdvertisement();
});

function setInputSearchAdvertisement(){
	$("input[name=opcaoFiltro]").click(function() {          
	    var valor = $('input[name=opcaoFiltro]:checked').val();
	    if(valor === '1'){
	    	$('#datetimepicker1').datetimepicker({format: 'yyyy-mm-dd'});
	    	$('#inputSearchByTipo').css({"display":"none"});
	    	$('#datetimepicker1').css({"display":"block"});
	    	$('#datetimepicker1 input').css({"width":"90%"});
	    	$('#datetimepicker1 .input-group-addon').css({"height":"34px"});
	    	$('#datetimepicker1 .btn-search-date').css({"width":"20%", "display":"block", "margin":"0 auto", "margin-top":"10px"});
	    } else{
	    	$('#inputSearchByTipo').css({"display":"block"});
	    	$('#datetimepicker1').css({"display":"none"});
	    	$('#inputSearchByTipo input').css({"width":"90%"});
	    }
	});
}