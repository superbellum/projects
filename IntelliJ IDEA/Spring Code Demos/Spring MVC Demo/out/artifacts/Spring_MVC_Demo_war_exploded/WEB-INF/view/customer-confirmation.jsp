<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>

<!DOCTYPE html>
<html>
<head>
    <title>Customer Confirmation</title>
</head>
<body>

<strong>Customer:</strong>
First name: ${customer.firstName}
Last name: ${customer.lastName}
Free passes: ${customer.freePasses}
Postal code: ${customer.postalCode}

</body>
</html>
