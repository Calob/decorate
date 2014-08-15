<%@ page contentType="text/html; charset=UTF-8" language="java" %>
<%
	String username = (String) session.getAttribute("username");
%>
<style type="text/css">
	@import "../css/jQueryTableStyle/table_jui.css";
	@import "../css/jQueryTableStyle/smoothness/jquery-ui-1.8.4.custom.css";
	@import "../css/jQueryTableStyle/minimal/minimal.css";
</style>
<link rel="stylesheet"  href="../css/mainnav.css">
<link rel="stylesheet"  href="../css/contentCommonStyle.css">	
<link rel="shortcut icon" href="images/favicon.ico" type="image/x-icon" />
<script src="../javascript/script.js"></script>
<header>
	<div>
		<h1>IBM电商管理平台V1.0</h1>
		<ul id="header_info" class="clearfix">
			<li id="loginUser"><a href="javascript:void(0)"><%=username%></a></li>
			<li id="exit"><a href="#" title="退出登录">退出登录</a></li>
			<li><a href="javascript:void(0)" title="帮助信息">帮助信息</a></li>
			<li id="IBM"><a href="javascript:void(0)"><img alt="IBM"
					src="../images/IBM-logo.png"></a></li>
		</ul>
	</div>
</header>
<nav>
	<div>
		<ul>
			<li class="mainlevel"><a href="dashboard">首页</a></li>
			<li class="mainlevel"><a href="merCatalogManage">商店目录</a></li>
			<li class="mainlevel"><a href="Jproduct">产品中心</a>
				<ul>
					<li><a href="merPutProducts">添加新产品</a></li>
				</ul></li>
			<li class="mainlevel"><a href="merOrders">订单管理</a>
				<ul>
					<li><a href="merOrders?state=I">未付款订单</a></li>
					<li><a href="merOrders?state=M">已付款订单</a></li>
					<li><a href="merOrders?state=X">已取消订单</a></li>
					<li><a href="merOrders?state=B">缺货订单</a></li>
					<li><a href="merOrders?state=R">待发货订单</a></li>								
					<li><a href="merOrders?state=C">已完成订单</a></li>					
				</ul>
			</li>	
			<li class="mainlevel"><a href="storeInfo">商店设置</a></li>
			<li class="mainlevel"><a href="personalInfo">账户设置</a></li>
		</ul>
		<!-- <form>
			<input type="text" id="search" placeholder="搜索" />
		</form> -->
	</div>
</nav>
