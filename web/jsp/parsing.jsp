<%--
  Created by IntelliJ IDEA.
  User: Vika
  Date: 24.08.2014
  Time: 15:29
  To change this template use File | Settings | File Templates.
--%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>Christmas gift</title>
</head>
<body>
<form action="/gift" method="post">
        <c:forEach var="elem" items="${gift}">
                <c:out value="${ elem.toString() }"/>
                <br/>
        </c:forEach>
</form>
</body>
</html>
