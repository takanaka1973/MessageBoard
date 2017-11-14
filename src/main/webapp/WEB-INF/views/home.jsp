<tags:layout1 pageTitle="ホーム" pageCssClass="page-home">
    <jsp:body>
        <h1>
            <c:out value="${applicationInfo.displayName}" />
        </h1>

        <div class="content">
            <a href="<c:url value="/messages/list" />">メッセージ一覧</a>
        </div>

        <div class="footer">
            <c:out value="${applicationInfo.copyrightNotice}" />
        </div>
    </jsp:body>
</tags:layout1>
