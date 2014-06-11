(function(){
	if (TOKEN == '') return false;
	$.ajax({
	   type: "POST",
	   url: "/index.php",
	   data: {mode: 'api', token: TOKEN, action: 'getcs'},
	   async: false,
	   success: function(data){
			var obj = eval("(" + data + ")");
			var service1 = obj.domain + 'chatClient/floatButton.js?'+(obj.jid?('jid='+obj.jid):'')+'&companyID='+obj.companyID+'&configID='+((obj.domain.indexOf('kf800') >= 0)?31:12484)+'&codeType=custom'+(obj.info?('&info='+obj.info):'');
			var service2 = obj.domain + 'chatClient/monitor.js?'+(obj.jid?('jid='+obj.jid):'')+'&companyID='+obj.companyID+'&configID='+((obj.domain.indexOf('kf800') >= 0)?3:12476)+'&codeType=custom'+(obj.info?('&info='+obj.info):'');
			document.write('<script type="text\/javascript" src="' + service1 + '"><\/script>');
			//document.write('<textarea class="fragment_box" style="display: none;"><script src="'+ service1 +'" type="text/javascript"><\/script><\/textarea>');
			if(getCookie('chatMonitor') != ('chat' + TOKEN)){
				document.write('<script type="text\/javascript" src="' +service2 + '"><\/script>');
				//document.write('<textarea class="fragment_box" style="display: none;"><script src="' + service2 +'" type="text/javascript"><\/script><\/textarea>');
				SetCookie('chatMonitor', 'chat' + TOKEN, 1);
			}
	   }

	});
})();
$(function () {
	//$('textarea.fragment_box').imglazyload({
	//	loadScript : true
	//}); 
});