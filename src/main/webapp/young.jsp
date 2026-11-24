<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
<jsp:useBean id="person" type="lab.mvc.model.Person" scope="request" />
<html>
<head><title>Young page</title></head>
<body>
<h1>Page for people born in 1990 or AFTER</h1>
<table border="1" cellpadding="5">
  <tr><th>First name</th><td><jsp:getProperty name="person" property="firstName" /></td></tr>
  <tr><th>Last name</th><td><jsp:getProperty name="person" property="lastName" /></td></tr>
  <tr><th>Birth date</th><td><jsp:getProperty name="person" property="birthDay" /></td></tr>
  <tr><th>Sex</th><td><jsp:getProperty name="person" property="sex" /></td></tr>
</table>
<p><a href="mvc.html">Back</a></p>
</body>
</html>
