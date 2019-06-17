# java-chess
체스 게임 구현을 위한 저장소



**보드의 역할**
Board.class

```java

private Piece getPiece(Position source); // 특정 좌표의 말 식별

public void move(Position source, Position target); // 게임 진행 (보드는 말에게 받은 위치 리스트를 필터링 하여 최종적으로 갈 수 있는 위치리스트를 만듦)

```



**말의 역할**

Piece.class

```java
public boolean isAlive; // 죽었는지 살았는지
public abstract List<Position> movableList(Position source); // 갈 수 있는 좌표 반환
```

말은 보드에게 현재 위치를 받을 시 갈 수 있는 위치 리스트를 보드에게 넘겨줌

**좌표**

```java
class Position {
    int x;
	int y;
}
```

게임

```
체스 게임을 시작합니다.
> 게임 시작 : start
> 게임 종료 : end
> 게임 이동 : move b2 b3
```

​		