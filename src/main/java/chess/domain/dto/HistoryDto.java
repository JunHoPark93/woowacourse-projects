package chess.domain.dto;

import chess.domain.board.Square;
import chess.domain.board.XPosition;
import chess.domain.board.YPosition;

public class HistoryDto {
    private String src;
    private String trg;

    public HistoryDto(String source, String target) {
        this.src = source;
        this.trg = target;
    }

    public void setSrc(String src) {
        this.src = src;
    }

    public void setTrg(String trg) {
        this.trg = trg;
    }

    public Square getSrc() {
        return new Square(new XPosition(src.substring(0 , 1)), new YPosition(src.substring(1, 2)));
    }

    public Square getTrg() {
        return new Square(new XPosition(trg.substring(0, 1)), new YPosition(trg.substring(1, 2)));
    }
}
