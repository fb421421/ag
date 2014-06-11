function updateseccode() {
	var token = $("#formhash").val();
	if (token == '') return false;
	$('#verifycode').html('<img width="65" height="26" border="0" style="vertical-align:middle; margin-top: -4px;display: inline; cursor: pointer;" onclick="updateseccode()" src="/serviceRegister/checkCode?mode=api&token=' + token + '&action=getverifycode&type=reg&rand=' + Math.random() + '" alt="点击刷新验证码">');
	$("#seccode").val('');
	$("#seccode")[0].focus();
}

function checkseccode(verifycode) {
	var token = $("#formhash").val();
	if (token == '') return false;
	if('undefined' == verifycode || '' == verifycode || verifycode.length == 0) {
		$('#t4').html('<img width="16" height="16" src="/theme/images/tips_er.gif" style="vertical-align:middle; margin-top: -4px;display: inline;" /><span style="color: #ff8723;">验证码不能为空</span>');
		return false;
	}
	$.post("../index-2.html",
		  {mode: 'api', token: token, action: 'checksec', type: 'reg', secode: verifycode},
		  function(data){
			var error = eval("(" + data + ")");
			if(error.code == -1){
				$('#t4').html('<img width="16" height="16" src="/theme/images/tips_er.gif" style="vertical-align:middle; margin-top: -4px;display: inline;" />');
				return false;
			}else{
				$('#t4').html('<img width="16" height="16" src="/theme/images/tips_ok.gif" style="vertical-align:middle; margin-top: -4px;display: inline;" />');
			}
	});
	return true;
}


function checkname(name) {
	var token = $("#formhash").val();
	if (token == '') return false;
	if('undefined' == name || '' == name || name.length == 0) {
		$('#t1').html('<img width="16" height="16" src="/theme/images/tips_er.gif" style="vertical-align:middle; margin-top: -4px;display: inline;" /><span style="color: #ff8723;">用户名不能为空</span>');
		return false
	};
	if(!strstartwidth(name, 'vg')){
		$('#t1').html('<img width="16" height="16" src="/theme/images/tips_er.gif" style="vertical-align:middle; margin-top: -4px;display: inline;" /><span style="color: #ff8723;">用户名以vg开头</span>');
		return false;
	}
	if(!strlength7_10(name)){
		$('#t1').html('<img width="16" height="16" src="/theme/images/tips_er.gif" style="vertical-align:middle; margin-top: -4px;display: inline;" /><span style="color: #ff8723;">请输入7-10位数字或字母组合</span>');
		return false;
	}
	$.post("../index-2.html",
		  {mode: 'api', token: token, action: 'checkname', username: name},
		  function(data){
			var error = eval("(" + data + ")");
			if($.trim(error) == 1){
				$('#t1').html('<img width="16" height="16" src="/theme/images/tips_ok.gif" style="vertical-align:middle; margin-top: -4px;display: inline;" />');
			}else{
				$('#t1').html('<img width="16" height="16" src="/theme/images/tips_er.gif" style="vertical-align:middle; margin-top: -4px;display: inline;" /><span style="color: #ff8723;">该账户名已经被注册</span>');
				return false;
			}
	}, "json");
	return true;
}

function checkpwd(pwd) {
	var token = $("#formhash").val();
	if (token == '') return false;
	if('undefined' == pwd || '' == pwd || pwd.length == 0) {
		$('#t2').html('<img width="16" height="16" src="/theme/images/tips_er.gif" style="vertical-align:middle; margin-top: -4px;display: inline;" /><span style="color: #ff8723;">密码不能为空</span>');
		return false;
	}
	if(pwd.length < 6){
		$('#t2').html('<img width="16" height="16" src="/theme/images/tips_er.gif" style="vertical-align:middle; margin-top: -4px;display: inline;" /><span style="color: #ff8723;">请输入6-12位数字或字母组合</span>');
		return false;
	}else{
		$('#t2').html('<img width="16" height="16" src="/theme/images/tips_ok.gif" style="vertical-align:middle; margin-top: -4px;display: inline;" />');
	}
	return true;
}

function checkcfmpwd(pwd, cfmpwd) {
	var token = $("#formhash").val();
	if (token == '') return false;
	if('undefined' == pwd || '' == pwd || pwd.length == 0) return false;
	if('undefined' == cfmpwd || '' == cfmpwd || cfmpwd.length == 0) {
		$('#t3').html('<img width="16" height="16" src="/theme/images/tips_er.gif" style="vertical-align:middle; margin-top: -4px;display: inline;" /><span style="color: #ff8723;">确认密码不能为空</span>');
		return false;
	}
	if(cfmpwd.length < 6){
		$('#t3').html('<img width="16" height="16" src="/theme/images/tips_er.gif" style="vertical-align:middle; margin-top: -4px;display: inline;" /><span style="color: #ff8723;">请输入6-12位数字或字母组合</span>');
		return false;
	}else{
		if(cfmpwd != pwd){
			$('#t3').html('<img width="16" height="16" src="/theme/images/tips_er.gif" style="vertical-align:middle; margin-top: -4px;display: inline;" /><span style="color: #ff8723;">两次密码输入不一致</span>');
			return false;
		}else{
			$('#t3').html('<img width="16" height="16" src="/theme/images/tips_ok.gif" style="vertical-align:middle; margin-top: -4px;display: inline;" />');
		}
	}
	return true;
}

function reg(){
	var token = $("#formhash").val();
	var username = $.trim($("#username").val());
	var password = $.trim($("#password").val());
	var cfmpwd = $.trim($("#cfmpwd").val());
	var verifycode = $("#seccode").val();
	if (token == '') return false;

	if((!checkname(username)) || (!checkpwd(password)) || (!checkcfmpwd(password, cfmpwd)) || (!checkseccode(verifycode))) {
		return false;
	}
	$('#regbtn').attr('disabled', 'disabled').val('正在提交...');
	$.post("../index-2.html",
		  {mode: 'api', token: token, action: 'reg', username: username, password: password, cfmpwd: cfmpwd, secode: verifycode},
		  function(data){
			var obj = eval("(" + data + ")");
			//alert(obj.code);
			if(obj.code == 8){
				$('#regbtn').val('申请成功');
				window.location.href = "../reg-success/index.html";
			}else{
				$('#regbtn').attr('disabled', false).val('提交');
				updateseccode();
			}
	});
}

function updatetrialseccode() {
	var token = $("#formhash").val();
	if (token == '') return false;
	$('#verifycode').html('<img width="65" height="26" border="0" style="vertical-align:middle; margin-top: -4px;display: inline; cursor: pointer;" onclick="updatetrialseccode()" src="/index.php?mode=api&token=' + token + '&action=getverifycode&type=trial&rand=' + Math.random() + '" alt="验证码">');
	$("#seccode").val('');
	$("#seccode")[0].focus();
}

function checktrialseccode(verifycode) {
	var token = $("#formhash").val();
	if (token == '') return false;
	if('undefined' == verifycode || '' == verifycode) return false;
	$.post("../index-2.html",
		  {mode: 'api', token: token, action: 'checksec', type: 'trial', secode: verifycode},
		  function(data){
			var error = eval("(" + data + ")");
			if(error.code == -1){
				$('#t4').html('<img width="16" height="16" src="/theme/images/tips_er.gif" style="vertical-align:middle; margin-top: -4px;display: inline;" /><span style="color: #ff8723;">验证码错误</span>');
				return false;
			}else{
				$('#t4').html('<img width="16" height="16" src="/theme/images/tips_ok.gif" style="vertical-align:middle; margin-top: -4px;display: inline;" />');
			}
	});
	return true;
}

function trialreg(){
	var token = $("#formhash").val();
	var verifycode = $("#seccode").val();
	if (token == '') return false;
	if(verifycode.length == 0){
		$('#t4').html('<img width="16" height="16" src="/theme/images/tips_er.gif" style="vertical-align:middle; margin-top: -4px;display: inline;" /><span style="color: #ff8723;">验证码不能为空</span>');
		return false;
	}

	if(!checktrialseccode($.trim(verifycode))) return false;
	$('#regbtn').attr('disabled', 'disabled').val('正在提交...');
	$.post("../index-2.html",
		  {mode: 'api', token: token, action: 'trial', secode: verifycode},
		  function(data){
			var obj = eval("(" + data + ")");
			//alert(obj.code);
			if(obj.code == -1){
				$('#t4').html('<img width="16" height="16" src="/theme/images/tips_er.gif" style="vertical-align:middle; margin-top: -4px;display: inline;" /><span style="color: #ff8723;">验证码错误</span>');
				$('#regbtn').attr('disabled', false).val('提 交');
				updatetrialseccode();
			}
			if(obj.code == 1){
				$('#regbtn').val('申请成功');
				window.location.href = "../try-success/index.html";
			}
	});
}