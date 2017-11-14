<%@ tag pageEncoding="UTF-8" %>
<%@ attribute name="pageTitle" required="true" %>
<%@ attribute name="pageCssClass" required="true" %>
<%@ attribute name="pageHeadFragment" fragment="true" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html>
<html>
<head>
    <meta charset="UTF-8">
    <meta name="viewport" content="width=device-width, initial-scale=1">

    <meta http-equiv="pragma" content="no-cache">
    <meta http-equiv="cache-control" content="no-cache">
    <meta http-equiv="expires" content="0">

    <title><c:out value="${pageTitle}" /></title>

    <link rel="stylesheet" href="<c:url value="/resources/css/bootstrap.min.css" />">
    <link rel="stylesheet" href="<c:url value="/resources/css/main.css?v=1" />">

    <jsp:invoke fragment="pageHeadFragment" />
</head>
<body>
    <div class="container-fluid">
        <div class="page <c:out value="${pageCssClass}" />">
            <jsp:doBody />
        </div>
    </div>
</body>
</html>
