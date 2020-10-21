<%@ page language="java" contentType="text/html; charset=EUC-KR"
    pageEncoding="UTF-8"%>
<%
	String id = request.getParameter("id");
	String pwd = request.getParameter("pwd");
	System.out.println(id + " " + pwd);
	Thread.sleep(3000);
	if (id.equals("id01")  && pwd.equals("pwd01") ) {
		out.print("1");
	} else
		out.print("2");
 %>