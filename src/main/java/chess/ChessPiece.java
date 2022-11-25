package chess;

import boardgame.Board;
import boardgame.Piece;
import boardgame.Position;
import lombok.Getter;

public abstract class ChessPiece extends Piece {

    @Getter
    private final Color color;

    public ChessPiece(final Board board, final Color color) {
        super(board);
        this.color = color;
    }

    protected boolean isThereOpponentPiece(final Position position) {
        final var piece = (ChessPiece) getBoard().piece(position);
        return piece != null && piece.getColor() != color;
    }

}
