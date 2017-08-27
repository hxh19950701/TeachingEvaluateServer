<%@ page language="java" import="java.util.*" pageEncoding="ISO-8859-1"%>
<%@taglib prefix="s"  uri="/struts-tags"%>
<%
String path = request.getContextPath();
String basePath = request.getScheme()+"://"+request.getServerName()+":"+request.getServerPort()+path+"/";
%>

<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<html>
  <head>
    <base href="<%=basePath%>">
    
    <title>Teaching Evaluation</title>
	<meta http-equiv="pragma" content="no-cache">
	<meta http-equiv="cache-control" content="no-cache">
	<meta http-equiv="expires" content="0">    
	<meta http-equiv="keywords" content="keyword1,keyword2,keyword3">
	<meta http-equiv="description" content="This is my page">
	<!--
	<link rel="stylesheet" type="text/css" href="styles.css">
	-->
  </head>
  
  <body>
  <center>
    Welcome to Teaching Evaluation Website.<br>
    <a href="">Click here</a> to download the Android application!<br>
    (Support to Andorid 4.0 or above, Size: Unknown)
    <br>
    You IP Address is <% out.println(request.getRemoteAddr());  %><br>
    <br>
    <br>
    Thinks to Vasthread Hu, Qiong Xiao, Debing Hu and all the Open Sourse Community devoloper.
    </center>
  </body>
</html>
