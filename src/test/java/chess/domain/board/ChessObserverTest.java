package chess.domain.board;

import chess.domain.piece.Piece;
import chess.domain.piece.PieceColor;
import chess.domain.piece.Rook;
import org.junit.jupiter.api.Test;

import java.util.ArrayList;
import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;

class ChessObserverTest {
    @Test
    void 죽은_유닛_추가() {
        ChessObserver chessObserver = new ChessObserver();
        Piece rook = Rook.createWhite();
        chessObserver.take(rook);
        List<Piece> deadList = new ArrayList<>();
        deadList.add(rook);

        assertThat(chessObserver.deadList(PieceColor.WHITE)).isEqualTo(deadList);
    }
}