<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>


<!DOCTYPE html>
<html>
<head>
    <title>Student Confirmation</title>
</head>
<body>

First name: ${student.firstName} <br/>
Last name: ${student.lastName} <br/>
Country: ${student.country}  <br/>
Favorite programming language: ${student.favouriteProgrammingLanguage} <br/>
Operating systems:
<ul>
    <c:forEach var="temp" items="${student.operatingSystems}">
        <li>${temp}</li>
    </c:forEach>
</ul>


</body>
</html>
