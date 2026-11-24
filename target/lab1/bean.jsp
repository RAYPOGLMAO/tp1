<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
<%-- encoding must be set BEFORE the properties are read from the request --%>
<% request.setCharacterEncoding("UTF-8"); %>
<jsp:useBean id="person" class="isep.lab1.bean.Person" scope="request" />
<%-- property="*" binds every request parameter whose name matches a bean property --%>
<jsp:setProperty name="person" property="*" />
<html>
<head><title>JSP + JavaBean</title></head>
<body>
<h1>Data saved in the bean, then read back from it</h1>
<table border="1" cellpadding="5">
  <tr><th>First name</th><td><jsp:getProperty name="person" property="firstName" /></td></tr>
  <tr><th>Last name</th><td><jsp:getProperty name="person" property="lastName" /></td></tr>
  <tr><th>Birth date</th><td><jsp:getProperty name="person" property="birthDay" /></td></tr>
  <tr><th>Sex</th><td><jsp:getProperty name="person" property="sex" /></td></tr>
</table>
<p><a href="form-jsp.html">Back</a></p>
</body>
</html>
