package chess.domain.board;

import chess.domain.piece.King;
import chess.domain.piece.Piece;

import java.util.HashSet;
import java.util.Map;
import java.util.Optional;
import java.util.Set;
import java.util.stream.Collectors;

public class DefaultPlayer implements Player {
    private Map<Square, Piece> pieces;

    public DefaultPlayer(Map<Square, Piece> pieces) {
        this.pieces = pieces;
    }
    
    @Override
    public int getPiecesCount() {
        return pieces.size();
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
    }

    @Override
    public void move(Square source, Square target) {
        pieces.put(target, pieces.get(source));
        pieces.remove(source);
    }

    @Override
    public Piece remove(Square target) {
        return pieces.remove(target);
    }

    @Override
    public double score() {
        return pieces.entrySet().stream()
                .map(Map.Entry::getValue)
                .mapToDouble(Piece::getScore)
                .sum();
    }
}
