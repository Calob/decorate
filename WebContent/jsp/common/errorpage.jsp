<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8" isErrorPage="true"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<title>error page</title>
</head>
<body>
	<div id="center-div">
		<table>
			<tr>
				<td>数据读取错误，错误信息：<%=exception.getMessage()%>
					<p>
						<a href="javascript:history.go(-1);">返回</a>
					</p></td>
			</tr>
		</table>
	</div>
</body>
</html>