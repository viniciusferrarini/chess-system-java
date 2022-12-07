package chess.pieces;

import boardgame.Board;
import boardgame.Position;
import chess.ChessMatch;
import chess.ChessPiece;
import chess.Color;

import static chess.Color.WHITE;

public class Pawn extends ChessPiece {

    private final ChessMatch chessMatch;

    public Pawn(final Board board, final Color color, final ChessMatch chessMatch) {
        super(board, color);
        this.chessMatch = chessMatch;
    }

    @Override
    public boolean[][] possibleMoves() {
        final var moves = new boolean[getBoard().getRows()][getBoard().getColumns()];
        final var p = new Position(0, 0);
        if (getColor().equals(WHITE)) {
            p.setValues(position.getRow() - 1, position.getColumn());
            if (positionExistsAndIsEmpty(p)) {
                moves[p.getRow()][p.getColumn()] = true;
            }
            p.setValues(position.getRow() - 2, position.getColumn());
            final var firstPosition = new Position(position.getRow() - 1, position.getColumn());
            if (positionExistsAndIsEmpty(p) && positionExistsAndIsEmpty(firstPosition) && getMoveCount() == 0) {
                moves[p.getRow()][p.getColumn()] = true;
            }
            p.setValues(position.getRow() - 1, position.getColumn() - 1);
            if (getBoard().positionExists(p) && isThereOpponentPiece(p)) {
                moves[p.getRow()][p.getColumn()] = true;
            }
            p.setValues(position.getRow() - 1, position.getColumn() + 1);
            if (getBoard().positionExists(p) && isThereOpponentPiece(p)) {
                moves[p.getRow()][p.getColumn()] = true;
            }
            // #specialmove en passant white
            if (position.getRow() == 3) {
                Position left = new Position(position.getRow(), position.getColumn() - 1);
                if (getBoard().positionExists(left) && isThereOpponentPiece(left) && getBoard().piece(left) == chessMatch.getEnPassantVulnerable()) {
                    moves[left.getRow() - 1][left.getColumn()] = true;
                }
                Position right = new Position(position.getRow(), position.getColumn() + 1);
                if (getBoard().positionExists(right) && isThereOpponentPiece(right) && getBoard().piece(right) == chessMatch.getEnPassantVulnerable()) {
                    moves[right.getRow() - 1][right.getColumn()] = true;
                }
            }
        } else {
            p.setValues(position.getRow() + 1, position.getColumn());
            if (positionExistsAndIsEmpty(p)) {
                moves[p.getRow()][p.getColumn()] = true;
            }
            p.setValues(position.getRow() + 2, position.getColumn());
            final var firstPosition = new Position(position.getRow() + 1, position.getColumn());
            if (positionExistsAndIsEmpty(p) && positionExistsAndIsEmpty(firstPosition) && getMoveCount() == 0) {
                moves[p.getRow()][p.getColumn()] = true;
            }
            p.setValues(position.getRow() + 1, position.getColumn() - 1);
            if (getBoard().positionExists(p) && isThereOpponentPiece(p)) {
                moves[p.getRow()][p.getColumn()] = true;
            }
            p.setValues(position.getRow() + 1, position.getColumn() + 1);
            if (getBoard().positionExists(p) && isThereOpponentPiece(p)) {
                moves[p.getRow()][p.getColumn()] = true;
            }
            // #specialmove en passant black
            if (position.getRow() == 4) {
                Position left = new Position(position.getRow(), position.getColumn() - 1);
                if (getBoard().positionExists(left) && isThereOpponentPiece(left) && getBoard().piece(left) == chessMatch.getEnPassantVulnerable()) {
                    moves[left.getRow() + 1][left.getColumn()] = true;
                }
                Position right = new Position(position.getRow(), position.getColumn() + 1);
                if (getBoard().positionExists(right) && isThereOpponentPiece(right) && getBoard().piece(right) == chessMatch.getEnPassantVulnerable()) {
                    moves[right.getRow() + 1][right.getColumn()] = true;
                }
            }
        }
        return moves;
    }

    private boolean positionExistsAndIsEmpty(final Position position) {
        return getBoard().positionExists(position) && !getBoard().thereIsAPiece(position);
    }

    @Override
    public String toString() {
        return "P";
    }
}
