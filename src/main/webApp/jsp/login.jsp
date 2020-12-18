<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<%
    String basePath = request.getScheme() + "://" + request.getServerName() + ":" + request.getServerPort() + request.getContextPath();
%>
<head>
    <title>login page</title>
    <script src="<%=basePath%>/main/webContent/static/js/jquery-1.9.0.min.js" type="text/javascript"></script>
    <script type="text/javascript">
        $(function () {
            console.log($('#title').html());
        })
    </script>
</head>
<body>
<h4 id="title">login page</h4>

<form action="/ssmm/testShiro/shiroLogin" method="post">
    userName:<input type="text" name="userName"/>
    <br><br>

    password:<input type="password" name="password"/>
    <br><br>

    <input type="submit" value="submit"/>
</form>
</body>
</html>
