package chess.domain.board;


import chess.domain.path.PathFactory;
import chess.domain.piece.*;

import java.util.HashMap;
import java.util.Map;

public class PlayerFactory {
    public static Map<Square, Piece> init(PieceColor color) {
        Map<Square, Piece> pieces = new HashMap<>();

        if (color == PieceColor.WHITE) {
            // Pawn
            pieces.put(new Square(new XPosition("a"), new YPosition("2")), new Pawn(PieceColor.WHITE, PathFactory.WHITE_PAWN.create()));
            pieces.put(new Square(new XPosition("b"), new YPosition("2")), new Pawn(PieceColor.WHITE, PathFactory.WHITE_PAWN.create()));
            pieces.put(new Square(new XPosition("c"), new YPosition("2")), new Pawn(PieceColor.WHITE, PathFactory.WHITE_PAWN.create()));
            pieces.put(new Square(new XPosition("d"), new YPosition("2")), new Pawn(PieceColor.WHITE, PathFactory.WHITE_PAWN.create()));
            pieces.put(new Square(new XPosition("e"), new YPosition("2")), new Pawn(PieceColor.WHITE, PathFactory.WHITE_PAWN.create()));
            pieces.put(new Square(new XPosition("f"), new YPosition("2")), new Pawn(PieceColor.WHITE, PathFactory.WHITE_PAWN.create()));
            pieces.put(new Square(new XPosition("g"), new YPosition("2")), new Pawn(PieceColor.WHITE, PathFactory.WHITE_PAWN.create()));
            pieces.put(new Square(new XPosition("h"), new YPosition("2")), new Pawn(PieceColor.WHITE, PathFactory.WHITE_PAWN.create()));

            // Knight
            pieces.put(new Square(new XPosition("b"), new YPosition("1")), new Knight(PieceColor.WHITE, PathFactory.KNIGHT.create()));
            pieces.put(new Square(new XPosition("g"), new YPosition("1")), new Knight(PieceColor.WHITE, PathFactory.KNIGHT.create()));

            // Rook
            pieces.put(new Square(new XPosition("a"), new YPosition("1")), new Rook(PieceColor.WHITE, PathFactory.ROOK.create()));
            pieces.put(new Square(new XPosition("h"), new YPosition("1")), new Rook(PieceColor.WHITE, PathFactory.ROOK.create()));

            // BishopPath
            pieces.put(new Square(new XPosition("c"), new YPosition("1")), new Bishop(PieceColor.WHITE, PathFactory.BISHOP.create()));
            pieces.put(new Square(new XPosition("f"), new YPosition("1")), new Bishop(PieceColor.WHITE, PathFactory.BISHOP.create()));

            //Queen
            pieces.put(new Square(new XPosition("d"), new YPosition("1")), new Queen(PieceColor.WHITE, PathFactory.QUEEN.create()));

            //King
            pieces.put(new Square(new XPosition("e"), new YPosition("1")), new King(PieceColor.WHITE, PathFactory.KING.create()));

            return pieces;
        }

        // black
        // Pawn
        pieces.put(new Square(new XPosition("a"), new YPosition("7")), new Pawn(PieceColor.BLACK, PathFactory.BLACK_PAWN.create()));
        pieces.put(new Square(new XPosition("b"), new YPosition("7")), new Pawn(PieceColor.BLACK, PathFactory.BLACK_PAWN.create()));
        pieces.put(new Square(new XPosition("c"), new YPosition("7")), new Pawn(PieceColor.BLACK, PathFactory.BLACK_PAWN.create()));
        pieces.put(new Square(new XPosition("d"), new YPosition("7")), new Pawn(PieceColor.BLACK, PathFactory.BLACK_PAWN.create()));
        pieces.put(new Square(new XPosition("e"), new YPosition("7")), new Pawn(PieceColor.BLACK, PathFactory.BLACK_PAWN.create()));
        pieces.put(new Square(new XPosition("f"), new YPosition("7")), new Pawn(PieceColor.BLACK, PathFactory.BLACK_PAWN.create()));
        pieces.put(new Square(new XPosition("g"), new YPosition("7")), new Pawn(PieceColor.BLACK, PathFactory.BLACK_PAWN.create()));
        pieces.put(new Square(new XPosition("h"), new YPosition("7")), new Pawn(PieceColor.BLACK, PathFactory.BLACK_PAWN.create()));

        // Knight
        pieces.put(new Square(new XPosition("b"), new YPosition("8")), new Knight(PieceColor.BLACK, PathFactory.KNIGHT.create()));
        pieces.put(new Square(new XPosition("g"), new YPosition("8")), new Knight(PieceColor.BLACK, PathFactory.KNIGHT.create()));

        // Rook
        pieces.put(new Square(new XPosition("a"), new YPosition("8")), new Rook(PieceColor.BLACK, PathFactory.ROOK.create()));
        pieces.put(new Square(new XPosition("h"), new YPosition("8")), new Rook(PieceColor.BLACK, PathFactory.ROOK.create()));

        // BishopPath
        pieces.put(new Square(new XPosition("c"), new YPosition("8")), new Bishop(PieceColor.BLACK, PathFactory.BISHOP.create()));
        pieces.put(new Square(new XPosition("f"), new YPosition("8")), new Bishop(PieceColor.BLACK, PathFactory.BISHOP.create()));

        //Queen
        pieces.put(new Square(new XPosition("d"), new YPosition("8")), new Queen(PieceColor.BLACK, PathFactory.QUEEN.create()));

        //King
        pieces.put(new Square(new XPosition("e"), new YPosition("8")), new King(PieceColor.BLACK, PathFactory.KING.create()));

        return pieces;
    }
}
