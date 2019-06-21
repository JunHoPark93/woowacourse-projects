package chess.domain.board;

import chess.domain.path.PathFactory;
import chess.domain.piece.Piece;
import chess.domain.piece.PieceColor;
import chess.domain.piece.Rook;
import org.junit.jupiter.api.Test;

import java.util.HashMap;
import java.util.Map;
import java.util.Optional;

import static org.assertj.core.api.Assertions.assertThat;

public class PlayerTest {
    @Test
    void 플레이어_말_반환() {
        Piece rook = new Rook(PieceColor.BLACK, PathFactory.ROOK.create());
        Piece rook2 = new Rook(PieceColor.BLACK, PathFactory.ROOK.create());
        Map<Square, Piece> black = new HashMap<>();
        black.put(new Square(new XPosition("b"), new YPosition("7")), rook);
        black.put(new Square(new XPosition("f"), new YPosition("7")), rook2);
        Player blackPlayer = new MockPlayer(black);

        Optional<Piece> returnedRook = blackPlayer.getPiece(new Square(new XPosition("b"), new YPosition("7")));
        returnedRook.ifPresent(piece -> assertThat(piece).isEqualTo(rook));

        Optional<Piece> returnedRook2 = blackPlayer.getPiece(new Square(new XPosition("f"), new YPosition("7")));
        returnedRook2.ifPresent(piece -> assertThat(piece).isEqualTo(rook2));
    }
}
