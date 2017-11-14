<tags:layout1 pageTitle="メッセージ編集" pageCssClass="page-messages-edit">
    <jsp:body>
        <h1>メッセージ編集</h1>

        <div class="content">
            <tags:error_message_area1 visible="${errorMessage != null}" message="${errorMessage}" cssClass="error-message-area" />

            <form:form modelAttribute="messageEditForm" action="${pageContext.request.contextPath}/messages/id/${taglib:urlEncode(messageId)}/edit" cssClass="form-horizontal">
                <tags:form_input1 path="message.title" label="タイトル" disabled="${disableForm}" errorMessageCssClass="error-message" />
                <tags:form_textarea1 path="message.content" label="本文" rows="10" disabled="${disableForm}" errorMessageCssClass="error-message" />
                <tags:form_input2 path="message.validPeriodInDays" label="有効期間" unitLabel="日" disabled="${disableForm}" errorMessageCssClass="error-message" />

                <div class="row">
                    <div class="col-sm-3 col-sm-offset-2">
                        <button type="submit" name="commit" class="btn btn-primary btn-block" ${disableForm ? 'disabled' : ''}>更新</button>
                    </div>

                    <div class="col-sm-3 col-sm-offset-2">
                        <a class="btn btn-info btn-block" href="<c:url value="/messages/list" />">戻る</a>
                    </div>
                </div>
            </form:form>
        </div>
    </jsp:body>
</tags:layout1>
