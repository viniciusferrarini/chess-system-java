package boardgame;

import lombok.Getter;

public class Board {

    @Getter
    private final int rows;
    @Getter
    private final int columns;
    private final Piece[][] pieces;

    public Board(final int rows, final int columns) {
        this.rows = rows;
        this.columns = columns;
        this.pieces = new Piece[rows][columns];
    }

}
