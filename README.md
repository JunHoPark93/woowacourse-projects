# 우아한 프리코스 2주차
> 본 프로젝트는 우아한 형제들의 테크코스, 2주차 미션인 자동차 경주 게임 프로젝트 입니다.

## 기능 목록
1. 자동차 이름은 쉼표(,)를 기준으로 구분하며 이름은 5자 이하만 가능하다.
2. 사용자는 몇 번의 이동을 할 것인지를 입력할 수 있어야 한다. 
3. 주어진 횟수 동안 n대의 자동차는 전진 또는 멈출 수 있다. 
4. 각 자동차에 이름을 부여할 수 있다. 전진하는 자동차를 출력할 때 자동차 이름을 같이 출력한다. 
5. 전진하는 조건은 0에서 9 사이에서 random 값을 구한 후 random 값이 4 이상일 경우 전진하고, 3 이
하의 값이면 멈춘다. 
6. 자동차 경주 게임을 완료한 후 누가 우승했는지를 알려준다. 우승자는 한 명 이상일 수 있다.

## 프로그램 요구 사항
* 자바 코드 컨벤션을 지키면서 프로그래밍한다. 
* indent(인덴트, 들여쓰기) depth를 3이 넘지 않도록 구현한다. 2까지만 허용한다. 
* 함수(또는 메소드)의 길이가 15라인을 넘어가지 않도록 구현한다. 
* else 예약어를 쓰지 않는다. 

## 폴더 구조

```
|-- java
|   -- com
|       -- junhopark
|           -- javaracingcar
|               |-- domain
|               |   -- Car.java
|               |-- util
|               |   |-- GameUtil.java
|               |   |-- GameValidator.java
|               |-- GamePlay.java
|               |-- Main.java
| -- test
    -- com
        -- junhopark
            -- javaracingcar
                -- util
                    |-- GameUtilTest.java
                    |-- GameValidatorTest.java
```
* Car.java : 자동차 클래스
* GameUtil.java : Game을 진행하는데 필요한 기능들을 하는 클래스
* GameValidator.java : Game을 진행하는데 값의 검증을 도와주는 클래스
* GamePlay.java : Game 1회를 진행하는데 필요한 클래스
* GameUtilTest : GameUtil 클래스의 모든 기능들을 테스트하는 클래스
* GameValidatorTest : GameValidator 클래스의 모든 기능들을 테스트하는 클래스

## 실행 방법
```
java/com/junhopark/javaracingcar/Main.java
```
파일에서 main 함수를 실행한다. 

#### 정상적 입/출력 예시

```
경주할 자동차 이름을 입력하세요.(이름은 쉼표(,) 기준으로 구분)
pobi,crong,honux
시도할 회수는 몇회인가요?
5

실행결과
pobi : -
crong : -
honux : 

pobi : -
crong : --
honux : 

pobi : --
crong : --
honux : 

pobi : ---
crong : ---
honux : 

pobi : ----
crong : ----
honux : -

pobi, crong가 최종 우승했습니다.
```
#### 비정상적 입/출력 예시

```
경주할 자동차 이름을 입력하세요.(이름은 쉼표(,) 기준으로 구분)
pobi,crong,honux,woowahanpobi
유효하지 않은 입력입니다. 재입력 입력하세요.
pobi,crong,,honux
유효하지 않은 입력입니다. 재입력 입력하세요.

시도할 회수는 몇회인가요?
woowa
유효하지 않은 입력입니다. 재입력 입력하세요.
```


