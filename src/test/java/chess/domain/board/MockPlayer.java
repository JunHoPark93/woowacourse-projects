package chess.domain.board;


import chess.domain.piece.King;
import chess.domain.piece.Piece;
import chess.domain.piece.PieceColor;
import org.assertj.core.data.MapEntry;

import java.util.HashSet;
import java.util.Map;
import java.util.Optional;
import java.util.Set;
import java.util.stream.Collectors;

public class MockPlayer implements Player {
    private final PieceColor color;
    private Map<Square, Piece> pieces;

    public MockPlayer(PieceColor color, Map<Square, Piece> pieces) {
        this.color = color;
        this.pieces = pieces;
    }
    @Override
    public int getPiecesCount() {
        return 0;
    }

    @Override
    public Optional<Piece> getPiece(Square source) {
        return Optional.ofNullable(pieces.get(source));
    }

    @Override
    public boolean contains(Vector vector) {
        return pieces.containsKey(vector.getSquare());
    }

    @Override
    public Set<Square> getKingPath() {
        Set<Vector> set = new HashSet<>();
        for (Map.Entry<Square, Piece> entry : pieces.entrySet()) {
            if (entry.getValue() instanceof King) {
                set = entry.getValue().movableList(entry.getKey());
            }
        }

        return set.stream().map(Vector::getSquare).collect(Collectors.toSet());

        //throw new RuntimeException("King이 죽었습니다");
    }
}
