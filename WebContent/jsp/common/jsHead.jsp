<%@ page language="java"  pageEncoding="UTF-8"%>
<% 
String noDojo = request.getParameter("noDojo");
if(noDojo==null) {
%>
<script type="text/javascript" src="dojoroot/dojo/dojo.js"></script>
<%
}
%>
<link rel="stylesheet" type="text/css" href="css/alertBar.css">
<script type="text/javascript" src="javascript/jquery-1.10.2.js"></script>
<script type="text/javascript" src="javascript/jquery.noty.packaged.min.js"></script>
<script type="text/javascript" src="javascript/alertBar.js"></script>
