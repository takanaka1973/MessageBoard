<tags:layout1 pageTitle="メッセージ削除" pageCssClass="page-messages-delete">
    <jsp:body>
        <h1>メッセージ削除</h1>

        <div class="content">
            <tags:error_message_area1 visible="${errorMessage != null}" message="${errorMessage}" cssClass="error-message-area" />

            <form:form action="${pageContext.request.contextPath}/messages/id/${taglib:urlEncode(messageId)}/delete" cssClass="form-horizontal">
                <div class="form-group">
                    <label class="control-label col-sm-2">タイトル</label>

                    <div class="col-sm-9">
                        <p class="form-control-static"><c:out value="${message.title}" /></p>
                    </div>
                </div>

                <div class="form-group">
                    <label class="control-label col-sm-2">本文</label>

                    <div class="col-sm-9">
                        <p class="form-control-static message-content">
                            <taglib:newLineToBr><c:out value="${message.content}" /></taglib:newLineToBr>
                        </p>
                    </div>
                </div>

                <div class="form-group">
                    <label class="control-label col-sm-2">有効期間</label>

                    <div class="col-sm-9">
                        <p class="form-control-static"><c:out value="${message.validPeriodInDays}" />&nbsp;日</p>
                    </div>
                </div>

                <div class="row">
                    <div class="col-sm-3 col-sm-offset-2">
                        <button type="submit" name="commit" class="btn btn-danger btn-block" ${(message == null) ? 'disabled' : ''}>削除</button>
                    </div>

                    <div class="col-sm-3 col-sm-offset-2">
                        <a class="btn btn-info btn-block" href="<c:url value="/messages/list" />">戻る</a>
                    </div>
                </div>
            </form:form>
        </div>
    </jsp:body>
</tags:layout1>
