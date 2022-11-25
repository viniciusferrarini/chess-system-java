package boardgame;

import lombok.AccessLevel;
import lombok.AllArgsConstructor;
import lombok.Getter;


@AllArgsConstructor(access = AccessLevel.PRIVATE)
public abstract class Piece {

    protected Position position;

    @Getter(AccessLevel.PROTECTED)
    private final Board board;

    protected Piece(final Board board){
        this.position = null;
        this.board = board;
    }

    public abstract boolean[][] possibleMoves();

    //Hook method (Template method)
    public boolean possibleMove(final Position position) {
        return possibleMoves()[position.getRow()][position.getColumn()];
    }

    public boolean isThereAnyPossibleMove() {
        final var possibleMoves = possibleMoves();
        for (int i = 0; i < possibleMoves.length; i++) {
            for (int j = 0; j < possibleMoves.length; j++) {
                if (possibleMoves[i][j]) {
                    return true;
                }
            }
        }
        return false;
    }
}
