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

    public Piece piece(final int row, final int column) {
        return this.pieces[row][column];
    }

    public Piece piece(final Position position) {
        return this.pieces[position.getRow()][position.getColumn()];
    }

    public void placePiece(final Piece piece, final Position position) {
        this.pieces[position.getRow()][position.getColumn()] = piece;
        piece.position = position;
    }

}
