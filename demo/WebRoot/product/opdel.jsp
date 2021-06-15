<%@page import="com.etcxm.www.utils.DBUtil"%>
<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%
String path = request.getContextPath();
String basePath = request.getScheme()+"://"+request.getServerName()+":"+request.getServerPort()+path+"/";
%>

<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<html>
  <head>
    <base href="<%=basePath%>">
    
    <title>删除</title>
    
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

	<%
		DBUtil dbUtil=new DBUtil();
		String sql="delete from tproduct where id=?";
		
		String id=request.getParameter("id");
		
		int i=dbUtil.update(sql, id);
		if(i==1){
			%>
				<script type="text/javascript">
					alert("删除成功！");
					window.location.href="product/products.jsp";
				</script>
			<%
		}else{
			%>
				<script type="text/javascript">
					alert("删除失败！");
					window.history.back();
				</script>
			<%
		}
		
	
	 %>

  </body>
</html>
