<%--
  Created by IntelliJ IDEA.
  User: ShowGa
  Date: 2024/6/1
  Time: 上午 12:07
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<jsp:useBean id="counter" class="Obj.Counter" scope="application"></jsp:useBean>
<html>
<head>
    <title>counter</title>
</head>
<body>
<% counter.increment(); %>
    <p>You are the <%= counter.getCount() %>%></p>
</body>
</html>
