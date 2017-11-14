<tags:layout1 pageTitle="新規メッセージ確認" pageCssClass="page-messages-register-confirm">
    <jsp:body>
        <h1>新規メッセージ確認</h1>

        <div class="content">
            <tags:error_message_area1 visible="${errorMessage != null}" message="${errorMessage}" cssClass="error-message-area" />

            <form:form modelAttribute="messageRegisterForm" action="${pageContext.request.contextPath}/messages/register" cssClass="form-horizontal">
                <div class="form-group">
                    <form:label path="message.title" cssClass="control-label col-sm-2">タイトル</form:label>

                    <div class="col-sm-9">
                        <form:input path="message.title" cssClass="form-control" readonly="true" />
                    </div>
                </div>

                <div class="form-group">
                    <form:label path="message.content" cssClass="control-label col-sm-2">本文</form:label>

                    <div class="col-sm-9">
                        <form:textarea path="message.content" cssClass="form-control" readonly="true" rows="10" />
                    </div>
                </div>

                <div class="form-group">
                    <form:label path="message.validPeriodInDays" cssClass="control-label col-sm-2">有効期間</form:label>

                    <div class="col-sm-9">
                        <div class="input-group">
                            <form:input path="message.validPeriodInDays" cssClass="form-control" readonly="true" />
                            <div class="input-group-addon">日</div>
                        </div>
                    </div>
                </div>

                <div class="row">
                    <div class="col-sm-3 col-sm-offset-2">
                        <button type="submit" name="commit" class="btn btn-primary btn-block">登録</button>
                    </div>

                    <div class="col-sm-3 col-sm-offset-2">
                        <button type="submit" name="redo" class="btn btn-info btn-block">修正</button>
                    </div>
                </div>
            </form:form>
        </div>
    </jsp:body>
</tags:layout1>
