<%@ tag pageEncoding="UTF-8" body-content="empty" %>
<%@ attribute name="path" required="true" %>
<%@ attribute name="label" required="true" %>
<%@ attribute name="unitLabel" required="true" %>
<%@ attribute name="disabled" type="java.lang.Boolean" required="false" %>
<%@ attribute name="errorMessageCssClass" required="false" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags" %>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<spring:bind path="${path}">
    <div class="form-group ${status.error ? 'has-error' : ''}">
        <form:label path="${path}" cssClass="control-label col-sm-2"><c:out value="${label}" /></form:label>

        <div class="col-sm-9">
            <div class="input-group">
                <form:input path="${path}" disabled="${disabled}" cssClass="form-control" />
                <div class="input-group-addon"><c:out value="${unitLabel}" /></div>
            </div>

            <form:errors path="${path}" element="div" cssClass="${errorMessageCssClass} text-danger" />
        </div>
    </div>
</spring:bind>
