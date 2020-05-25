
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags" %>
<html>
<head>
    <title>wszystkie pożyczki</title>
</head>
<body>

<h1><spring:message code="app.loan"/></h1>


<table border='1' style='border-collapse:collapse'>

    <tbody>
    <th>id</th>
    <th>opis</th>
    <th>typ</th>
    <th>wartość</th>


    <c:forEach items="${loans}" var="loan">
        <tr>
            <td><c:out value="${loan.id}"/></td>
            <td><c:out value="${loan.description}"/></td>
            <td><c:out value="${loan.loanType}"/></td>
            <td><c:out value="${loan.value}"/></td>
              </tr>
    </c:forEach>
    </tbody>
</table>
</body>
</html>
