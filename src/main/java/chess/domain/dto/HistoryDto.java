package chess.domain.dto;

import chess.domain.board.Square;
import chess.domain.board.XPosition;
import chess.domain.board.YPosition;

public class HistoryDto {
    private String source;
    private String target;

    public HistoryDto(String source, String target) {
        this.source = source;
        this.target = target;
    }

    public Square getSource() {
        return new Square(new XPosition(source.substring(0 , 1)), new YPosition(source.substring(1, 2)));
    }

    public Square getTarget() {
        return new Square(new XPosition(target.substring(0, 1)), new YPosition(target.substring(1, 2)));
    }
}
