const ArticleService = function () {
    const request = new Api().request;

    const deleteArticle = function (event) {
        event.preventDefault();
        const message = event.target.closest("div");
        const articleId = message.parentElement.id;

        request
            .delete('/articles/' + articleId)
            .then(response => {
                if (response.data === "SUCCESS") {
                    alert("게시글이 삭제되었습니다.");
                    window.location = '/';
                }
            })
            .catch(error => {
                const errRes = error.response;
                if (error.response.status === 401) {
                    alert(errRes.data.msg);
                }
            });
    };

    return {
        deleteArticle: deleteArticle,
    }
};