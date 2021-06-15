<%@page import="com.etcxm.www.utils.DBUtil"%>
<%@page import="java.text.SimpleDateFormat"%>
<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%
String path = request.getContextPath();
String basePath = request.getScheme()+"://"+request.getServerName()+":"+request.getServerPort()+path+"/";
%>

<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<html>
  <head>
    <base href="<%=basePath%>">
    
    <title>添加产品</title>
    
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
    	request.setCharacterEncoding("utf-8");
    	String productName=request.getParameter("productName");
    	String productPrice=request.getParameter("productPrice");
    	String productStore=request.getParameter("productStore");
    	
    	SimpleDateFormat simpleDateFormat=new SimpleDateFormat("yyyy-MM-dd");
    	String productPdate=simpleDateFormat.format(new Date());
    	
    	DBUtil dbUtil=new DBUtil();	//实例DBUtil
    	String sql="insert into tproduct(product_name,product_price,product_store,product_pdate) values(?,?,?,?)";
    	int i=dbUtil.update(sql, productName,productPrice,productStore,productPdate);
    	if(i==1){
    		%>
    		<script type="text/javascript">
    			alert("新增成功！");
    			window.location.href="product/products.jsp";
    		</script>
    		<%
    	}else{
    		%>
    		<script type="text/javascript">
    			alert("新增失败！");
    			window.history.back();
    		</script>
    		<%
    	}
    %>
  </body>
</html>
