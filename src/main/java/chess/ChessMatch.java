package chess;

import boardgame.Board;
import boardgame.Piece;
import boardgame.Position;
import chess.pieces.Rook;

import static chess.Color.BLACK;
import static chess.Color.WHITE;

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

    public ChessPiece performChessMove(final ChessPosition sourcePosition, final ChessPosition targetPosition) {
        final var source = sourcePosition.toPosition();
        final var target = targetPosition.toPosition();
        validateSourcePosition(source);
        final var capturedPiece = makeMove(source, target);
        return (ChessPiece) capturedPiece;
    }

    private Piece makeMove(final Position source, final Position target) {
        final var removedPiece = board.removePiece(source);
        final var capturedPiece = board.removePiece(target);
        board.placePiece(removedPiece, target);
        return capturedPiece;
    }

    private void validateSourcePosition(final Position source) {
        if (!board.thereIsAPiece(source)) {
            throw new ChessException("There is no piece on source position");
        }
    }

    private void placeNewPiece(final char column, final int row, final ChessPiece piece) {
        board.placePiece(piece, new ChessPosition(column, row).toPosition());
    }

    private void initialSetup() {
        placeNewPiece('c', 1, new Rook(board, WHITE));
        placeNewPiece('c', 2, new Rook(board, WHITE));
        placeNewPiece('d', 2, new Rook(board, WHITE));
        placeNewPiece('e', 2, new Rook(board, WHITE));
        placeNewPiece('e', 1, new Rook(board, WHITE));
        placeNewPiece('d', 1, new Rook(board, WHITE));

        placeNewPiece('c', 7, new Rook(board, BLACK));
        placeNewPiece('c', 8, new Rook(board, BLACK));
        placeNewPiece('d', 7, new Rook(board, BLACK));
        placeNewPiece('e', 7, new Rook(board, BLACK));
        placeNewPiece('e', 8, new Rook(board, BLACK));
        placeNewPiece('d', 8, new Rook(board, BLACK));

    }

}
