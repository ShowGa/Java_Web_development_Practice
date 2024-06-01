<%--
  Created by IntelliJ IDEA.
  User: ShowGa
  Date: 2024/5/31
  Time: 下午 12:37
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>Title</title>
</head>
<body>
<%for (int j = 1; j <= 10; j++) { %>
<%= addUpto(j) %>
<%}%>

<%!
    private int addUpto(int n) {
        int result=0;
        for (int i = 0; i < n; i++) {
            result=result+i;
        }
        return result;
    }
%>
</body>
</html>
