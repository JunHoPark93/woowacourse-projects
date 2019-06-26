package chess.domain.board;

import chess.domain.piece.*;

import java.util.HashMap;
import java.util.Map;

public class PlayerFactory {
    public static Map<Square, Piece> init(PieceColor color) {
        Map<Square, Piece> pieces = new HashMap<>();

        if (color == PieceColor.WHITE) {
            // Pawn
            pieces.put(new Square(new XPosition("a"), new YPosition("2")), Pawn.createWhite());
            pieces.put(new Square(new XPosition("b"), new YPosition("2")), Pawn.createWhite());
            pieces.put(new Square(new XPosition("c"), new YPosition("2")), Pawn.createWhite());
            pieces.put(new Square(new XPosition("d"), new YPosition("2")), Pawn.createWhite());
            pieces.put(new Square(new XPosition("e"), new YPosition("2")), Pawn.createWhite());
            pieces.put(new Square(new XPosition("f"), new YPosition("2")), Pawn.createWhite());
            pieces.put(new Square(new XPosition("g"), new YPosition("2")), Pawn.createWhite());
            pieces.put(new Square(new XPosition("h"), new YPosition("2")), Pawn.createWhite());

            // Knight
            pieces.put(new Square(new XPosition("b"), new YPosition("1")), Knight.createWhite());
            pieces.put(new Square(new XPosition("g"), new YPosition("1")), Knight.createWhite());

            // Rook
            pieces.put(new Square(new XPosition("a"), new YPosition("1")), Rook.createWhite());
            pieces.put(new Square(new XPosition("h"), new YPosition("1")), Rook.createWhite());

            // BishopPath
            pieces.put(new Square(new XPosition("c"), new YPosition("1")), Bishop.createWhite());
            pieces.put(new Square(new XPosition("f"), new YPosition("1")), Bishop.createWhite());

            //Queen
            pieces.put(new Square(new XPosition("d"), new YPosition("1")), Queen.createWhite());

            //King
            pieces.put(new Square(new XPosition("e"), new YPosition("1")), King.createWhite());

            return pieces;
        }

        // black
        // Pawn
        pieces.put(new Square(new XPosition("a"), new YPosition("7")), Pawn.createBlack());
        pieces.put(new Square(new XPosition("b"), new YPosition("7")), Pawn.createBlack());
        pieces.put(new Square(new XPosition("c"), new YPosition("7")), Pawn.createBlack());
        pieces.put(new Square(new XPosition("d"), new YPosition("7")), Pawn.createBlack());
        pieces.put(new Square(new XPosition("e"), new YPosition("7")), Pawn.createBlack());
        pieces.put(new Square(new XPosition("f"), new YPosition("7")), Pawn.createBlack());
        pieces.put(new Square(new XPosition("g"), new YPosition("7")), Pawn.createBlack());
        pieces.put(new Square(new XPosition("h"), new YPosition("7")), Pawn.createBlack());

        // Knight
        pieces.put(new Square(new XPosition("b"), new YPosition("8")), Knight.createBlack());
        pieces.put(new Square(new XPosition("g"), new YPosition("8")), Knight.createBlack());

        // Rook
        pieces.put(new Square(new XPosition("a"), new YPosition("8")), Rook.createBlack());
        pieces.put(new Square(new XPosition("h"), new YPosition("8")), Rook.createBlack());

        // BishopPath
        pieces.put(new Square(new XPosition("c"), new YPosition("8")), Bishop.createBlack());
        pieces.put(new Square(new XPosition("f"), new YPosition("8")), Bishop.createWhite());

        //Queen
        pieces.put(new Square(new XPosition("d"), new YPosition("8")), Queen.createBlack());

        //King
        pieces.put(new Square(new XPosition("e"), new YPosition("8")), King.createBlack());

        return pieces;
    }
}
