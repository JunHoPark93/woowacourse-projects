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
        const searchService = new SearchService();
        const articleService = new ArticleService();
        const commentService = new CommentService();
        const ddabongService = new DdabongService();

        const toggleSearchInput = function () {
            document.querySelector('.search-toggle').addEventListener('click', searchService.toggleSearchInput);
        };

        const showSearchedList = function () {
            document.querySelector(".search-input input").addEventListener('keyup', searchService.showSearchedList);
        };

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
            toggleSearchInput();
            showSearchedList();
            addComment();
            toggleHeart();
            deleteArticle();
            fetchDdabongMembers();
        };

        return {
            init: init,
        };
    };

    const SearchService = function () {
        const toggleSearchInput = function (event) {
            event.preventDefault();
            document.querySelector('.search-box').classList.toggle('active')
            document.querySelector(".search-input").classList.toggle("active")
            document.querySelector(".search-input input").focus()
        };

        const showSearchedList = function (event) {
            if (event.target.value.length > 0) {
                document.querySelector(".advanced-search").classList.add("active")
            } else {
                document.querySelector(".advanced-search").classList.remove("active")
            }
        };

        return {
            toggleSearchInput: toggleSearchInput,
            showSearchedList: showSearchedList,
        }
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
                    console.log(response);

                    if (response.data === "SUCCESS") {
                        alert("게시글이 삭제되었습니다.");
                        window.location = '/';
                    }
                }).catch(response => {
                console.log(response);
                alert("게시글에 대한 권한이 없습니다.");
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

            const inputValue = message.querySelector("input").value;
            const commentList = message.parentElement.querySelector("#comment-list");

            if (inputValue.length < 1 || inputValue.length > 500) {
                alert('댓글은 1글자 이상 500글자 이하로 입력해 주세요');
                return;
            }

            request
                .post('/' + articleId + '/comments/new', {contents: inputValue})
                .then(res => {
                    console.log(res);
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
                .get('/ddabongs/articles/' + articleId)
                .then(response => {
                    console.log(response);
                    ddabongCountTag.innerText = response.data.count;

                    if (response.data.clicked === true) {
                        activeDdabong(event.target);
                    } else {
                        disableDdabong(event.target);
                    }
                });
        };

        const fetchDdabongMembers = function (event) {
            const message = event.target.closest("div").parentNode;
            const articleIdSplits = message.id.split("-");
            const articleId = articleIdSplits[articleIdSplits.length - 1];

            const ddabongUlTag = document.querySelector('#ddabong-ul');

            console.log(articleId);
            request
                .get('/ddabongs/members/' + articleId)
                .then(response => {
                    console.log(response);
                    return response.data.memberResponses;
                })
                .then(memberResponses => {
                    ddabongUlTag.innerHTML = "";
                    memberResponses.forEach(member => {
                        const memberNode = document.createElement("LI");
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