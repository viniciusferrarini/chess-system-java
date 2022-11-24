package application;

import chess.ChessMatch;

import java.util.Scanner;

public class Program {

    public static void main(String[] args) {
        final var sc = new Scanner(System.in);
        final var chessMatch = new ChessMatch();

        while (true) {
            UI.printBoard(chessMatch.getPieces());
            System.out.println();
            System.out.println("Source: ");
            final var source = UI.readChessPosition(sc);

            System.out.println();
            System.out.println("Target: ");
            final var target = UI.readChessPosition(sc);

            final var capturedPiece = chessMatch.performChessMove(source, target);
        }
    }

}
