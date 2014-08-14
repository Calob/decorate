<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@include file="jsp/common/includeTld.jsp" %>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>  

<!DOCTYPE html>
<html>
<head>

	<meta charset="UTF-8">
	<title>首页</title>
	<%@ include file="jsp/common/jsHead.jsp" %>
	<script type="text/javascript" src="javascript/login.js"></script>
	<script type="text/javascript" src="javascript/script.js"></script>
	<link href='css/Login_register.css' rel='stylesheet' />
	<link rel="shortcut icon" href="images/favicon.ico" type="image/x-icon" />
</head>
<body> 	
<div id="body">

<div  class="container">
	<form  action="customer/cusInfoDetail" method="post">
		<ul>
			<li id="login_title">
				装潢设计有限公司 V1.0
			</li>
			<li id="login_regiter_title">
				登录/注册
			</li>
			<li>
				<input id="login_username" type="text" placeholder="用户名"  class="login_text" name='customerName'  value='<c:if test="${not empty param.login_error}" >  
                        <c:out value="${SPRING_SECURITY_LAST_USERNAME}"/></c:if>' required>
			</li>
			<li>
				<input id="login_password" type="password" placeholder="密码"  class="login_text" name='j_password' required>
			</li>
			<li id="login_forgot_pass">
				忘记 <a href="forgetPassView">密码</a> ？
			</li>
			<li>
				<input class="login_input" id="submit_login" type="submit" value="登录" name="submit">
			</li>
			<li id="register_title">
				立即注册成为会员，愉快的进行购物
			</li>
			<li>
				<input class="login_input" id="submit_register" type="button" value="注册" name="register">
			</li>
			<li>	
			<!-- 颜色 -->
				<hr color="rgb(204,204,204)"/>
			</li>
			<li id="copyright_logo">
				<img id="copyright" src="images/copyright.png" />
				<img id="logo" src="images/IBM-logo.png" />
			</li>
		</ul>
	</form>
			
</div>						

	
</div>
</body>
</html>