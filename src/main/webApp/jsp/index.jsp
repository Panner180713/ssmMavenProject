<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%
    String basePath = request.getScheme() + "://"
            + request.getServerName() + ":" + request.getServerPort()
            + request.getContextPath();

%>
<html>
<head>
    <title>welcome</title>
    <script src="<%=basePath%>/jQuery/jquery-1.9.0.min.js" type="application/javascript"></script>
    <script type="application/javascript">

        window.onload = function () {
            // var li1 = document.getElementById("li1");
            // li1.onclick = function () {
            //     alert(this.innerHTML);
            // }
            var div1 = document.getElementById("div1");
            drag(div1);
        };

        function addLi1() {
            var city = document.getElementById("city");
            var li = document.createElement("li");
            li.innerHTML = "广州";
            city.appendChild(li);
        }

        function addLi2() {
            var city = document.getElementById("city");
            //会修改ul内部html，清除绑定的事件
            city.innerHTML += "<li>广州</li>";
        }

        function alertInnerHtml(obj) {
            alert(obj.innerHTML);
        }

        function alertMessage() {
            alert("点击了");
        }

        function sendMessage() {
            $.ajax({
                url: "http://localhost:8080/ssmm/student/testSameParamName.action",
                data : {
                    userName : "tom"
                },
                type: "post",
                success : function (data) {

                },
                error : function () {
                    alert("error");
                }
            });
        }

        function drag(obj) {
            obj.onmousedown = function (event) {
                // if(obj.setCapture){
                //     obj.setCapture();
                // }
                obj.setCapture && obj.setCapture();
                event = event || window.event;
                var ot = event.clientY - obj.offsetTop;
                var ol = event.clientX - obj.offsetLeft;
                document.onmousemove = function (event) {
                    event = event || window.event;
                    obj.style.top = event.clientY - ot + 'px';
                    obj.style.left = event.clientX -ol + 'px';
                };

                document.onmouseup = function () {
                    document.onmousemove = null;
                    document.onmouseup = null;
                    // if(obj.releaseCapture){
                    //     obj.releaseCapture();
                    // }
                    obj.releaseCapture && obj.releaseCapture();
                };

                return false;
            };
        }
    </script>
    <style type="text/css">
        #div1{
            width: 100px;
            height: 100px;
            position: absolute;
            background-color: red;
        }
        #div2{
            width: 100px;
            height: 100px;
            left: 200px;
            top: 100px;
            position: absolute;
            background-color: yellow;
        }
    </style>
</head>
<body>
    <h1>welcome</h1>
    <input type="button" value="按钮" onclick="sendMessage()"/>
    <button id="addLi" onclick="addLi2()">添加按钮</button>
    <ul id="city" style="background-color: aqua" onclick="alertMessage()">
        <li id="li1" onclick="alertInnerHtml(this)">北京</li>
        <li id="li2">上海</li>
    </ul>
    <div id="div2"></div>
    <div id="div1"></div>
</body>
</html>
