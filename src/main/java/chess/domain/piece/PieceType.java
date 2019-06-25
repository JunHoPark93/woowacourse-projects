package chess.domain.piece;

public enum PieceType {
    BISHOP("Bishop"),
    KING("King"),
    KNIGHT("Knight"),
    PAWN("Pawn"),
    QUEEN("Queen"),
    ROOK("Rook");

    PieceType(String name) {
        this.name = name;
    }

    private String name;
}
