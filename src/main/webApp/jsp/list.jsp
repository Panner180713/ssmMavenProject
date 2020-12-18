<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<%
    String basePath = request.getScheme() + "://" + request.getServerName() + ":" + request.getServerPort() + request.getContextPath();
%>
<head>
    <title>list page</title>
    <script src="<%=basePath%>/static/js/jquery-1.9.0.min.js" type="text/javascript"></script>
</head>
<body>
<input type="hidden" id="isRemembered" value="${isRememberMe}"/>
<input type="hidden" id="isAuthenticated" value="${isAuthenticated}"/>
<h4>list page</h4>
<a href="/ssmm/testShiro/jumpToAdminPage">admin page</a>
<br>
<a href="/ssmm/testShiro/jumpToUserPage">user page</a>
<br>
<a href="javascript:void(0)" onclick="testIsRemembered()">is remembered</a><br/>
<a href="javascript:void(0)" onclick="testIsAuthenticated()">is authenticated</a>
<br>
<a href="/ssmm/testShiro/logout">注销</a>
</body>
<script type="text/javascript">
    function testIsRemembered() {
        alert($('#isRemembered').val());
    }
    function testIsAuthenticated() {
        alert($('#isAuthenticated').val());
    }
</script>
</html>
