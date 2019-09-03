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
                const heartTag = event.target;

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
            .then(response => response.data.memberResponses)
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