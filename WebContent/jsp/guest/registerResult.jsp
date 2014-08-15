<%@ page language="java" contentType="text/html;charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Refresh" content="10;url=index.jsp">
<title>success</title>
<link rel="stylesheet" href="css/register.css">
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
			<img src="images/step-three.png" />
			<ul>
				<li id="progress-basic" class="progress-pre-font">基本信息</li>
				<li id="progress-store" class="progress-pre-font">商店信息</li>
				<li id="progress-finish" class="progress-pre-font">完成注册</li>
			</ul>
		</div>
		
	</div>
	<div id="register-final">
		<div id="congr-info-check">
			<img src="images/check.png" />
			<p>恭喜你，你已经成功提交了所有信息，接下来管理员会在第一时间内审核你的信息，一旦审核成功，将Email通知到您。</p>
		</div>
		<div id="congr-info-wait">
		    <p>等待审核人数:<span id="congr-info-num">${waitNum}</span></p><br>
			<p>预估等待时间:<span id="congr-info-time">18 小时</span></p>
		</div>
	</div>


</body>
</html>
