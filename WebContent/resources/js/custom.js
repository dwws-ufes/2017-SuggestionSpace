$(document).ready(function(){
	if($(".field-phone").length){
		$(".field-phone").focusout(function(){
		    var phone, element;
		    element = $(this);
		    element.unmask();
		    phone = element.val().replace(/\D/g, '');
		    if(phone.length > 10) {
		        element.mask("(99) 99999-999?9");
		    } else {
		        element.mask("(99) 9999-9999?9");
		    }
		}).trigger('focusout');
	}
	if($(".field-date").length){
		$(".field-date").mask("99/99/9999");
	}
	if($(".field-datetime").length){
		$(".field-datetime").mask("99/99/9999 99:99");
	}
	if($('[data-toggle="tooltip"]').length){
		$('[data-toggle="tooltip"]').tooltip(); 
	}
});