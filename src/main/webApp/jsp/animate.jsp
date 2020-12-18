<%@ page language="java" import="java.util.*" pageEncoding="UTF-8" %>
<html>
<%
    String basePath = request.getScheme() + "://" + request.getServerName() + ":" + request.getServerPort() + request.getContextPath();
%>
<head>
    <title>The Matrix</title>
    <script src="<%=basePath%>/main/webContent/static/js/jquery-1.9.0.min.js" type="text/javascript"></script>
    <meta charset="utf-8">
    <script type="text/javascript">
        $(function () {
            var s = window.screen;
            var width = q.width = s.width;
            var height = q.height;
            var yPositions = Array(300).join(0).split('');
            var ctx = q.getContext('2d');
            var draw = function () {
                ctx.fillStyle = 'rgba(0,0,0,.05)';
                ctx.fillRect(0, 0, width, height);
                ctx.fillStyle = 'red';
                ctx.font = '10pt Georgia';
                yPositions.map(function (y, index) {
                    text = String.fromCharCode(1e2 + Math.random() * 33);
                    x = (index * 10) + 10;
                    q.getContext('2d').fillText(text, x, y);
                    if (y > Math.random() * 1e4) {
                        yPositions[index] = 0;
                    } else {
                        yPositions[index] = y + 10;
                    }
                });
            };
            RunMatrix();

            function RunMatrix() {
                Game_Interval = setInterval(draw, 30);
            }
        });
    </script>
</head>
<body>
<div align="center">
    <h1>黑客帝国</h1>
    <canvas id="q" width="500" height="500"></canvas>
</div>
</body>
</html>
