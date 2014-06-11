// 加入收藏 
function AddFavorite(url, title){
	var ua = navigator.userAgent.toLowerCase();
	if (ua.indexOf("360se") > -1) {
		alert("由于360浏览器功能限制，请按 Ctrl+D 手动收藏！");
	}else{
		try {
			window.external.addFavorite(url, title);
		}catch (e) {
			try {
				window.sidebar.addPanel(title, url, "");
			}catch (e) {
				alert("\n\t对不起，您的浏览器不支持此操作！\n\n\t请您使用菜单栏或ctrl+D收藏本站。");
			}
		}
	}
	return false;
}

// 找回密码
function getOS(){
	if (TOKEN == '' || typeof(TOKEN) == 'undefined') return false;
	window.open('http://chat32.live800.com/live800/chatClient/chatbox.jsp?companyID=129467&amp;jid=6260235038&amp;enterurl=http%3A%2F%2Fgcgc.cc%2Fzh%2F&amp;pagereferrer=&amp;tm=1319636373735');
}

// 登出
function ajax_logout(){
	if (TOKEN == '' || typeof(TOKEN) == 'undefined') return false;
	if(confirm("您确定要退出吗？")){			
		$.post("index-2.html",
			  {mode: 'api', token: TOKEN, action: 'logout'},
			  function(data){
				var obj = eval("(" + data + ")");
				if(obj.code == 1){
					window.location.href = 'http://' + window.location.host+"/?2" + '.' + Math.floor(Math.random()*(1024-9172)+9172);
				}
		});
	}
}

function logout(){
	if (TOKEN == '' || typeof(TOKEN) == 'undefined') return false;			
	$.post("index-2.html",
		  {mode: 'api', token: TOKEN, action: 'logout'},
		  function(data){
			var obj = eval("(" + data + ")");
			if(obj.code == 1){
				window.location.href = 'http://' + window.location.host+"/?2" + '.' + Math.floor(Math.random()*(1024-9172)+9172);
			}
	});

}

// 登录
function login(){
	var token = $("#formhash").val();
	var un = $.trim($("#uname").val());
	var pwd = $.trim($("#pwd").val());
	var captcha = $("#captcha").val();
	var pc = $("#pc").attr("checked") ? 1 : 0;
	if (token == '') return false;
	if(un.length == 0){
		alert('用户名不能为空，请输入用户名。');
		return false;
	}
	if(pwd.length == 0){
		alert('密码不能为空，请输入密码。');
		return false;
	}
	if($('#verify').attr('data-available') == 1 && captcha.length == 0){
		alert('验证码不能为空，请输入验证码。');
		return false;
	}
	$('.login-button').attr('disabled', 'disabled').val('登录中...');
	$.post("index-2.html",
		  {mode: 'api', token: token, action: 'login', username: un, password: pwd, secode: captcha, persistentcookie: pc},
		  function(data){
			var obj = eval("(" + data + ")");
			if(obj.code == 8){
				$('.login-button').val('登录成功');
				window.location.href = 'http://' + window.location.host+"/?1" + '.' + Math.floor(Math.random()*(1024-9172)+9172);
				return true;
			}else if(obj.code == 1){
				updatecaptcha(token);
				$("#un")[0].focus();
				alert('用户名不存在');
			}else if(obj.code == 2){
				updatecaptcha(token);
				$("#pwd").val('');
				$("#pwd")[0].focus();
				alert('您输入的帐号或者密码有误，请重新输入。');
			}else if(obj.code == -1){
				$('#verify').attr('data-available', 1).css('display', 'block');
				updatecaptcha(token);
				alert('验证码错误，请重新输入。');
			}

			if('undefined' !== obj.errorcouter && obj.errorcouter == 3 && $('#verify').attr('data-available') == 0){
				$('#verify').attr('data-available', 1).css('display', 'block');
				updatecaptcha(token);
			}
			$('.login-button').attr('disabled', false).val('登 录');
			return false;
	});
}

// 更新验证码
function updatecaptcha(token){
	if (token == '' || typeof(token) == 'undefined') return false;
	$('#verifycodeimage').html('<img width="65" height="26" border="0" style="vertical-align:middle; margin-top: -4px;display: inline; cursor: pointer;" onclick="updatecaptcha(' + token + ')" src="/index.php?mode=api&token=' + token + '&action=getverifycode&type=login&rand=' + Math.random() + '" alt="验证码">');
	$("#captcha").val('');
	try{$("#captcha")[0].focus();}catch(e){}
}

function unix_to_datetime(unix) {
	var now = new Date(parseInt(unix) * 1000);
	return now.toLocaleString().replace(/年|月/g, "-").replace(/日/g, " ");
}

function strstartwidth(str, substr){ 
	if(substr == null|| substr == "" || str.length == 0 || substr.length > str.length)
		return false;
	if(str.indexOf(substr, 0) === 0)
		return true;
	else
		return false;
	return true;
}

function strlength7_10(str){  
	 var patrn=/^(\w){7,10}$/;  
	 return patrn.test(str);  
}

function SetCookie(name, value, Days){
    var exp  = new Date();
    exp.setTime(exp.getTime() + Days*24*60*60*1000);
    document.cookie = name + "=" + escape(value) + "; expires=" + exp.toGMTString() + "; path=/";
}

function getCookie(name){
    var arr = document.cookie.match(new RegExp("(^| )" + name + "=([^;]*)(;|$)"));
    if(arr != null) 
    return unescape(arr[2]);
    else 
    return null;
}

function playdes(pid){
	if (pid == '' || typeof(pid) == 'undefined') return false;
	 $.zxxbox($('<iframe frameborder="0" width="720" height="500" style="padding: 10px;" src="/post/' + pid + '/"></iframe>'), {fix:true, title: "玩法说明"});
}
	
function poplogin(id){
	var elements = $('<div id="popup_content" class="popup-html-content">'
	+ '<div class="popup-login">'
	+ '<form autocomplete="off" method="post" action="/index.php" onsubmit="return false;">'
	+ '<p class="error" id="pMessage"></p>'
	+ '<p class="username"><label for="pUsername">用户名：</label><input type="text" placeholder="输入账号 vg**" maxlength="10" id="pUsername"></p>'
	+ '<p class="password"><label for="pPassword">密&nbsp;&nbsp;码：</label><input type="password" placeholder="输入密码" id="pPassword"></p>'
	+ '<p style="display: none;" id="pop_vc" data-available="0"><label for="pVerificationCode">验证码：</label><input type="text" style="border:none; padding-left: 2px; width: 64px;" placeholder="验证码" maxlength="4" id="pVerificationCode" class="login-vc"><img width="16" height="16" id="codeLoading" src="/theme/images/code-loading.gif"><img width="65" border="0" height="26" alt="验证码" available="0" src="" id="pImgCode" title="看不清楚？点击换另一张" style="vertical-align:top; cursor:pointer; display: none;"></p>'
	+ '<p style="padding-left:52px; position: relative; _width: 202px;" class="font12 cf"><label style="width: 120px;_height: 25px;*padding-top:0px;_padding-top: 7px;" for="pPersistentCookie"><input type="checkbox" class="login-checkbox" id="pPersistentCookie"><span class="font12">下次自动登录</span></label><a onclick="getOS();" href="javascript: void(0);" hidefocus="true" style="position: absolute; right: 4px; top: 10px;">忘记密码?</a></p>'
	+ '<p class="font12"><input type="submit" value="登录" style="_padding-top: 4px;" class="popbtn mr10" hidefocus="true" title="登&nbsp;录" id="pBtnSubmit" /></p>'
	+ '</form>'
	+ '<p style="margin-top: 24px;" class="font12 cf"><span>还没有黄金城账户？</span><a href="/reg/" class="redlink"><b>直接开户</b></a>&nbsp;&nbsp;<a href="/try/" class="redlink"><b>免费试玩</b></a></p></div></div>');
	$.zxxbox(elements, {onshow: function(){
		$("#pBtnSubmit").click(function() {
			var token = $("#formhash").val();
			var un = $.trim($("#pUsername").val());
			var pwd = $.trim($("#pPassword").val());
			var captcha = $("#pVerificationCode").val();
			var pc = $("#pPersistentCookie").attr("checked") ? 1 : 0;
			if (token == '') return false;
			if(un.length == 0){
				alert('用户名不能为空，请输入用户名。');
				return false;
			}
			if(pwd.length == 0){
				alert('密码不能为空，请输入密码。');
				return false;
			}
			if($('#verify').attr('data-available') == 1 && captcha.length == 0){
				alert('验证码不能为空，请输入验证码。');
				return false;
			}
			$('#pBtnSubmit').attr('disabled', 'disabled').val('正在登录...');
			$.post("index-2.html",
				  {mode: 'api', token: token, action: 'login', username: un, password: pwd, secode: captcha, persistentcookie: pc},
				  function(data){
					var obj = eval("(" + data + ")");
					if(obj.code == 8){
						$('#pBtnSubmit').val('登录成功');
						if (id && parseInt(id) == id){
							window.location.href = 'http://' + window.location.host+'/join/' + id;
						}else{
							window.location.href = 'http://' + window.location.host+"/?1" + '.' + Math.floor(Math.random()*(1024-9172)+9172);
						}
						return true;
					}else if(obj.code == 1){
						updatecaptcha(token);
						$("#pUsername")[0].focus();
						alert('用户名不存在');
					}else if(obj.code == 2){
						updatecaptcha(token);
						$("#pPassword").val('');
						$("#pPassword")[0].focus();
						alert('您输入的帐号或者密码有误，请重新输入。');
					}else if(obj.code == -1){
						$('#pop_vc').attr('data-available', 1).css('display', 'block');
						updatecaptcha(token);
						alert('验证码错误，请重新输入。');
					}

					if('undefined' !== obj.errorcouter && obj.errorcouter == 3 && $('#pop_vc').attr('data-available') == 0){
						$('#pop_vc').attr('data-available', 1).css('display', 'block');
						updatecaptcha(token);
					}
					$('#pBtnSubmit').attr('disabled', false).val('登 录');
					return false;
			});
		});
		// placeholder
		$('#popup_content input').placeholder();
	}, width: 360, fix:true, drag: true, title: '会员登录'});
}

$(function(){
	$('.poplogin').each(function(){
		var id = $(this).attr('data-id');
		$(this).click(function(){
			poplogin(id);
			});
	});
	//查询余额
	$('#balance').click(function() {
		$(this).html('正在查询');
		var token = TOKEN;
		if (token == '') return false;
		$.post("index-2.html", 
			{mode: 'api', token: token, action: 'getbalance'}
		)
		.done(function(data) {
			$('#balance').html('' + (data == '0' ? '0.00' : data) + ' 元');
			//alert( "Data Loaded: " + data );
		}) 
		.fail(function() {
			$('#balance').html('显示余额');
		});
	});

	//进入游戏下拉菜单
	$('#drop-menu').hover(function() {
		$('#drop-menu dd').stop(true).slideDown(240);
	}, function() {
		$('#drop-menu dd').stop(true).slideUp(240);		
	});

	// 确认框、关闭
	$(".tips-recharge").click(function(){   
		$.zxxbox.remind('<p>试玩账号无法充值，请注册真钱账户进行充值!</p><p>立即去 <a href="/reg/" style="text-decoration:underline;" target="_blank">申请真钱账户</a></p>', null, {
			drag: true,
			fix: true,
			remind: true,
			title: "黄金城娱乐场提示您"      
		});
		$('#zxxSubmitBtn').html('关闭'); 
	});
});