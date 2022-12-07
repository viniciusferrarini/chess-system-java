package chess.pieces;

import boardgame.Board;
import boardgame.Position;
import chess.ChessMatch;
import chess.ChessPiece;
import chess.Color;

public class King extends ChessPiece {

    public King(final Board board, final Color color, final ChessMatch chessMatch) {
        super(board, color);
        this.chessMatch = chessMatch;
    }

    private ChessMatch chessMatch;

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

        // #specialmove castling
        if (getMoveCount() == 0 && !chessMatch.isCheck()) {
            // #specialmove castling king side move
            final var rightRookPosition = new Position(position.getRow(), position.getColumn() + 3);
            if (testRookCastling(rightRookPosition)) {
                final var p1 = new Position(position.getRow(), position.getColumn() + 1);
                final var p2 = new Position(position.getRow(), position.getColumn() + 2);
                if (getBoard().piece(p1) == null && getBoard().piece(p2) == null) {
                    mat[position.getRow()][position.getColumn() + 2] = true;
                }
            }

            // #specialmove castling queen side move
            final var leftRookPosition = new Position(position.getRow(), position.getColumn() - 4);
            if (testRookCastling(leftRookPosition)) {
                final var p1 = new Position(position.getRow(), position.getColumn() - 1);
                final var p2 = new Position(position.getRow(), position.getColumn() - 2);
                final var p3 = new Position(position.getRow(), position.getColumn() - 3);
                if (getBoard().piece(p1) == null && getBoard().piece(p2) == null && getBoard().piece(p3) == null) {
                    mat[position.getRow()][position.getColumn() - 2] = true;
                }
            }
        }

        return mat;
    }

    private boolean canMove(final Position position) {
        ChessPiece piece = (ChessPiece) getBoard().piece(position);
        return piece == null || piece.getColor() != getColor();
    }

    private boolean testRookCastling(final Position position) {
        final var piece = (ChessPiece) getBoard().piece(position);
        return piece != null && piece instanceof Rook && piece.getColor() == getColor() && piece.getMoveCount() == 0;
    }

}