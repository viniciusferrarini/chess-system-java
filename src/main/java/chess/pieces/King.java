package chess.pieces;

import boardgame.Board;
import boardgame.Position;
import chess.ChessPiece;
import chess.Color;

public class King extends ChessPiece {

    public King(final Board board, final Color color) {
        super(board, color);
    }

    @Override
    public String toString() {
        return "K";
    }

    @Override
    public boolean[][] possibleMoves() {
        boolean[][] mat = new boolean[getBoard().getRows()][getBoard().getColumns()];

        Position piece = new Position(0, 0);

        // above
        piece.setValues(position.getRow() - 1, position.getColumn());
        if (getBoard().positionExists(piece) && canMove(piece)) {
            mat[piece.getRow()][piece.getColumn()] = true;
        }

        // below
        piece.setValues(position.getRow() + 1, position.getColumn());
        if (getBoard().positionExists(piece) && canMove(piece)) {
            mat[piece.getRow()][piece.getColumn()] = true;
        }

        // left
        piece.setValues(position.getRow(), position.getColumn() - 1);
        if (getBoard().positionExists(piece) && canMove(piece)) {
            mat[piece.getRow()][piece.getColumn()] = true;
        }

        // right
        piece.setValues(position.getRow(), position.getColumn() + 1);
        if (getBoard().positionExists(piece) && canMove(piece)) {
            mat[piece.getRow()][piece.getColumn()] = true;
        }

        // nw
        piece.setValues(position.getRow() - 1, position.getColumn() - 1);
        if (getBoard().positionExists(piece) && canMove(piece)) {
            mat[piece.getRow()][piece.getColumn()] = true;
        }

        // ne
        piece.setValues(position.getRow() - 1, position.getColumn() + 1);
        if (getBoard().positionExists(piece) && canMove(piece)) {
            mat[piece.getRow()][piece.getColumn()] = true;
        }

        // sw
        piece.setValues(position.getRow() + 1, position.getColumn() - 1);
        if (getBoard().positionExists(piece) && canMove(piece)) {
            mat[piece.getRow()][piece.getColumn()] = true;
        }

        // se
        piece.setValues(position.getRow() + 1, position.getColumn() + 1);
        if (getBoard().positionExists(piece) && canMove(piece)) {
            mat[piece.getRow()][piece.getColumn()] = true;
        }

        return mat;
    }

    private boolean canMove(final Position position) {
        ChessPiece piece = (ChessPiece) getBoard().piece(position);
        return piece == null || piece.getColor() != getColor();
    }
}
