const INDEX_PAGE = (function () {
    const request = new Api().request;

    const IndexPageController = function () {
        const defaultArticlePaginationSize = 5;

        const articleService = new ArticleService();
        const commentService = new CommentService();
        const ddabongService = new DdabongService();

        const addComment = function () {
            document.querySelectorAll('.btn-add-comment')
                .forEach(el => el.addEventListener('click', commentService.addComment));
        };

        const toggleHeart = function () {
            document.querySelectorAll('.fa-heart-o')
                .forEach(el => el.addEventListener('click', ddabongService.toggleHeart));
        };

        const deleteArticle = function () {
            document.querySelectorAll('.delete-article')
                .forEach(el => el.addEventListener('click', articleService.deleteArticle));
        };

        const fetchDdabongMembers = function () {
            document.querySelectorAll('.ddabong-members')
                .forEach(el => el.addEventListener('click', ddabongService.fetchDdabongMembers));
        };

        const fetchArticles = function () {
            articleService.fetchArticlePages(Number.MAX_SAFE_INTEGER, defaultArticlePaginationSize);
        };

        const getScrollTop = function () {
            return (window.pageYOffset !== undefined) ? window.pageYOffset : (document.documentElement || document.body.parentNode || document.body).scrollTop;
        };

        const getDocumentHeight = function () {
            const body = document.body;
            const html = document.documentElement;

            return Math.max(
                body.scrollHeight, body.offsetHeight,
                html.clientHeight, html.scrollHeight, html.offsetHeight
            );
        };

        const init = function () {
            fetchArticles();
            addComment();
            toggleHeart();
            deleteArticle();
            fetchDdabongMembers();
        };

        const onscroll = function () {
            if (getScrollTop() === getDocumentHeight() - window.innerHeight) {
                const articleCards = document.querySelectorAll('.article-card');

                const minId = Array.from(articleCards).map(function (card) {
                    return parseInt(card.id, 10);
                }).reduce(function (previous, current) {
                    return previous > current ? current : previous;
                });

                articleService.fetchArticlePages(minId, defaultArticlePaginationSize);
            }
        };

        return {
            init: init,
            onscroll: onscroll,
        };
    };

    const ArticleService = function () {
        const defaultCommentPreviewSize = 2;
        const commentService = new CommentService();
        const ddabongService = new DdabongService();
        const indexArticles = document.querySelector("#index-articles");
        const articleCardTemplate = new ArticleCardTemplate();

        function addEventsToArticles() {
            document.querySelectorAll('.btn-add-comment')
                .forEach(el => el.addEventListener('click', commentService.addComment));

            document.querySelectorAll('.ddabong-area')
                .forEach(el => el.addEventListener('click', ddabongService.toggleHeart));

            document.querySelectorAll('.delete-article')
                .forEach(el => el.addEventListener('click', deleteArticle));

            document.querySelectorAll('.ddabong-members')
                .forEach(el => el.addEventListener('click', ddabongService.fetchDdabongMembers));
        }

        const fetchArticlePages = function (lastArticleId, size) {
            function createNewNode(tagName, innerHTML) {
                const node = document.createElement(tagName);
                node.innerHTML = innerHTML;
                return node;
            }

            function appendCommentsOnArticleCard(json) {
                const commentResponses = json.commentResponses;
                const commentSize = Object.keys(commentResponses).length;
                const commentList = document.querySelector(`#comment-list-${json.id}`);

                function appendComments(commentList, size) {
                    for (let i = 0; i < size; i++) {
                        commentList.insertAdjacentHTML('afterbegin', articleCardTemplate.comment(commentResponses[i]));
                    }
                }

                if (commentSize > defaultCommentPreviewSize) {
                    const commentPreviewMessage = document.querySelector(`#comment-preview-message-${json.id}`);
                    const commentPreviewMessageNode = createNewNode('li', articleCardTemplate.commentPreviewMessage(commentSize, json.id));
                    commentPreviewMessage.appendChild(commentPreviewMessageNode);

                    appendComments(commentList, defaultCommentPreviewSize);
                    return;
                }

                appendComments(commentList, commentSize);
            }

            request
                .get('/api/articles', {
                    lastArticleId: lastArticleId,
                    size: size,
                })
                .then(response => response.data)
                .then(data => {
                    data.forEach(function (json) {
                        const articleNode = createNewNode('span', articleCardTemplate.articleCard(json));
                        indexArticles.appendChild(articleNode);

                        if (json.ddabongClicked) {
                            const ddabongHeart = document.querySelector(`#ddabong-${json.id}`);
                            ddabongService.activeDdabong(ddabongHeart);
                        }

                        const ddabongMessage = document.querySelector(`#ddabong-message-${json.id}`);
                        ddabongMessage.innerText = json.ddabongCount;

                        appendCommentsOnArticleCard(json);

                        addEventsToArticles();
                    })
                })
        };

        const deleteArticle = function (event) {
            event.preventDefault();
            const message = event.target.closest("div");
            const articleId = message.parentElement.id;

            request
                .delete(`/articles/${articleId}`)
                .then(response => {
                    if (response.data === "SUCCESS") {
                        const childNode = message.parentNode;
                        const parentNode = childNode.parentNode;
                        parentNode.removeChild(childNode);
                        alert("게시글이 삭제되었습니다.");
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
            fetchArticlePages: fetchArticlePages,
            deleteArticle: deleteArticle,
        }
    };

    const indexPageController = new IndexPageController();

    const init = function () {
        indexPageController.init();
    };

    const onscroll = function () {
        indexPageController.onscroll();
    };

    return {
        init: init,
        onscroll: onscroll,
    }
})();

INDEX_PAGE.init();

window.onscroll = function () {
    INDEX_PAGE.onscroll();
};