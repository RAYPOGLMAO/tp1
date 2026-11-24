<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
<html>
<head><title>JSP expression</title></head>
<body>
<h1>JSP expression</h1>
<!-- an expression: <%= ... %> (no semicolon) is evaluated and printed -->
<p>Random number: <%= Math.random() %></p>
<p>Random integer 1..100: <%= 1 + (int) (Math.random() * 100) %></p>
</body>
</html>
