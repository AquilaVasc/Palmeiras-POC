(function (document, $, ns) {
	"use strict";
	
	$(document).on("click", ".cq-dialog-submit", function(e) {
		if ($(".dialog-class-sep-partners").length > 0 && $('.dialog-class-sep-partnersList').length < 5) {
			e.stopPropagation();
			e.preventDefault();
							
			var message = Granite.I18n.get("partnersList.minMessage");

			ns.ui.helpers.prompt({
				title : Granite.I18n.get("Warning"),
				message : message,
				type : "WARNING",
				actions : [{
					id : "CANCEL",
					text : "OK",
					className : "coral-Button"
				}],
				callback : function(actionId) { }
			});
		} 
	});
})(document, Granite.$, Granite.author);