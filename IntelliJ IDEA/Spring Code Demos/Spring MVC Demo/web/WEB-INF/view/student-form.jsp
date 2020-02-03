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

<%--        <form:option value="Brazil" label="Brazil"/>--%>
<%--        <form:option value="France" label="France"/>--%>
<%--        <form:option value="Germany" label="Germany"/>--%>
<%--        <form:option value="USA" label="USA"/>--%>
<%--        <form:option value="Slovakia" label="Slovakia"/>--%>

        <form:options items="${student.countryOptions}"/>

    </form:select>


    <br/>

    <br/>
    What is your favourite programming language?
    <br/>
    <br/>
    <form:radiobutton path="favouriteProgrammingLanguage" value="Java"/> Java
    <br/>
    <form:radiobutton path="favouriteProgrammingLanguage" value="Python"/> Python
    <br/>
    <form:radiobutton path="favouriteProgrammingLanguage" value="C++"/> C++
    <br/>
    <form:radiobutton path="favouriteProgrammingLanguage" value="Javascript"/> Javascript
    <br/>
    <form:radiobutton path="favouriteProgrammingLanguage" value="C#"/> C#
    <br/>

    <br/>
    Which operating systems do you have experience with?
    <br/>
    <form:checkbox path="operatingSystems" value="Linux"/> Linux
    <br/>
    <form:checkbox path="operatingSystems" value="Mac"/> Mac
    <br/>
    <form:checkbox path="operatingSystems" value="Windows"/> Windows
    <br/>


    <br/><br/>

    <input type="submit" value="Submit"/>

</form:form>

</body>
</html>
