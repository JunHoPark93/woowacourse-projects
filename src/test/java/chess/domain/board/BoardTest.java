package chess.domain.board;

import chess.domain.piece.Pawn;
import chess.domain.piece.Piece;
import chess.domain.piece.PieceColor;
import chess.domain.piece.Rook;
import org.junit.jupiter.api.Test;

import java.util.*;

import static org.assertj.core.api.Assertions.assertThat;

public class BoardTest {
    @Test
    void 실제_보드_초기화() {
        Player whitePlayer = new DefaultPlayer(PieceColor.WHITE);
        Player blackPlayer = new DefaultPlayer(PieceColor.BLACK);

        Board board = new Board(whitePlayer, blackPlayer);

        assertThat(board.whitePiecesCount()).isEqualTo(16);
        assertThat(board.blackPiecesCount()).isEqualTo(16);
    }

    @Test
    void 보드_해당_위치_말_확인() {
        Piece rook = new Rook(PieceColor.BLACK);
        Map<Square, Piece> black = new HashMap<>();
        black.put(new Square(new XPosition("a"), new YPosition("8")), rook);
        Player whitePlayer = new MockPlayer(PieceColor.WHITE, black);

        Piece pawn = new Pawn(PieceColor.WHITE);
        Map<Square, Piece> white = new HashMap<>();
        white.put(new Square(new XPosition("a"), new YPosition("2")), pawn);
        Player blackPlayer = new MockPlayer(PieceColor.BLACK, white);

        Board board = new Board(whitePlayer, blackPlayer);

        assertThat(board.getPiece(new Square(new XPosition("a"), new YPosition("8")))).isEqualTo(rook);
    }

    @Test
    void 보드_룩_움직임() {
        Piece rook = new Rook(PieceColor.BLACK);
        Map<Square, Piece> black = new HashMap<>();
        black.put(new Square(new XPosition("b"), new YPosition("7")), rook);
        Player whitePlayer = new MockPlayer(PieceColor.WHITE, black);

        Piece pawn = new Pawn(PieceColor.WHITE);
        Map<Square, Piece> white = new HashMap<>();
        white.put(new Square(new XPosition("b"), new YPosition("2")), pawn);
        Player blackPlayer = new MockPlayer(PieceColor.BLACK, white);

        Board board = new Board(whitePlayer, blackPlayer);

        Set<Square> moveList = board.moveList(new Square(new XPosition("b"), new YPosition("7")));
        List<Square> list = Arrays.asList(
                new Square(new XPosition("a"), new YPosition("7")),
                new Square(new XPosition("c"), new YPosition("7")),
                new Square(new XPosition("d"), new YPosition("7")),
                new Square(new XPosition("e"), new YPosition("7")),
                new Square(new XPosition("f"), new YPosition("7")),
                new Square(new XPosition("g"), new YPosition("7")),
                new Square(new XPosition("h"), new YPosition("7")),
                new Square(new XPosition("b"), new YPosition("8")),
                new Square(new XPosition("b"), new YPosition("6")),
                new Square(new XPosition("b"), new YPosition("5")),
                new Square(new XPosition("b"), new YPosition("4")),
                new Square(new XPosition("b"), new YPosition("3")),
                new Square(new XPosition("b"), new YPosition("2"))
        );
        Set<Square> expectedMoveList = new HashSet<>(list);
        assertThat(moveList).isEqualTo(expectedMoveList);
    }

    // TODO
    @Test
    void 보드_룩이_폰을잡는다() {
        Piece rook = new Rook(PieceColor.BLACK);
        Map<Square, Piece> black = new HashMap<>();
        black.put(new Square(new XPosition("a"), new YPosition("8")), rook);
        Player whitePlayer = new MockPlayer(PieceColor.WHITE, black);

        Piece pawn = new Pawn(PieceColor.WHITE);
        Map<Square, Piece> white = new HashMap<>();
        white.put(new Square(new XPosition("a"), new YPosition("2")), pawn);
        Player blackPlayer = new MockPlayer(PieceColor.BLACK, white);

        Board board = new Board(whitePlayer, blackPlayer);
    }
}
