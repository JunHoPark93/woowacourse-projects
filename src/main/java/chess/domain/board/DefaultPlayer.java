package chess.domain.board;

import chess.domain.piece.King;
import chess.domain.piece.Pawn;
import chess.domain.piece.Piece;

import java.util.*;
import java.util.stream.Collectors;

public class DefaultPlayer implements Player {
    private final Map<Square, Piece> pieces;

    public DefaultPlayer(Map<Square, Piece> pieces) {
        this.pieces = pieces;
    }
    
    @Override
    public int pieceCount() {
        return pieces.size();
    }

    @Override
    public Optional<Piece> piece(Square source) {
        return Optional.ofNullable(pieces.get(source));
    }

    @Override
    public boolean contains(Vector vector) {
        return vector.containsSquare(pieces);
    }

    @Override
    public Set<Vector> kingPath() {
        Set<Vector> vectors = new HashSet<>();
        for (Map.Entry<Square, Piece> entry : pieces.entrySet()) {
            if (entry.getValue() instanceof King) {
                vectors = entry.getValue().movableList(entry.getKey());
            }
        }
        return vectors;
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
        double sum = pieces.entrySet().stream()
                .map(Map.Entry::getValue)
                .mapToDouble(Piece::getScore)
                .sum();

        sum = checkPawnConstraint(sum);

        return sum;
    }

    private double checkPawnConstraint(double sum) {
        List<Square> pawns = pieces.entrySet().stream()
                .filter(pieces -> pieces.getValue() instanceof Pawn)
                .map(Map.Entry::getKey)
                .collect(Collectors.toList());

        int count = 0;
        for (int i = 0; i < pawns.size(); i++) {
            count = checkVerticalLine(pawns, count, i);
        }

        sum -= (count / 2) * 0.5;
        return sum;
    }

    private int checkVerticalLine(List<Square> pawns, int count, int i) {
        for (int j = 0; j < pawns.size(); j++) {
            if (i == j) {
                continue;
            }
            if (pawns.get(i).isVertical(pawns.get(j))) {
                count++;
            }
        }
        return count;
    }

    @Override
    public String toString() {
        return "{" +
                "pieces=" + pieces +
                '}';
    }
}
