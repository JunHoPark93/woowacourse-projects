# java-ladder
사다리타기 미션 저장소

## 우아한테크코스 코드리뷰
* [온라인 코드 리뷰 과정](https://github.com/woowacourse/woowacourse-docs/blob/master/maincourse/README.md)

## TODO
* 입력
    * 사다리 게임에 참여하는 사람에 이름을 최대5글자까지 부여할 수 있다. 사다리를 출력할 때 사람 이름도 같이 출력한다.
    * 사람 이름은 쉼표(,)를 기준으로 구분한다.
* 사다리
    * 사람 이름을 5자 기준으로 출력하기 때문에 사다리 폭도 넓어져야 한다.
    * 사다리 타기가 정상적으로 동작하려면 라인이 겹치지 않도록 해야 한다.
    * |-----|-----| 모양과 같이 가로 라인이 겹치는 경우 어느 방향으로 이동할지 결정할 수 없다.
    * 포지션은 (왼쪽, 오른쪽, 상태없음) 로 정의한다.
