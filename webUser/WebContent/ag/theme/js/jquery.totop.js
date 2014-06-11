$(function() {
	$.fn.toTop = function(options) {
		var defaults = {           
			showHeight : 150,
			contentWidth : 980,
			topWidth : 71,
			speed : 1000
		};
		var options = $.extend(defaults,options);
		$("body").prepend("<div id='totop'><a>返回</a></div>");
		var $toTop = $(this);
		var $top = $("#totop");
		var $ta = $("#totop a");
		$toTop.scroll(function(){
			var scrolltop=$(this).scrollTop();     
			if(scrolltop>=options.showHeight){
				var ww = $(window).width();
				var posRight = 0;
				posRight = (ww - options.contentWidth)/2 - options.topWidth;
				$top.css({right: posRight + "px", bottom: "40px"});
				$top.show();
			}
			else{
				$top.hide();
			}
		});
		$ta.hover(function(){      
			$(this).addClass("cur");   
		},function(){          
			$(this).removeClass("cur");    
		});
		$top.click(function(){
			$("html,body").animate({scrollTop: 0}, options.speed); 
		});
	}
});