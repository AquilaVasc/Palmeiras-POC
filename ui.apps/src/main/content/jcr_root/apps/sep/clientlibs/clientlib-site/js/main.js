function elementExists(element) {
    return $(element).html() != undefined;
}

function isFunction(possibleFunction) {
    return typeof possibleFunction === "function";
}

function showScores() {
    $(".member-score").html('');
    
    $.ajax({
        url: '/score/get-scores',
        method: 'post',
        data: {},
        dataType: 'json',
        success: function (response) {

            if (response === null || response.scores === null) {
                $("#show-scores").html('');
            } else {                
                var scoreData = "<span class='member-score'>";
                scoreData += "CLUBE DE VANTAGENS: ";
                scoreData +=  response.scores;
                 scoreData += " PONTO(S)";
                scoreData += "</span>";
                
                $("#show-scores").html(scoreData);                                                                                                
            }
        },
        error: function () {
            $(".member-score").html('');
        }
    });
}

function verifyValuation() {

    $('#opinionModal').hide();
    
    $.ajax({
        url: '/survey/verify-valuation',
        method: 'post',
        data: {},
        dataType: 'json',
        success: function (response) {
        	if (response.rating === null) {
        	    $('#opinionModal').fadeIn(700);
        	} else {
        		$('#opinionModal').hide();
        	}
        },
        error: function () {
        	$('#opinionModal').hide();
        }
    });
}

function showModalStateNotPermitted() {

	$('#stateNotPermittedModal').fadeIn(700);
    
}

$(document).ready(function () {

    AOS.init();

    showScores();

    $('.responsive_link').click(function () {
        $('.responsive_nav').toggleClass('open');
    });

    let carouselLength = $('.new_carousel').find('.item').length; 
    if(carouselLength > 1) {
	    $('.new_carousel').slick({
	        prevArrow: '.prev-arrow',
	        nextArrow: '.next-arrow',
	        dots:true,
	        infinite: true,
	        autoplay: true,
	        arrows: true,
	        responsive: [
	        { 
	            breakpoint: 576,
	            settings: {
	                dots: false,
	            }
	
	        }]
	    });
	    $('.banner_rotativo').show();
    } else if(carouselLength == 1) {
	    $('.banner_rotativo').show();
    }

    $('.list_parceiros').slick({
        slidesToShow: 4,
        slidesToScroll: 1,
        autoplay: true,
        autoplaySpeed: 2000,
        prevArrow: '#prev-arrow',
        nextArrow: '#next-arrow',
        infinite: true,
        responsive: [
        {
            breakpoint: 576,
            settings: {
                slidesToShow: 1,
            }
        }]

    });

    $('.has-error').tooltip();
}); 

// Hidding antecipate message
$('#antecipate_message').hide();

$(document).ready(() => {

    $('#stars li').on('mouseover', function(){
    
        const onStar = parseInt($(this).data('value'), 10); // The star currently mouse on
       
        $(this).parent().children('li.star').each(function(e){

            if (e < onStar) {
                $(this).addClass('checked');
            }
            else {
                $(this).removeClass('checked');
            }
        });
    
    }).on('mouseout', function(){

        $(this).parent().children('li.star').each(function(e){
            $(this).removeClass('checked');
        });

    });


    $('#stars li').on('click', function(){

        const onStar = parseInt($(this).data('value'), 10); // The star currently selected
        const stars = $(this).parent().children('li.star');
    
        for (i = 0; i < stars.length; i++) {
            $(stars[i]).removeClass('selected');
        }

        for (i = 0; i < onStar; i++) {
            $(stars[i]).addClass('selected');
        }
    
        const ratingValue = $('#stars li.selected').last().data('value');
        let msg = "";
    
        if (ratingValue == 2) {
            msg = "Regular";
            selectStar = 2;
        } else if(ratingValue == 3) {
            msg ="Bom";
            selectStar = 3;
        } else if(ratingValue == 4) {
            msg ="Muito bom!";
            selectStar = 4;
        } else if(ratingValue == 5) {
            msg ="Excelente!";
            selectStar = 5;
        }else if(ratingValue < 2){
            msg = "Ruim :(";
            selectStar = 1;
        }

        $('#ratingSelected').val(ratingValue);

        finalValue(msg);
    });

    $('#ratingOpinion').on('focus', function(){
    	
    	selectStar = $('#stars li.selected').last().data('value');

        if (! selectStar) {
	    	msg ="Escolha abaixo";
	    	finalValue(msg);
	    	return;
        }
        
    });

    $('#popUp_send').on('click', function(){
    	
    	selectStar = $('#stars li.selected').last().data('value');
        textareaValue = $(this).parents().find('.popUp_wrapper--textarea').val();
        
        if (! selectStar) {
        	msg ="Necessário escolher uma avaliação!";
        	finalValue(msg);
        	return false;
        }

		$.ajax({
			url : '/survey/rating',
			method : 'post',
			data : {
				'rating_value' : selectStar,
				'rating_opinion'  : textareaValue
			},
			dataType : 'json',
			success : function(json) {

		        $('.popUp_wrapper--content')
		        .html(
		            "<h2>Obrigado!</h2>" +
		            "<p>Sua opinião é muito importante para nós!<br><br>" +
		            "<small>Essa janela fechará em 5 segundos</small></p>"
		            //"<br><br>rating: " + selectStar + // Valor das estrelas
		            //"<br><br>opinion: " + textareaValue // Conteudo do text area
		         );

		        $('.popUp_links').hide();
		        setTimeout(function(){
                    $('#opinionModal').fadeOut(500);
                }, 5000);

			},
			error : function() {

		        $('.popUp_wrapper--content')
		        .html(
		            "<h2>Erro!</h2>" +
		            "<p>Ocorreu um erro ao enviar sua avaliação, tente novamente mais tarde!<br><br>" +
		            "<small>Essa janela fechará em 5 segundos</small></p>"
		         );

		        $('.popUp_links').hide();
		        setTimeout(function(){
                    $('#opinionModal').fadeOut(500);
                }, 5000);

			}
		});
		
    });
    
    /* Verifica se já existe avaliação para o sócio, caso sim, não habilita modal de avaliação */
    verifyValuation();

    /* Mostra mensagem se estado não é permitido para o cadastro do usuário */
    showModalStateNotPermitted();

});

finalValue = (msg) => {
    $('#rating_result').animate({opacity: 1});  
    $('#rating_result').html("<span>" + msg + "</span>");
}


$('.saiba_mais--toggle').on('click', function() {

    $(this).parents('.item').find('.saiba_mais').toggleClass('active');

});

$('.radio-item input[type="radio"]').on('change', function(){

    $('.radio-item').removeClass('active');

    if($(this).prop('checked', true)) {
        $(this).parent().parent().parent().toggleClass('active');
    }


});