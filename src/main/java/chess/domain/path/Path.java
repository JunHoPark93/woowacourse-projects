package chess.domain.path;

import chess.domain.board.Square;
import chess.domain.board.Vector;

import java.util.Set;

public interface Path {
    Set<Vector> movableList(Square source);
}
