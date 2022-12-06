package chess;

import boardgame.Board;
import boardgame.Piece;
import boardgame.Position;
import chess.pieces.King;
import chess.pieces.Pawn;
import chess.pieces.Rook;
import lombok.Getter;

import java.util.ArrayList;
import java.util.List;

import static chess.Color.BLACK;
import static chess.Color.WHITE;

public class ChessMatch {

    private final Board board;

    @Getter
    private int turn;
    @Getter
    private Color currentPlayer;
    @Getter
    private boolean check;
    @Getter
    private boolean checkMate;
    private final List<Piece> piecesOnTheBoard = new ArrayList<>();
    private final List<Piece> capturedPieces = new ArrayList<>();

    public ChessMatch() {
        this.board = new Board(8, 8);
        this.turn = 1;
        this.currentPlayer = WHITE;
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

    public boolean[][] possibleMoves(final ChessPosition sourcePosition) {
        final var position = sourcePosition.toPosition();
        validateSourcePosition(position);
        return board.piece(position).possibleMoves();
    }

    public ChessPiece performChessMove(final ChessPosition sourcePosition, final ChessPosition targetPosition) {
        final var source = sourcePosition.toPosition();
        final var target = targetPosition.toPosition();
        validateSourcePosition(source);
        validateTargetPosition(source, target);
        final var capturedPiece = makeMove(source, target);

        if (testCheck(currentPlayer)) {
            undoMove(source, target, capturedPiece);
            throw new ChessException("You can't put yourself in check");
        }

        check = testCheck(opponent(currentPlayer));

        if (testCheckMate(opponent(currentPlayer))) {
            checkMate = true;
        } else {
            nextTurn();
        }

        return (ChessPiece) capturedPiece;
    }

    private Piece makeMove(final Position source, final Position target) {
        final var removedPiece = (ChessPiece) board.removePiece(source);
        removedPiece.increaseMoveCount();
        final var capturedPiece = board.removePiece(target);
        board.placePiece(removedPiece, target);
        if (capturedPiece != null) {
            piecesOnTheBoard.remove(capturedPiece);
            capturedPieces.add(capturedPiece);
        }
        return capturedPiece;
    }

    private void undoMove(final Position source, final Position target, final Piece capturedPiece) {
        final var removedPiece = (ChessPiece) board.removePiece(source);
        removedPiece.decreaseMoveCount();
        board.placePiece(removedPiece, source);
        if (capturedPiece != null) {
            board.placePiece(capturedPiece, target);
            capturedPieces.remove(capturedPiece);
            piecesOnTheBoard.add(capturedPiece);
        }
    }

    private void validateSourcePosition(final Position source) {
        if (!board.thereIsAPiece(source)) {
            throw new ChessException("There is no piece on source position");
        }
        if (currentPlayer != ((ChessPiece) board.piece(source)).getColor()) {
            throw new ChessException("The chosen piece is not yours");
        }
        if (!board.piece(source).isThereAnyPossibleMove()) {
            throw new ChessException("There is no possible moves for the chosen piece");
        }
    }

    private void validateTargetPosition(final Position source, final Position target) {
        if (!board.piece(source).possibleMove(target)) {
            throw new ChessException("The chosen piece can't move to target position");
        }
    }

    private void nextTurn() {
        turn++;
        currentPlayer = currentPlayer == WHITE ? BLACK : WHITE;
    }

    private Color opponent(final Color color) {
        return color == WHITE ? BLACK : WHITE;
    }

    private ChessPiece king(final Color color) {
        return (ChessPiece) piecesOnTheBoard.stream()
                .filter(piece -> ((ChessPiece) piece).getColor().equals(color) && piece instanceof King)
                .findFirst()
                .orElseThrow(() -> new IllegalStateException("There is no " + color + " king on the board"));
    }

    private boolean testCheck(final Color color) {
        final var kingPosition = king(color).getChessPosition().toPosition();
        final var opponentPieces = piecesOnTheBoard.stream()
                .filter(piece -> ((ChessPiece) piece).getColor().equals(opponent(color)))
                .toList();
        for (Piece p : opponentPieces) {
            final var mat = p.possibleMoves();
            if (mat[kingPosition.getRow()][kingPosition.getColumn()]) {
                return true;
            }
        }
        return false;
    }

    private boolean testCheckMate(final Color color) {
        if (!testCheck(color)) return false;
        final var list = piecesOnTheBoard.stream().filter(piece -> ((ChessPiece) piece).getColor().equals(color)).toList();
        for (final Piece piece : list) {
            final var mat = piece.possibleMoves();
            for (int i = 0; i < board.getRows(); i++) {
                for (int j = 0; j < board.getColumns(); j++) {
                    if (mat[i][j]) {
                        final var source = ((ChessPiece) piece).getChessPosition().toPosition();
                        final var target = new Position(i, j);
                        final var capturedPiece = makeMove(source, target);
                        final var testCheck = testCheck(color);
                        undoMove(source, target, capturedPiece);
                        if (!testCheck) return false;
                    }
                }
            }
        }
        return true;

    }

    private void placeNewPiece(final char column, final int row, final ChessPiece piece) {
        board.placePiece(piece, new ChessPosition(column, row).toPosition());
        piecesOnTheBoard.add(piece);
    }

    private void initialSetup() {
        placeNewPiece('a', 1, new Rook(board, WHITE));
        placeNewPiece('e', 1, new King(board, WHITE));
        placeNewPiece('h', 1, new Rook(board, WHITE));
        placeNewPiece('a', 2, new Pawn(board, WHITE));
        placeNewPiece('b', 2, new Pawn(board, WHITE));
        placeNewPiece('c', 2, new Pawn(board, WHITE));
        placeNewPiece('d', 2, new Pawn(board, WHITE));
        placeNewPiece('e', 2, new Pawn(board, WHITE));
        placeNewPiece('f', 2, new Pawn(board, WHITE));
        placeNewPiece('g', 2, new Pawn(board, WHITE));
        placeNewPiece('h', 2, new Pawn(board, WHITE));

        placeNewPiece('a', 8, new Rook(board, WHITE));
        placeNewPiece('e', 8, new King(board, WHITE));
        placeNewPiece('h', 8, new Rook(board, WHITE));
        placeNewPiece('a', 7, new Pawn(board, WHITE));
        placeNewPiece('b', 7, new Pawn(board, WHITE));
        placeNewPiece('c', 7, new Pawn(board, WHITE));
        placeNewPiece('d', 7, new Pawn(board, WHITE));
        placeNewPiece('e', 7, new Pawn(board, WHITE));
        placeNewPiece('f', 7, new Pawn(board, WHITE));
        placeNewPiece('g', 7, new Pawn(board, WHITE));
        placeNewPiece('h', 7, new Pawn(board, WHITE));

    }

}
