package chess;

import boardgame.Board;
import boardgame.Piece;
import boardgame.Position;
import lombok.Getter;

@Getter
public abstract class ChessPiece extends Piece {

    private final Color color;
    private int moveCount;

    public ChessPiece(final Board board, final Color color) {
        super(board);
        this.color = color;
    }

    protected boolean isThereOpponentPiece(final Position position) {
        final var piece = (ChessPiece) getBoard().piece(position);
        return piece != null && piece.getColor() != color;
    }

    public void increaseMoveCount() {
        this.moveCount++;
    }

    public void decreaseMoveCount() {
        this.moveCount--;
    }

    public ChessPosition getChessPosition() {
        return ChessPosition.fromPosition(this.position);
    }

}
