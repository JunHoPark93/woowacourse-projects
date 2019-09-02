let stompClient = null;

function socketConnect() {
    const socket = new SockJS('/zzazan');
    stompClient = Stomp.over(socket);
    stompClient.connect({}, function (frame) {
        const endpoint = readCookie('endpoint');
        stompClient.subscribe('/topics/follow-notification/' + endpoint, function (notification) {
            const data = JSON.parse(notification.body);
            const followerName = data.followee.nickName;
            const isFollow = data.follow;

            if (!isFollow) {
                vNotify.error({text: followerName + '이 당신을 언팔로우 했습니다', title: '언팔로우'});
                return;
            }
            vNotify.info({text: followerName + '이 당신을 팔로우 했습니다', title: '팔로우'});
        });
    });
}

function readCookie(name) {
    const nameEQ = name + "=";
    const ca = document.cookie.split(';');
    for (let i = 0; i < ca.length; i++) {
        let c = ca[i];
        while (c.charAt(0) === ' ') c = c.substring(1, c.length);
        if (c.indexOf(nameEQ) === 0) return c.substring(nameEQ.length, c.length);
    }
    return null;
}