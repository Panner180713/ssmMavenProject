<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%
    String basePath = request.getScheme() + "://"
            + request.getServerName() + ":" + request.getServerPort()
            + request.getContextPath();
%>
<html>
<head>
    <title>测试layui</title>
    <script src="<%=basePath%>/main/webContent/static/js/layui.js" type="application/javascript"></script>
    <link rel="stylesheet" href="<%=basePath%>/main/webContent/static/css/layui.css"/>
    <script type="text/javascript">
        $(function () {
            layui.sessionData('test',{
                key: 'key01',
                value: 'value01'
            });
        });
        
        function getSessionData() {
            var obj = layui.sessionData('test');
            return obj.key01;
        }
    </script>
</head>
<body>
<input type="button" onclick="getSessionData()" value="按钮01"/>
</body>
</html>
