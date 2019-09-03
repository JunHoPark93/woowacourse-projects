const HASHTAG_PAGE = (function () {
    const HashTagPageController = function () {
        const articleService = new ArticleService();
        const commentService = new CommentService();
        const ddabongService = new DdabongService();

        const addComment = function () {
            document.querySelectorAll('.btn-add-comment')
                .forEach(el => el.addEventListener('click', commentService.addComment));
        };

        const toggleHeart = function () {
            document.querySelectorAll('.fa-heart-o')
                .forEach((el) => {
                    el.addEventListener('click', ddabongService.toggleHeart);
                });

            document.querySelectorAll('.fa-heart')
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

        const init = function () {
            addComment();
            toggleHeart();
            deleteArticle();
            fetchDdabongMembers();
        };

        return {
            init: init,
        };
    };

    const init = function () {
        const hashTagPageController = new HashTagPageController();
        hashTagPageController.init();
    };

    return {
        init: init,
    }
})();

HASHTAG_PAGE.init();