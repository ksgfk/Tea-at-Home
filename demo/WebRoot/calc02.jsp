<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%
String path = request.getContextPath();
String basePath = request.getScheme()+"://"+request.getServerName()+":"+request.getServerPort()+path+"/";
%>

<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<html>
  <head>
    <base href="<%=basePath%>">
    
    <title>My JSP 'calc02.jsp' starting page</title>
    
	<meta http-equiv="pragma" content="no-cache">
	<meta http-equiv="cache-control" content="no-cache">
	<meta http-equiv="expires" content="0">    
	<meta http-equiv="keywords" content="keyword1,keyword2,keyword3">
	<meta http-equiv="description" content="This is my page">
	<!--
	<link rel="stylesheet" type="text/css" href="styles.css">
	-->
	<%!
		int n1,n2;
		public void trans(String str,String c){
			String num1,num2;
			String[] ns=str.split(c);
			num1=ns[0];
			num2=ns[1];
			//转换成整数
			n1=Integer.parseInt(num1);
			n2=Integer.parseInt(num2);
		}
	 %>
  </head>
  
  <body>
	<form action="calc02.jsp" method="post">
		<input type="text" name="n" />
		<input type="submit" value="=" />
	</form>
	<%
		request.setCharacterEncoding("utf-8");
		String n=request.getParameter("n");
		if(n==null||"".equals(n)){
		}else{
		%>
			<%=n+"=" %>
		<%
			//out.println(n+"=");
			//对表达式进行处理
			//校验符号所在的位置
			//indexOf返回查找的字符所在的位置 n=8+9,找不到返回-1，找到的话返回字符所在的位置
			String num1,num2;
			if(n.indexOf('+')>0){	//加法
				trans(n, "\\+");
				%>
				<font color="red"><%=n1+n2 %></font>
				<%
				//out.println(n1+n2);
			}else if(n.indexOf('-')>0){	//加法
				trans(n, "\\-");
				out.println(n1-n2);
			}else if(n.indexOf('*')>0){
				trans(n, "\\*");
				out.println(n1*n2);
			}else if(n.indexOf('/')>0){
				trans(n, "/");
				out.println(n1/n2);
			}		 
		}
		
		
	 %>
  </body>
</html>





