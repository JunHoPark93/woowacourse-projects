const ArticleCardTemplate = function () {
    const articleCard = function (json) {
        return `
        <div class="card article-card widget-feed no-pdd mrg-btm-30" id="${json.id}">
            <div class="feed-header padding-15">
                <ul class="list-unstyled list-info">
                    <li>
                        <img class="thumb-img img-circle" src="${json.profileImage}" alt="">
                        <div class="info">
                            <a href="/members/${json.nickName}" class="title no-pdd-vertical text-bold inline-block font-size-15">${json.nickName}</a>
                        </div>
                    </li>
                    <a class="pointer absolute top-10 right-0" data-toggle="dropdown" aria-expanded="false">
                        <span class="btn-icon text-dark">
                            <i class="ti-more font-size-16"></i>
                        </span>
                    </a>
                    <ul class="dropdown-menu">
                        <li>
                            <a class="pointer">
                                <i class="ti-trash pdd-right-10 text-dark"></i>
                                <span class="">게시글 삭제</span>
                            </a>
                        </li>
                    </ul>
                </ul>
            </div>
            <div class="feed-body no-pdd">
                <img class="img-fluid" src="${json.image}" alt="">
            </div>
            <ul class="feed-action pdd-horizon-15 pdd-top-5">
                <li>
                    <a href="">
                        <i class="fa fa-heart-o font-size-25"></i>
                    </a>
                </li>
                <li>
                    <a href="">
                        <i class="ti-comment font-size-22"></i>
                    </a>
                </li>
                <li>
                    <a href="">
                        <i class="ti-export font-size-22"></i>
                    </a>
                </li>
                <li class="float-right">
                    <a href="" class="pdd-right-0">
                        <i class="fa fa-bookmark-o font-size-25"></i>
                    </a>
                </li>
            </ul>
            <div class="feedback-status-container pdd-horizon-15">
                <img class="mini-profile-img" src="images/default/eastjun_profile.jpg">
                <p class="no-mrg pdd-left-5 d-inline-block">
                    <span class="text-bold">eastjun</span>님 외 <span class="text-bold">37</span>명이 좋아합니다.
                </p>
            </div>
            <div class="feed-footer">
                <div class="comment">
                    <ul class="list-unstyled list-info pdd-horizon-5">
                        <li class=" no-pdd">
                            <div class="info pdd-left-15 pdd-vertical-5">
                                <a href="" class="title no-pdd-vertical text-bold inline-block font-size-15">${json.nickName}</a>
                                <p>${json.contents}</p>
                                <p>
                                    <a href="" class="hashtag">#우테코1기</a>
                                    <a href="" class="hashtag">#베스트커플상</a>
                                </p>
                                <time class="font-size-8 text-gray d-block">${json.lastModifiedDate}</time>
                                <span id="comment-preview-message-${json.id}"></span>
                            </div>
                        </li>
                    </ul>
                    <ul id="comment-list-${json.id}" class="list-unstyled list-info pdd-horizon-5">
                    </ul>
                    <p class="mrg-btm-5"></p>
                    <div id="article-comment-input-${json.id}" class="add-comment relative">
                        <input id="comment-input-${json.id}" rows="1" class="form-control text-dark padding-15"
                               placeholder="댓글 달기...">
                        <p class="absolute top-2 right-0">
                            <button class="btn btn-default no-border text-gray btn-add-comment">게시
                            </button>
                        </p>
                    </div>
                </div>
            </div>
        </div>`
    };

    const comment = function (json) {
        return `
        <li>
            <p class="inline-block text-bold no-mrg-btm mrg-left-15">
                ${json.commenterNickName}
            </p>
            <p class="inline-block no-mrg-btm mrg-left-5">
                ${json.commentContents}
            </p>
        </li>`
    };

    const commentPreviewMessage = function (commentSize) {
        return `
        <a class="text-gray text-bold no-mrg"
           href="">댓글 ${commentSize}개 모두보기</a>
        `
    };

    return {
        articleCard: articleCard,
        comment: comment,
        commentPreviewMessage: commentPreviewMessage,
    }
};

// export { ArticleCardTemplate }
