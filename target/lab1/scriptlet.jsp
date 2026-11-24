<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
<html>
<head><title>JSP scriptlet</title></head>
<body>
<h1>JSP scriptlet</h1>
<%
    // a scriptlet: Java code copied into _jspService() (local variables)
    java.util.Date now = new java.util.Date();
    String host = request.getRemoteHost();
    String clientAddr = request.getRemoteAddr();
    String serverAddr = request.getLocalAddr();
%>
<p>Current time: <%= now %></p>
<p>Host (request.getRemoteHost()): <%= host %></p>
<p>Client IP (getRemoteAddr): <%= clientAddr %> -- Server IP (getLocalAddr): <%= serverAddr %></p>
<!-- NOTE: getRemoteHost() returns the CLIENT host; the server address is getLocalAddr() -->
</body>
</html>
