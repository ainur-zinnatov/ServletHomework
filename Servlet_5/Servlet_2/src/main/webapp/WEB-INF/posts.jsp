<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>

<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>Posts</title>
  <style>
    .container {width: 900px;
      margin: 0 auto;
      background: #f0f0f0; }
    .card  {
      width: 600px;
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
  <h1>Text</h1>
  <hr>
<c:forEach items="${posts}" var="post">


  <div class="card">
    <h2><c:out value="${post.getUser_name()}"/></h2>
    <span><c:out value="${post.getText()}"/></span>
<p><c:out value="${post.getDatetime()}"/></p>
  </div>

</c:forEach>
</div>
</body>
</html>
