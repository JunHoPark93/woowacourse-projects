package chess.domain.board;


import chess.domain.piece.*;

import java.util.HashMap;
import java.util.Map;

public class PlayerFactory {
    public static Map<Square, Piece> init(PieceColor color) {
        Map<Square, Piece> pieces = new HashMap<>();

        if (color == PieceColor.WHITE) {
            // Pawn
            pieces.put(new Square(new XPosition("a"), new YPosition("2")), new Pawn(PieceColor.WHITE));
            pieces.put(new Square(new XPosition("b"), new YPosition("2")), new Pawn(PieceColor.WHITE));
            pieces.put(new Square(new XPosition("c"), new YPosition("2")), new Pawn(PieceColor.WHITE));
            pieces.put(new Square(new XPosition("d"), new YPosition("2")), new Pawn(PieceColor.WHITE));
            pieces.put(new Square(new XPosition("e"), new YPosition("2")), new Pawn(PieceColor.WHITE));
            pieces.put(new Square(new XPosition("f"), new YPosition("2")), new Pawn(PieceColor.WHITE));
            pieces.put(new Square(new XPosition("g"), new YPosition("2")), new Pawn(PieceColor.WHITE));
            pieces.put(new Square(new XPosition("h"), new YPosition("2")), new Pawn(PieceColor.WHITE));

            // Knight
            pieces.put(new Square(new XPosition("b"), new YPosition("1")), new Knight(PieceColor.WHITE));
            pieces.put(new Square(new XPosition("g"), new YPosition("1")), new Knight(PieceColor.WHITE));

            // Rook
            pieces.put(new Square(new XPosition("a"), new YPosition("1")), new Rook(PieceColor.WHITE));
            pieces.put(new Square(new XPosition("h"), new YPosition("1")), new Rook(PieceColor.WHITE));

            // Bishop
            pieces.put(new Square(new XPosition("c"), new YPosition("1")), new Bishop(PieceColor.WHITE));
            pieces.put(new Square(new XPosition("f"), new YPosition("1")), new Bishop(PieceColor.WHITE));

            //Queen
            pieces.put(new Square(new XPosition("d"), new YPosition("1")), new Queen(PieceColor.WHITE));

            //King
            pieces.put(new Square(new XPosition("e"), new YPosition("1")), new King(PieceColor.WHITE));

            return pieces;
        }

        // black
        // Pawn
        pieces.put(new Square(new XPosition("a"), new YPosition("7")), new Pawn(PieceColor.BLACK));
        pieces.put(new Square(new XPosition("b"), new YPosition("7")), new Pawn(PieceColor.BLACK));
        pieces.put(new Square(new XPosition("c"), new YPosition("7")), new Pawn(PieceColor.BLACK));
        pieces.put(new Square(new XPosition("d"), new YPosition("7")), new Pawn(PieceColor.BLACK));
        pieces.put(new Square(new XPosition("e"), new YPosition("7")), new Pawn(PieceColor.BLACK));
        pieces.put(new Square(new XPosition("f"), new YPosition("7")), new Pawn(PieceColor.BLACK));
        pieces.put(new Square(new XPosition("g"), new YPosition("7")), new Pawn(PieceColor.BLACK));
        pieces.put(new Square(new XPosition("h"), new YPosition("7")), new Pawn(PieceColor.BLACK));

        // Knight
        pieces.put(new Square(new XPosition("b"), new YPosition("8")), new Knight(PieceColor.BLACK));
        pieces.put(new Square(new XPosition("g"), new YPosition("8")), new Knight(PieceColor.BLACK));

        // Rook
        pieces.put(new Square(new XPosition("a"), new YPosition("8")), new Rook(PieceColor.BLACK));
        pieces.put(new Square(new XPosition("h"), new YPosition("8")), new Rook(PieceColor.BLACK));

        // Bishop
        pieces.put(new Square(new XPosition("c"), new YPosition("8")), new Bishop(PieceColor.BLACK));
        pieces.put(new Square(new XPosition("f"), new YPosition("8")), new Bishop(PieceColor.BLACK));

        //Queen
        pieces.put(new Square(new XPosition("d"), new YPosition("8")), new Queen(PieceColor.BLACK));

        //King
        pieces.put(new Square(new XPosition("e"), new YPosition("8")), new King(PieceColor.BLACK));

        return pieces;
    }
}
