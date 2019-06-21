package chess.domain.path;

import java.util.function.Supplier;

public enum PathFactory {
    WHITE_PAWN(WhitePawnPath::new),
    BLACK_PAWN(BlackPawnPath::new),
    BISHOP(BishopPath::new),
    KING(KingPath::new),
    KNIGHT(KnightPath::new),
    QUEEN(QueenPath::new),
    ROOK(RookPath::new);

    private Supplier<Path> path;

    PathFactory(Supplier<Path> path) {
        this.path = path;
    }

    public Path create() {
        return path.get();
    }
}
