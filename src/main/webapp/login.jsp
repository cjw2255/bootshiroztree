<%--
  Created by IntelliJ IDEA.
  User: 26053
  Date: 2020/5/23
  Time: 16:11
  To change this template use File | Settings | File Templates.
--%>
<%@ page isELIgnored="false" contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>Title</title>
</head>
<body>
    <fieldset>
        <details>登录</details>
        <form action="login" method="post">
            uname<input type="text" name="uname"><br>
            upwd<input type="text" name="upwd"><br>
            <input type="submit" value="登录">
        </form>
        ${error}
    </fieldset>
</body>
</html>
