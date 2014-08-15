<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@include file="../common/includeTld.jsp" %>
<!DOCTYPE html>
<html>
<head>
	<title>Register</title>
	<meta http-equiv="Content-Type" content="text/html; charset=UTF-8" />
	<%@ include file="../common/jsHead.jsp" %>
	<script type="text/javascript" src="javascript/register.js"></script>
	<link rel="stylesheet" href="。。/css/register.css">
	<link rel="shortcut icon" href="images/favicon.ico" type="image/x-icon" />
</head>
<body>
	<div id="masthead">
		<div id="masthead-top">
			<ul>
			<li id="mast-top-title">IBM电商管理平台 V1.0</li>
			<li id="mast-top-logo"><img src="images/ibm.png" /></li>
			</ul>
		</div>
		<div id="masthead-nav">
			<div id="register-title">注册流程</div>
		</div>
	</div>
	<div id="register-progress">
		<div id="progress">
			<img src="images/step-one.png" />
			<ul>
				<li id="progress-basic" class="progress-pre-font">基本信息</li>
				<li id="progress-finish" class="progress-next-font">完成注册</li>
			</ul>
		</div>
	</div>
	<div id="register-step-one">
		<form id="register_merchant" name="register" action="../../guest/register" method="post" accept-charset="utf-8" onsubmit="return checkForm(); ">
		<table>
			<tr>
				<td id="register-step-one-username">
					<label for="username">用户名* :</label>
					<input type="text" id="register_name" name="username" placeholder="username" value="${tmp1}" required>
				</td>
				<td>
					<label for="usermail">邮箱* :</label>
					<input type="email" id="register_email" name="email" placeholder="yourname@email.com" value="${tmp2}" required>
				</td>
			</tr>
			<tr>
				<td id="register-step-one-username">
					<p id="username-checked"><img src="images/checkmark.png" />可用</p>
					<p id="username-invalid"><img src="images/invalid3.png" />用户名重复</p>
				</td>
				<td id="register-step-one-email">
					<p id="email-checked"><img src="images/checkmark.png" />可用</p>
					<p id="email-invalid"><img src="images/invalid3.png" />邮箱已被注册</p>
				</td>
			</tr>
			<tr>
				
				<td id="register-step-one-password">
					<label for="password">密码* :</label>
					<input type="password" name="password" id="password" placeholder="password" required>
				</td>
				<td>
					<label for="password2">确认密码* :</label>
					<input type="password" name="password_2" id="password_2" placeholder="password" required>
				</td>
			</tr>
			<tr>
				<td id="register-step-one-remind1">
					<p>密码6~13位，数字或字母</p>
				</td>
				<td id="register-step-one-remind1">
					<p>密码6~13位，数字或字母</p>
				</td>
			</tr>
			<tr>
				<td>
					<label for="identity">身份证* :</label>
					<input id="register_id" type="text" name="identityNumber" placeholder="identity" value="${tmp3}" required />
				</td>
				<td></td>
			</tr>
			<tr>
				<td id="register-step-one-identity">
					<p id="identity-checked"><img src="images/checkmark.png" />可用</p>
					<p id="identity-invalid"><img src="images/invalid3.png" />身份证已被注册</p>
				</td>
				<td></td>
			</tr>
		</table>
			<ul>
				<li id="register-step-one-remind2">
					<p>*必填项</p> 
				</li>
				<li>
					<button type="button" id="register-cancel"  >取消</button>
					<input class="login_input" id="submit_login" type="submit" value="提交" name="submit">
<!-- 				<button id="register-next"  type="button">下一步</button>	 -->
				</li>
			</ul>
		</form>
			
	</div>
<script type="text/javascript">
	require(['dojo/on','dojo/dom','dojo/request','dojo/dom-form'], function(on,dom,request,domForm){
		on(dom.byId("register_merchant"), 'submit', function(evt) {
			evt.stopPropagation();
			evt.preventDefault();
			request.post("register_merchant", {
				data:domForm.toObject('register_merchant'),
				handleAs:'json'
			}).then(function(response) {
				if(response.status=='success'){
					window.location.href = 'registerStore';
				}
				if(response.status=='failure'){
					alert('参数非法');
				}
			},function(error){
				alert(error);
			})
		})
	})
</script>		

</body>
</html>
