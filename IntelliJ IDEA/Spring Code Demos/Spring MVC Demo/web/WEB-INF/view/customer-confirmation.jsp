<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>

<!DOCTYPE html>
<html>
<head>
    <title>Customer Confirmation</title>
</head>
<body>

<strong>Customer:</strong> <br/>
First name: ${customer.firstName} <br/>
Last name: ${customer.lastName} <br/>
Free passes: ${customer.freePasses} <br/>
Postal code: ${customer.postalCode} <br/>
Course code: ${customer.courseCode} <br/>

</body>
</html>
