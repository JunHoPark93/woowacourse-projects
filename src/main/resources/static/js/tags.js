const HASHTAG_PAGE = (function () {
    const Api = function () {
        const request = {
            get(path) {
                return axios.get(`${path}`);
            },
            post(path, data) {
                return axios.post(`${path}`, data);
            },
            put(path, data) {
                return axios.put(`${path}`, data);
            },
            delete(path) {
                return axios.delete(`${path}`);
            }
        };

        return {
            request: request
        };
    };

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
                }).catch(error => {
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

    const CommentService = function () {
        const request = new Api().request;

        const getCommentTemplate = function (nickName, commentContents) {
            return `<li>
                        <p class="inline-block text-bold  no-mrg-btm mrg-left-15">${nickName}</p>
                        <p class="inline-block no-mrg-btm mrg-left-5">${commentContents}</p>
                   </li>`;
        };

        const addComment = function (event) {
            const message = event.target.closest("div");
            const articleId = message.id.split("-")[2];

            let inputValue = message.querySelector("input").value;
            const commentList = message.parentElement.querySelector("#comment-list");

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
                    const nickName = res.data.commenterNickName;
                    const commentContents = res.data.commentContents;

                    const comment = getCommentTemplate(nickName, commentContents);
                    commentList.insertAdjacentHTML('beforeend', comment);
                    document.getElementById('comment-input').value = '';
                }).catch(err => {
                alert(err.response.data);
            });

        };

        return {
            addComment: addComment,
        }
    };

    const DdabongService = function () {
        const memberCardTemplate = new MemberCardTemplate();
        const request = new Api().request;

        function activeDdabong(el) {
            el.classList.remove('fa-heart-o');
            el.classList.add('fa-heart', 'activated-heart');
        }

        function disableDdabong(el) {
            el.classList.remove('fa-heart', 'activated-heart');
            el.classList.add('fa-heart-o');
        }

        const toggleHeart = function (event) {
            event.preventDefault();
            const message = event.target.closest("div");
            let articleId = message.id;

            const splits = articleId.split('-');
            if (splits.length > 1) {
                articleId = splits[splits.length - 1];
            }
            const ddabongCountTag = message.querySelector('.ddabong-message');

            request
                .get('/api/ddabongs/articles/' + articleId)
                .then(response => {
                    ddabongCountTag.innerText = response.data.count;
                    const heartTag = event.target.childNodes[1];

                    if (response.data.clicked === true) {
                        activeDdabong(heartTag);
                    } else {
                        disableDdabong(heartTag);
                    }
                });
        };

        const fetchDdabongMembers = function (event) {
            const message = event.target.closest("div").parentNode;
            const articleIdSplits = message.id.split("-");
            const articleId = articleIdSplits[articleIdSplits.length - 1];

            const ddabongUlTag = document.querySelector('#ddabong-ul');

            request
                .get('/api/ddabongs/members/' + articleId)
                .then(response => {
                    return response.data.memberResponses;
                })
                .then(memberResponses => {
                    ddabongUlTag.innerHTML = "";
                    memberResponses.forEach(member => {
                        const memberNode = document.createElement("li");
                        memberNode.innerHTML = memberCardTemplate.memberTemplate(member);
                        ddabongUlTag.appendChild(memberNode);
                    })
                })
        };

        return {
            activeDdabong: activeDdabong,
            toggleHeart: toggleHeart,
            fetchDdabongMembers: fetchDdabongMembers,
        }
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