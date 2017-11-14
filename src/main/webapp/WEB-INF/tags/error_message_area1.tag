<%@ tag pageEncoding="UTF-8" body-content="empty" %>
<%@ attribute name="visible" type="java.lang.Boolean" required="true" %>
<%@ attribute name="message" required="true" %>
<%@ attribute name="cssClass" required="false" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<c:if test="${visible}">
    <div class="${cssClass}">
        <c:out value="${message}" />
    </div>
</c:if>
