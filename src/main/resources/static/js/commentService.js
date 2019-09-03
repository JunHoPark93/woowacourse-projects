const CommentService = function () {
    const request = new Api().request;
    const articleCardTemplate = new ArticleCardTemplate();

    const addComment = function (event) {
        const message = event.target.closest("div");
        const articleIdSplits = message.id.split("-");
        const articleId = articleIdSplits[articleIdSplits.length - 1];

        const input = message.querySelector("input");
        let inputValue = input.value;
        const commentList = message.parentElement.querySelector("[id^='comment-list']");

        if (inputValue.length < 1 || inputValue.length > 500) {
            alert('댓글은 1글자 이상 500글자 이하로 입력해 주세요');
            return;
        }

        inputValue = inputValue.replace(/&/gi, "&amp;");
        inputValue = inputValue.replace(/</gi, "&lt;");
        inputValue = inputValue.replace(/>/gi, "&gt;");

        request
            .post('/' + articleId + '/comments/new', {contents: inputValue})
            .then(res => {
                const comment = articleCardTemplate.comment(res.data);
                commentList.insertAdjacentHTML('beforeend', comment);
                message.querySelector("input").value = '';
            })
            .catch(err => alert(err.response.data));
    };

    return {
        addComment: addComment,
    }
};