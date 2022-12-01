package chess;

import boardgame.Position;
import lombok.Getter;

public record ChessPosition(@Getter char column, @Getter int row) {

    public ChessPosition {
        if (column < 'a' || column > 'h' || row < 1 || row > 8) {
            throw new ChessException("Error instantiating ChessPosition. Valid values are from a1 to h8");
        }
    }

    protected Position toPosition() {
        return new Position(8 - this.row, this.column - 'a');
    }

    protected static ChessPosition fromPosition(final Position position) {
        return new ChessPosition((char) ('a' + position.getColumn()), 8 - position.getRow());
    }

    @Override
    public String toString() {
        return "" + column + row;
    }
}
