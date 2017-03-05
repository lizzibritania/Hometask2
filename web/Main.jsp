<!DOCTYPE HTML>
<%@ page contentType="text/html; charset=UTF-8" %>
<%@ page import="java.util.*, java.text.*" %>


<body>
<link rel="stylesheet" type="text/css" href="/css/sml.css">
<div class="center">
    <form action="Validation" method="post">
        <p align="center"> Login:  </p>     <p align="center"> <input type="text" name="login">   </p>
        <br />
        <p align="center">Password: </p>  <p align="center"> <input type="password" name="password" />    </p>
        <br>
        <p > <input type="checkbox"name="cbox" /> Remember me </p>
            <br>
        <p align="center"> <input class="ok" type="submit" value="Submit" />    </p>

    </form> </div>
</body>
</html>