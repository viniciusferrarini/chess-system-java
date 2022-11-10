package chess;

import boardgame.Board;
import chess.pieces.King;
import chess.pieces.Rook;

public class ChessMatch {

    private final Board board;

    public ChessMatch() {
        this.board = new Board(8, 8);
        this.initialSetup();
    }

    public ChessPiece[][] getPieces() {
        final var mat = new ChessPiece[this.board.getRows()][this.board.getColumns()];
        for (int i = 0; i < this.board.getRows(); i++) {
            for (int j = 0; j < this.board.getColumns(); j++) {
                mat[i][j] = (ChessPiece) board.piece(i, j);
            }
        }
        return mat;
    }

    private void placeNewPiece(final char column, final int row, final ChessPiece piece) {
        board.placePiece(piece, new ChessPosition(column, row).toPosition());
    }

    private void initialSetup() {
        placeNewPiece('b', 6, new Rook(board, Color.BLACK));
        placeNewPiece('e', 8, new King(board, Color.WHITE));
        placeNewPiece('e', 1, new King(board, Color.BLACK));
    }

}
