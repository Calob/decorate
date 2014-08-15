<%@ page contentType="text/html;charset=UTF-8" language="java"%>
<!DOCTYPE html>
<html>
<head>
	<title>Register store</title>
	<meta http-equiv="Content-Type" content="text/html; charset=UTF-8" />
	<%@ include file="../common/jsHead.jsp" %>
	<script type="text/javascript" src="javascript/registerAjax.js"></script>
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
			<img src="images/step-two.png" />
			<ul>
				<li id="progress-basic" class="progress-pre-font">基本信息</li>
				<li id="progress-store" class="progress-pre-font">商店信息</li>
				<li id="progress-finish" class="progress-next-font">完成注册</li>
			</ul>
		</div>
	</div>
	<div id="register-step-two">
		<form action="registerStore" method="post" id="registerStoreForm">
		<table>
			<tr>
				<td>
					<label for="storename">商店名称* :</label>
					<input type="text" id="storename" name="storeName" placeholder="storename" required>
				</td>
				<td>
					<label for="field">所属领域* :</label>
					<div id="register-step-two-select">
						<select id="industry" size="1" name="industry">
							<option selected></option>
						</select>
					</div>
				</td>
				<td>
					<label for="tele">联系电话* :</label>
					<input type="text" id="tele" name="tele" placeholder="telephone" required>
				</td>
			</tr>
			<tr>
				<td>
					<label for="address">所在地区* :</label>
					<div id="register-step-two-address">
						<ul>
							<li class="register-step-two-select-small">
								<select size="1" name="province" id="location">
									<option selected></option>
								</select>
							</li>
							<li class="register-step-two-select-small">
								<select size="1" name="city" id="city">
									<option selected></option>
								</select>
							</li>
						</ul>
					</div>
				</td>
				<td>
					<label for="address">详细地址* :</label>

					<input type="text" id="address" name="addressDetail" placeholder="" required>

				</td>
				<td>
					<label for="accredit">授权号码 :</label>
					<input type="text" id="accredit" name="businessLisenceId" placeholder="" required>
				</td>
			</tr>
			<tr>
				<td>
					<label for="revenue">税务号 :</label>
					<input type="text" id="revenue" name="revenue" placeholder="" required>
				</td>
				<td>
					<label for="company-name">公司名称 :</label>
					<input type="text" id="company-name" name="companyName" placeholder="" required>
				</td>
				<td>
					<label for="company-brand">公司品牌 :</label>
					<div id="register-step-two-select">
						<select id="brands" size="1" name="company-brand">
							<option selected></option>
						</select>
					</div>
				</td>
			</tr>
		</table>
			<ul>
				<li id="register-step-two-notice">
					<p id="register-step-two-remind">*必填项</p> 		
				</li>
				<li>
					<button id="register-cancel" type="button">取消</button>
					<button id="register-step-two-previous"  type="button" >上一步</button>
					<input id="register-next"  type="submit" value="下一步">
				</li>
			</ul>
		</form>
	</div>
</body>
</html>
