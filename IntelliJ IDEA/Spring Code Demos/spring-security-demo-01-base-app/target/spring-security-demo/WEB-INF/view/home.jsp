<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="security" uri="http://www.springframework.org/security/tags" %>

<html>

<head>

    <title>Home</title>

    <link href="${pageContext.request.contextPath}/resources/css/lll.css" type="text/css" rel="stylesheet">

</head>

<body>

<h1>Welcome home!</h1>

<hr>

<p>
    User: <security:authentication property="principal.username"/>
    <br><br>
    Roles: <security:authentication property="principal.authorities"/>
</p>

<hr>

<security:authorize access="hasRole('MANAGER')">

    <p>
        <a href="${pageContext.request.contextPath}/leaders">Leadership Meeting</a>
        (only for managers)
    </p>

</security:authorize>

<security:authorize access="hasRole('ADMIN')">

    <p>
        <a href="${pageContext.request.contextPath}/systems">IT Systems Meeting</a>
        (only for admins)
    </p>

</security:authorize>

<hr>


<!-- logout button -->
<form:form action="${pageContext.request.contextPath}/logout" method="post">

    <input type="submit" value="Logout"/>

</form:form>

</body>

</html>
