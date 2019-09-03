const Time = function () {
    const postedTime = function (date) {
        const currentDate = new Date();
        const postedDate = new Date(date);
        const elapsed = Math.floor((currentDate.getTime() - postedDate.getTime()) / 1000);

        if (elapsed >= 24 * 60 * 60) {
            return String(postedDate.getUTCFullYear() + '년 ' + (postedDate.getMonth() + 1) + '월 ' + postedDate.getDate() + '일');
        } else if (elapsed <= 60 * 60) {
            return String(Math.floor(elapsed / 60) + '분 전');
        }

        return String(Math.floor(elapsed / (60 * 60)) + '시간 전');
    };

    return {
        postedTime: postedTime
    }
};