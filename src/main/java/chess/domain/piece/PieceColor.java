package chess.domain.piece;

public enum PieceColor {
    BLACK, WHITE;

    public PieceColor toggle() {
        if (this.equals(BLACK)) {
            return WHITE;
        }
        return BLACK;
    }
}
