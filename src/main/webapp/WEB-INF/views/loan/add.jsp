<%--
  Created by IntelliJ IDEA.
  User: daniel
  Date: 09.05.2020
  Time: 15:13
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="form"
           uri="http://www.springframework.org/tags/form" %>

<html>
<head>
    <title>dodawanie kredytu</title>
</head>
<body>
<form:form method="POST" modelAttribute="loan">
   opis: <form:input path="description"/>
    <form:errors path="description"/>
    <br>

    wartość: <form:input path="value"/>
    <form:errors path="value"/>

    <br>
    typ: <form:select path="loanType" items="${loanTypes}"/>
    <form:errors path="loanType"/> <br>

    <input type="submit">
</form:form>
</body>
</html>
