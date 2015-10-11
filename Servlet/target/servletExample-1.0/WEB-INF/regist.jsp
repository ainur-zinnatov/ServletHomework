<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="c"  uri="http://java.sun.com/jsp/jstl/core" %>
<html xmlns="http://www.w3.org/1999/xhtml">
<head>
    <meta charset="utf-8">
    <title>Registration</title>
    <style>
        .Regform  {
           border: 2px solid #6db6e9; /* Рамка вокруг фотографии */
            padding: 15px; /* Расстояние от картинки до рамки */
            background: #77fffc; /* Цвет фона */
            margin-right: 10px; /* Отступ справа */
            margin-bottom: 10px; /* Отступ снизу */
        }
        #container {
            width: 800px; /* Ширина слоя */
            margin: 0 auto; /* Выравнивание по центру */
            background: #c2f5ff; /* Цвет фона левой колонки */
        }
    </style>
</head>
<body>



<div id="container">
    <div class="Regform">
        <c:if test="${not empty message}">
            <h3>${message}</h3>
        </c:if>
        <form name="loginForm" method="post" action="/registrationservlet">

        <p>Name: <input type="text" name="name" required/></p>
        <p>Email: <input type="text" name="email" placeholder="simple@gmail.com" /></p>
        <p>Password: <input type="password" placeholder="password" name="password" required/> </p>
        <p><input type="radio" name="sex" value="1">Male
        <input type="radio" name="sex" value="0">Female</p>
        <p><input type="checkbox" name="newsletter" value="1">Subscribe to the newsletter</p>
        <p><input type="submit" value="Sign up" /></p>
        </form>
        <form name="loginForm" method="post" action="/usercard">
            <p><input type="submit" value="User card" /></p>
        </form>

    </div>
</div>
</body>
</html>

