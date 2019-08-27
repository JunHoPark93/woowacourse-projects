const MemberCardTemplate = function () {
    const memberTemplate = function (json) {
        return `
    <div class="pdd-vertical-10 pdd-horizon-20 mrg-vertical-5">
        <img class="thumb-img img-circle" src="${json.profileImage}" alt="">
        <div class="info">
            <span class="title lh-1-5">${json.nickName}</span>
            <span class="sub-title">
                <span>${json.name}</span>
            </span>
        </div>
    </div>`
    };

    return {
        memberTemplate: memberTemplate,
    }
};
