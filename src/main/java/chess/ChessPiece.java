package chess;

import boardgame.Board;
import boardgame.Piece;
import lombok.Getter;

public abstract class ChessPiece extends Piece {

    @Getter
    private final Color color;

    public ChessPiece(final Board board, final Color color) {
        super(board);
        this.color = color;
    }

}
