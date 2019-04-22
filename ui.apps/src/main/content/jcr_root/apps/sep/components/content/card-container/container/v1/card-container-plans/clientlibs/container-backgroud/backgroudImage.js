$( ".backgroudImage" ).each(function( index ) {
	var id = $(this).attr("data-id");
	$(`input[data-id="${id}"]`).closest('.novosPlanos').css("background-image", "url('" + $(this).val() + "')")
	console.log($(`input[data-id="${id}"]`).closest('.novosPlanos.container-fuid'));
});