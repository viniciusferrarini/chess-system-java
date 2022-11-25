package chess.pieces;

import boardgame.Board;
import boardgame.Position;
import chess.ChessPiece;
import chess.Color;

public class Rook extends ChessPiece {

    public Rook(final Board board, final Color color) {
        super(board, color);
    }

    @Override
    public String toString() {
        return "R";
    }

    @Override
    public boolean[][] possibleMoves() {
        var possibleMoves = new boolean[getBoard().getRows()][getBoard().getColumns()];

        var auxPos = new Position(0, 0);

        // above
        auxPos.setValues(position.getRow() - 1, position.getColumn());
        while (getBoard().positionExists(auxPos) && !getBoard().thereIsAPiece(auxPos)) {
            possibleMoves[auxPos.getRow()][auxPos.getColumn()] = true;
            auxPos.setRow(auxPos.getRow() - 1);
        }
        if (getBoard().positionExists(auxPos) && isThereOpponentPiece(auxPos)) {
            possibleMoves[auxPos.getRow()][auxPos.getColumn()] = true;
        }

        // left
        auxPos.setValues(position.getRow(), position.getColumn() - 1);
        while (getBoard().positionExists(auxPos) && !getBoard().thereIsAPiece(auxPos)) {
            possibleMoves[auxPos.getRow()][auxPos.getColumn()] = true;
            auxPos.setColumn(auxPos.getColumn() - 1);
        }
        if (getBoard().positionExists(auxPos) && isThereOpponentPiece(auxPos)) {
            possibleMoves[auxPos.getRow()][auxPos.getColumn()] = true;
        }

        // right
        auxPos.setValues(position.getRow(), position.getColumn() + 1);
        while (getBoard().positionExists(auxPos) && !getBoard().thereIsAPiece(auxPos)) {
            possibleMoves[auxPos.getRow()][auxPos.getColumn()] = true;
            auxPos.setColumn(auxPos.getColumn() + 1);
        }
        if (getBoard().positionExists(auxPos) && isThereOpponentPiece(auxPos)) {
            possibleMoves[auxPos.getRow()][auxPos.getColumn()] = true;
        }

        // below
        auxPos.setValues(position.getRow() + 1, position.getColumn());
        while (getBoard().positionExists(auxPos) && !getBoard().thereIsAPiece(auxPos)) {
            possibleMoves[auxPos.getRow()][auxPos.getColumn()] = true;
            auxPos.setRow(auxPos.getRow() + 1);
        }
        if (getBoard().positionExists(auxPos) && isThereOpponentPiece(auxPos)) {
            possibleMoves[auxPos.getRow()][auxPos.getColumn()] = true;
        }

        return possibleMoves;
    }
}
