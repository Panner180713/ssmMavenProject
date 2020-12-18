<%@ page import="javaBean.Student" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>学生列表</title>
</head>
<body>
    <h1>学生列表1</h1>
    <%=request.getAttribute("studentList")%>
    <br>
    <%=(Student)request.getAttribute("student")%>
    <br>
    <%=(Student)request.getAttribute("student02")%>
    <br>
    <%=(Student)session.getAttribute("student")%>
</body>
</html>
