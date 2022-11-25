package application;

import chess.ChessException;
import chess.ChessMatch;

import java.util.InputMismatchException;
import java.util.Scanner;

import static java.lang.System.out;

public class Program {

    public static void main(String[] args) {
        final var sc = new Scanner(System.in);
        final var chessMatch = new ChessMatch();

        while (true) {
            try {
                UI.clearScreen();
                UI.printBoard(chessMatch.getPieces());
                out.println();
                out.println("Source: ");
                final var source = UI.readChessPosition(sc);

                out.println();
                out.println("Target: ");
                final var target = UI.readChessPosition(sc);

                final var capturedPiece = chessMatch.performChessMove(source, target);
            } catch (ChessException | InputMismatchException e) {
                out.println(e.getMessage());
                sc.nextLine();
            }
        }
    }
}
