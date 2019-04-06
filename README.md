# 우아한 프리코스 3주차
> 본 프로젝트는 우아한 형제들의 프리코스, 3주차 미션인 로또 프로젝트 입니다.	

## 기능 목록	
* 로또 게임 기능을 구현해야 한다. 
* 로또 구입 금액을 입력하면 구입 금액에 해당하는 로또를 발급해야 한다. 
* 로또 1장의 가격은 1000원이다. 
* 로또 당첨 금액은 고정되어 있는 것으로 가정한다. 
* 수익률을 계산해 출력해야 한다.

## 프로그램 요구사항

* 자바 코드 컨벤션을 지키면서 프로그래밍 한다.
* else 예약어를 쓰지 않는다.
* public/protected/private/package 접근 제어자를 용도에 적합하게 사용해 구현한다.
* 함수의 길이가 10라인을 넘어가지 않도록 구현한다.
* indent depth를 2가 넘어가지 않도록 구현한다. 1까지만 허용한다.
* 함수의 인자 수를 3개까지만 허용한다. 

* 추가 요구사항

```java
import java.util.List;

/**
 * 로또 한장을 의미하는 객체
 */
public class Lotto {
    private final List<Integer> numbers;

    public Lotto(List<Integer> numbers) {
        this.numbers = numbers;
    }

    // 추가 기능 구현
}
```
* 다음 Lotto 객체를 활용해 구현해야 한다. 
* Lotto 기본 생성자를 추가할 수 없다. 
* numbers 변수의 접근 제어자인 private을 변경할 수 없다. 
* Lotto에 필드(인스턴스 변수)를 추가할 수 없다.

```java
/**
 * 당첨 번호를 담당하는 객체
 */
public class WinningLotto {
    private final Lotto lotto;
    private final int bonusNo;

    public WinningLotto(Lotto lotto, int bonusNo) {
        this.lotto = lotto;
        this.bonusNo = bonusNo;
    }

    public Rank match(Lotto userLotto) {
        // TODO 로직 구현
        return null;
    }
}
```
* 다음 WinningLotto 객체를 활용해 구현해야 한다. 
* match() 메소드의 반환 값인 Rank는 저장소에서 제공한다. 
* WinningLotto 기본 생성자를 추가할 수 없다. 
* lotto, bonusNo 변수의 접근 제어자인 private을 변경할 수 없다. 
* WinningLotto에 필드(인스턴스 변수)를 추가할 수 없다.

