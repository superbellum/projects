<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>

<html>
<head>
    <title>Save Customer</title>

    <link type="text/css" rel="stylesheet" href="<c:url value="/resources/css/style.css"/>"/>
    <link type="text/css" rel="stylesheet" href="<c:url value="/resources/css/add-customer-style.css"/>"/>

</head>
<body>

<div id="wrapper">
    <div id="header">
        <h2>CRM - Customer Relationship Manager</h2>
    </div>
</div>

<div id="container">
    <h3>Save Customer</h3>

    <form:form action="saveCustomer" modelAttribute="customer" method="post">

        <form:hidden path="id"/>

        <table>
            <tbody>
                <tr>
                    <td>First Name:</td>
                    <td><form:input path="firstName"/></td>
                </tr>
                <tr>
                    <td>Last Name:</td>
                    <td><form:input path="lastName"/></td>
                </tr>
                <tr>
                    <td>Email:</td>
                    <td><form:input path="email"/></td>
                </tr>
                <tr>
                    <td></td>
                    <td><input type="submit" value="Save" class="save"/></td>
                </tr>
            </tbody>
        </table>

    </form:form>

    <div style="clear: both;"></div>

    <p>
        <a href="${pageContext.request.contextPath}/customer/list">Back to List</a>
    </p>

</div>

</body>
</html>
