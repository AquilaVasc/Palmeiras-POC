$( ".backgroudImage" ).each(function( index ) {
	var id = $(this).attr("data-id");
	$(`input[data-id="${id}"]`).closest('.planos').css("background-image", "url('" + $(this).val() + "')")
});