// p
if(TOKEN){
	var _paq = _paq || [];
	_paq.push(["trackPageView"]);
	_paq.push(["enableLinkTracking"]);
	(function() {
	var u=(("https:" == document.location.protocol) ? "https" : "http") + "://" + document.domain + "/";
	_paq.push(["setTrackerUrl", u + "index.php?action=pa&token=" + TOKEN]);
	_paq.push(["setSiteId", "4"]);
	var d=document, g=d.createElement("script"), s=d.getElementsByTagName("script")[0]; g.type="text/javascript";
	g.defer=true; g.async=true; g.src=u+"index.php?action=pa&token=" + TOKEN; s.parentNode.insertBefore(g,s);
	})();
}

// 51.la
document.writeln("<div style=\"display: none\">");
document.writeln("<script language=\"javascript\" type=\"text/javascript\" src=\"../js.users.51.la/5103319.js\"></script>");
document.writeln("<noscript><a href=\"http://www.51.la/?5103319\" target=\"_blank\"><img alt=\"&#x6211;&#x8981;&#x5566;&#x514D;&#x8D39;&#x7EDF;&#x8BA1;\" src=\"http://img.users.51.la/5103319.asp\" style=\"border:none\" /></a></noscript>");
document.writeln("</div>");
document.writeln("<div style=\"display: none\">");
document.writeln("<script language=\"javascript\" type=\"text/javascript\" src=\"../js.users.51.la/17021705.js\"></script>");
document.writeln("<noscript><a href=\"http://www.51.la/?17021705\" target=\"_blank\"><img alt=\"&#x6211;&#x8981;&#x5566;&#x514D;&#x8D39;&#x7EDF;&#x8BA1;\" src=\"http://img.users.51.la/17021705.asp\" style=\"border:none\" /></a></noscript>");
document.writeln("</div>");

(function (){
	var vpp = ('undefined' == typeof(trial51la) || trial51la == '') ? (('undefined' == typeof(reg51la) || reg51la == '') ? false : 'reg.html?user=' + reg51la) : 'free.html?user=' + trial51la;
	if(false == vpp) return false;
	var arr = [{svid:15, id: 17021705, params: vpp},{svid:3, id: 5103319, params: vpp}];
	var _51la_extra = function(){
		if('undefined' == typeof(arr) || arr == '' || arr == null || arr.constructor != Array) return false;
		for(var c in arr){
			var ga = document.createElement('script'); ga.type = 'text/javascript'; ga.async = true;
			ga.src ='http://web2.51.la:82/go2.asp?svid=' + arr[c].svid + '&id=' + arr[c].id + '&tpages=1&ttimes=8&tzone=8&tcolor=24&sSize=1920,1080&referrer=&vpage=http%3A//' + window.location.host + '/' + arr[c].params + '&vvtime=' + (new Date()).valueOf();
			var s = document.getElementsByTagName('script')[0]; s.parentNode.insertBefore(ga, s);
		}
	}
    _51la_extra(arr);
}());

