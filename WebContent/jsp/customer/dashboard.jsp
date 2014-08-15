<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<!DOCTYPE html>
<html>
	<head>
		<meta charset="UTF-8">					
		<link rel="stylesheet" type="text/css" href="../css/home.css">
		<link rel="stylesheet" type="text/css" href="../css/xcharts.min.css">
		<script src="../javascript/d3.min.js"></script>
		<script src="../javascript/xcharts.min.js"></script>
		<title>商人首页</title>		
	</head>
	<jsp:include page="../common/jsHead.jsp"></jsp:include>	
	<jsp:include page="merchantHead.jsp"></jsp:include>	
	<body>	
		<div class="home_container">
			<div id="topContent" class="clearfix" >
				<div id="quickLink">
                    <h2>快速链接</h2>
                    <ul class="clearfix">
                        <li id="plus"><a href="merPutProducts">添加产品</a></li>
                        <li id="catalog"><a href="merCatalogManage">目录管理</a></li>
                        <li id="library"><a href="">查看店铺</a></li>
                        <li id="setting"><a href="storeInfo">商店设置</a></li>
                    </ul>
				</div>
				<div id="notice">
					<h2>系统公告</h2>
					<ul>
						<li><a href="">系统公告1</a></li>
						<li><a href="">系统公告2</a></li>
						<li><a href="">系统公告3</a></li>
						<li><a href="">系统公告4</a></li>
					</ul>	
				</div>
			</div>
			
	
			<div class="clearfix"  id="bottomContent">
				<div id="viewStore">
					<h2>店铺一览</h2>
					<ul class="clearfix">
						<li id="catalogBig"><a href="merCatalogManage">销售目录</a><br/><span>${saleCatGroupNum}</span></li>
						<li id="box"><a href="Jproduct">产品</a><br/><span>${saleCatEntryNum}</span></li>
						<li id="person"><a href="">客户</a><br/><span>72</span></li>
						<li id="money"><a href="merOrders?state=M">待处理订单</a><br/><span>${mrchntOrdersForDeal}</span></li>
					</ul>
				</div>
				<div id="orderManage">
					<h2>订单状态</h2>
					<div style="display:block;width:100%;height:30px;"><p id="time_range">选择时间范围：
						<input type="date" name="datetime" placeholder="YTD">
					</p></div>
					<div id="order">
					<figure id="orderChart" style="width:520px;height:235px;margin:0">
					</figure>
					</div>
					<p>
						<span>总订单数量：<b>86</b></span> 
						<span>总收入：<b>$1232086</b></span>
					</p>
					
				</div>
			</div>
			
		</div>
	
		<script src="../javascript/dashboard.js"></script>
	</body>
</html>
