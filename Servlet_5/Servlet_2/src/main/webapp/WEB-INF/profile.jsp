<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>

<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
<meta charset=\"utf-8\">
 <title>Users</title>
 <style>
.container {width: 900px;
 margin: 0 auto;
 background: #f0f0f0; }
 .card  {
  width: 250px;
 float: left;
 border: 2px solid #55c5e9;
  padding: 15px;
 background: #666;
 margin-bottom: 10px;
 }
 </style>
 </head>
<body>


<div class="container">
    <div class="card">
      <h2><c:out value="${user.getName()}"/></h2>
      <p>email:<c:out value="${user.getEmail()}"/></p>
<c:choose>
        <c:when test="${user.getSex()==1}">
            <p>male</p>
        </c:when>

        <c:when test="${user.getSex()==0}">
            <p>female</p>
        </c:when>
    <c:otherwise>
        <p>secret</p>
    </c:otherwise>
</c:choose>
<c:choose>
       <c:when test="${user.getNewsletter().equals('1')}">
        <p>you are subscribed!</p>
        </c:when>

        <c:when test="${!user.getNewsletter().equals('1')}">
            <p>you are not subscribed!</p>
        </c:when>
    </c:choose>
      </div>
    </div>

<form  method="post" action="/postsserv">

    <p>Add post<br>
    <textarea name="text" id="textarea"  cols="40" rows="3"></textarea>
    </p>
    <p><input type="submit"  value="post" /></p>
</form>


   <div>
   <hr>
   <form name="loginForm" method="get" action="/logout">
   <p><input type="submit" value="exit" /></p>
   </form>

   </div>
<div>
    <hr>
    <form  method="get" action="/postsserv">
        <p><input type="submit" value="posts" /></p>
    </form>

</div>
</body>
</html>
