<tags:layout1 pageTitle="メッセージ一覧" pageCssClass="page-messages-list">
    <jsp:attribute name="pageHeadFragment">
        <script src="<c:url value="/resources/js/messages/list.js" />"></script>
    </jsp:attribute>
    <jsp:body>
        <h1>メッセージ一覧</h1>

        <div class="content">
            <tags:error_message_area1 visible="${errorMessage != null}" message="${errorMessage}" cssClass="error-message-area" />

            <table class="messageListTable table table-bordered table-condensed">
                <colgroup>
                    <col class="message-messageId">
                    <col class="message-title">
                    <col class="message-content">
                    <col class="message-validPeriodInDays">
                    <col class="message-createdDatetime">
                    <col class="message-operations">
                </colgroup>

                <thead>
                    <tr>
                        <th>ID</th>
                        <th>タイトル</th>
                        <th>本文</th>
                        <th>有効期間</th>
                        <th>作成日時</th>
                        <th>操作</th>
                    </tr>
                </thead>

                <tbody>
                    <c:forEach var="aMessage" items="${messages}" varStatus="status">
                        <tr>
                            <td class="message-messageId"><c:out value="${aMessage.messageId}" /></td>
                            <td class="message-title"><c:out value="${aMessage.title}" /></td>
                            <td class="message-content"><c:out value="${aMessage.content}" /></td>
                            <td class="message-validPeriodInDays"><c:out value="${aMessage.validPeriodInDays}" />日</td>

                            <td class="message-createdDatetime">
                                <fmt:formatDate value="${aMessage.createdDatetime}" pattern="yyyy/MM/dd HH:mm:ss" />
                            </td>

                            <td class="message-operations">
                                <a class="btn btn-xs btn-primary" href="<c:url value="/messages/id/${taglib:urlEncode(aMessage.messageId)}/edit" />">編集</a>
                                <a class="btn btn-xs btn-danger" href="<c:url value="/messages/id/${taglib:urlEncode(aMessage.messageId)}/delete" />">削除</a>
                            </td>
                        </tr>
                    </c:forEach>
                </tbody>
            </table>

            <a class="btn btn-primary" href="<c:url value="/messages/register" />">新規メッセージ</a>
        </div>
    </jsp:body>
</tags:layout1>
