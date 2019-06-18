package chess.domain.board;

import chess.domain.piece.PieceColor;
import org.junit.jupiter.api.Test;

import static org.assertj.core.api.Assertions.assertThat;

public class BoardTest {
    @Test
    void 보드_초기화() {
        Player whitePlayer = new Player(PieceColor.WHITE);
        Player blackPlayer = new Player(PieceColor.BLACK);

        Board board = new Board(whitePlayer, blackPlayer);

        assertThat(board.whitePiecesCount()).isEqualTo(16);
        assertThat(board.blackPiecesCount()).isEqualTo(16);
    }
}
