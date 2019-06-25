package chess.domain.dto;

import chess.domain.board.Square;
import chess.domain.board.XPosition;
import chess.domain.board.YPosition;

public class MovableDto {
    private String src;

    public Square getSrc() {
        return new Square(new XPosition(src.substring(0 , 1)), new YPosition(src.substring(1, 2)));
    }

    public void setSrc(String src) {
        this.src = src;
    }
}
