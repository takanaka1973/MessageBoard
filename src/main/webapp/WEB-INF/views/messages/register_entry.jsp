<tags:layout1 pageTitle="新規メッセージ" pageCssClass="page-messages-register-entry">
    <jsp:body>
        <h1>新規メッセージ</h1>

        <div class="content">
            <form:form modelAttribute="messageRegisterForm" action="${pageContext.request.contextPath}/messages/register" cssClass="form-horizontal">
                <tags:form_input1 path="message.title" label="タイトル" errorMessageCssClass="error-message" />
                <tags:form_textarea1 path="message.content" label="本文" rows="10" errorMessageCssClass="error-message" />
                <tags:form_input2 path="message.validPeriodInDays" label="有効期間" unitLabel="日" errorMessageCssClass="error-message" />

                <div class="row">
                    <div class="col-sm-3 col-sm-offset-2">
                        <button type="submit" name="confirm" class="btn btn-primary btn-block">確認</button>
                    </div>

                    <div class="col-sm-3 col-sm-offset-2">
                        <a class="btn btn-info btn-block" href="<c:url value="/messages/list" />">戻る</a>
                    </div>
                </div>
            </form:form>
        </div>
    </jsp:body>
</tags:layout1>
