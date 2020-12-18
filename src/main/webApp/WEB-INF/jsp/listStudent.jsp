<%@ page import="javaBean.Student" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>学生列表</title>
</head>
<body>
    <h1>学生列表</h1>
    <%=request.getAttribute("studentList")%>
    <%=(Student)request.getAttribute("student")%>
</body>
</html>
