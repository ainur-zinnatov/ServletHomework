<%@ taglib prefix="c"  uri="http://java.sun.com/jsp/jstl/core" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html xmlns="http://www.w3.org/1999/xhtml">
<head>
    <meta charset="utf-8">
    <title>Login</title>

    <script src="/JS/jquery-2.1.4.min.js"></script>

   <script type="text/javascript">

        $(function() {

            $('.jqButton').click(function(e) {
                e.preventDefault();
                alert("sdfgh");
                $("h1").first().text("azaz").css("color", "red");
                $(this).button();
            });

        });
    </script>
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

<!-- Делаем отдельную кнопку: -->
<button class="jqButton">Кнопка</button>

<h1>Az</h1>


<div id="container">

    <div class="Regform">
        <c:if test="${not empty message}">
            <h3>${message}</h3>
        </c:if>
        <form name="loginForm" method="post" action="/login">


        <p>Email: <input type="text" name="email" placeholder="simple@gmail.com" ></p>
        <p>Password: <input type="password" placeholder="password" name="password"> </p>

        <p><input type="submit" value="Sign up" /></p>
        </form>
        <form name="loginForm" method="get" action="/registrationservlet">
            <p><input type="submit" value="Registration" /></p>
        </form>
    </div>
</div>
</body>
</html>

