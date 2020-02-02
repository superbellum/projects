<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>

<html>
<head>
    <title>Student Registration Form</title>
</head>
<body>

<form:form action="processForm" modelAttribute="student">

    First name: <form:input path="firstName"/>
    <br/><br/>
    Last name: <form:input path="lastName"/>
    <br/><br/>
    Country:
    <form:select path="country">

        <form:option value="Brazil" label="Brazil"/>
        <form:option value="France" label="France"/>
        <form:option value="Germany" label="Germany"/>
        <form:option value="USA" label="USA"/>
        <form:option value="Slovakia" label="Slovakia"/>

    </form:select>







    <input type="submit" value="Submit"/>

</form:form>

</body>
</html>
