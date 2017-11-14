<tags:layout1 pageTitle="新規メッセージ登録完了" pageCssClass="page-messages-registercompleted">
    <jsp:body>
        <h1>新規メッセージ登録完了</h1>

        <div class="content">
            <div class="row">
                <div class="col-sm-9 col-sm-offset-3">
                    <div>以下のメッセージが登録されました。</div>
                    <div>タイトル<br><c:out value="${messageRegisterForm.message.title}" /></div>

                    <div class="links">
                        <a href="<c:url value="/messages/list" />">メッセージ一覧</a>
                    </div>
                </div>
            </div>
        </div>
    </jsp:body>
</tags:layout1>
