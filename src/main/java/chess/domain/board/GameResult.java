package chess.domain.board;

import chess.domain.piece.PieceColor;

public class GameResult {
    private final double blackScore;
    private final double whiteScore;
    private final PieceColor loser;

    public GameResult(double blackScore, double whiteScore) {
        this.blackScore = blackScore;
        this.whiteScore = whiteScore;
        this.loser = blackScore > whiteScore ? PieceColor.WHITE : PieceColor.BLACK;
    }

    public double getBlackScore() {
        return blackScore;
    }

    public double getWhiteScore() {
        return whiteScore;
    }

    public PieceColor getLoser() {
        return loser;
    }
}
