var stompClient = null;

function socketConnect(sessionName) {
    var socket = new SockJS('/gs-guide-websocket');
    stompClient = Stomp.over(socket);
    stompClient.connect({}, function (frame) {
        console.log('Connected: ' + frame);
        console.log('sessionName : ' + sessionName);
        stompClient.subscribe('/topics/follow-notification/' + sessionName, function (notification) {
            console.log("구독 알람 !!");
            console.log(notification);
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

