var $imgEl = $('.backgroudImage');
var imgPath = $imgEl.val();
$imgEl.closest('.planos').css("background-image", "url('" + imgPath +"')"); 

