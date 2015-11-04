<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" pageEncoding="utf-8" %>
<html>
<head>

  <meta content="text/html" charset="utf-8">

  <script  src="<c:url value="/JS/jquery-2.1.4.min.js"/>"></script>

  <script type="text/javascript">
    $(document).ready(function(){
      $('#myButton').click(function(e){
        e.preventDefault();
        $.post("<c:url value="/ajaxservlet"/>", {"text":$('textarea').val()}, function(json){
                  $('textarea').empty();
                  $('#text_').text(json.post);
                },
                'json'
        ).fail(function (){
                  alert("fail(");
                })
      });
    });
  </script>

  <title>Ajax Test</title>

</head>
<body>



<form  accept-charset="UTF-8"  method="POST">
 <textarea   name="text"  rows="5" id="text"></textarea>
<button  type="submit" id="myButton" >Отправить</button>

</form>
<h1 id="text_"></h1>
</body>
</html>