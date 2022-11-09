package application;

import chess.ChessMatch;

public class Program {

    public static void main(String[] args) {
        final var chessMatch = new ChessMatch();
        UI.printBoard(chessMatch.getPieces());
    }

}
