package chess.domain.board;

import chess.domain.piece.Piece;
import chess.domain.piece.PieceColor;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

public class ChessObserver {
    private final List<Piece> deadList;

    public ChessObserver() {
        this.deadList = new ArrayList<>();
    }

    public void take(Piece deadPiece) {
        deadList.add(deadPiece);
    }

    public List<Piece> deadList(PieceColor color) {
        return deadList.stream()
                .filter(piece -> piece.isSameColor(color))
                .collect(Collectors.toList());
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        ChessObserver that = (ChessObserver) o;

        return deadList != null ? deadList.equals(that.deadList) : that.deadList == null;
    }

    @Override
    public int hashCode() {
        return deadList != null ? deadList.hashCode() : 0;
    }
}
