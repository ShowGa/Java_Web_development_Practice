<%--
  Created by IntelliJ IDEA.
  User: ShowGa
  Date: 2024/5/31
  Time: 下午 10:57
  To change this template use File | Settings | File Templates.
--%>
<%@ page import="Obj.Currency" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ page errorPage="error.jsp" %>

<html>
<head>
    <title>Title</title>
</head>
<body>
<%
    int amount = Integer.parseInt(request.getParameter("amount"));
    Currency currency = new Currency(amount);
%>

<h2>美金: <%= amount * currency.getUsd() %></h2>
<h2>日幣: <%= amount * currency.getJpn() %></h2>
<h2>人民幣: <%= amount * currency.getRmb()%></h2>

</body>
</html>
