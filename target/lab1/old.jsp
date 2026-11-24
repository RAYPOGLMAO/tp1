<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
<%-- the View does NOT create the bean (type=, not class=) and does NOT modify it (only getProperty) --%>
<jsp:useBean id="person" type="lab.mvc.model.Person" scope="request" />
<html>
<head><title>Old page</title></head>
<body>
<h1>Page for people born BEFORE 1990</h1>
<table border="1" cellpadding="5">
  <tr><th>First name</th><td><jsp:getProperty name="person" property="firstName" /></td></tr>
  <tr><th>Last name</th><td><jsp:getProperty name="person" property="lastName" /></td></tr>
  <tr><th>Birth date</th><td><jsp:getProperty name="person" property="birthDay" /></td></tr>
  <tr><th>Sex</th><td><jsp:getProperty name="person" property="sex" /></td></tr>
</table>
<p><a href="mvc.html">Back</a></p>
</body>
</html>
