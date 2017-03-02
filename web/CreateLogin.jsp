<!DOCTYPE HTML>
<%@ page contentType="text/html; charset=UTF-8" %>
<%@ page import="java.util.*, java.text.*" %>


<body>
<link rel="stylesheet" type="text/css" href="/css/sml.css">
<div class="center">
    <form action="Creation" method="post">
        <label class="centre"> Придумайте имя пользователя и пароль: </label>
        <p align="center"> Login:  </p>     <p align="center"> <input type="text" name="newlogin">   </p>
        <br />
        <p align="center">Password: </p>  <p align="center"> <input type="text" name="newpassword" />    </p>
        <br>
        <p align="center"> <input class="ok" type="submit" value="Create" />    </p>
    </form> </div>
</body>
</html>