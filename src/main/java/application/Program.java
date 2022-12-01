package application;

import chess.ChessException;
import chess.ChessMatch;
import chess.ChessPiece;

import java.util.ArrayList;
import java.util.InputMismatchException;
import java.util.Scanner;

import static java.lang.System.out;

public class Program {

    public static void main(String[] args) {
        final var sc = new Scanner(System.in);
        final var chessMatch = new ChessMatch();
        var captured = new ArrayList<ChessPiece>();

        while (true) {
            try {
                UI.clearScreen();
                UI.printMatch(chessMatch, captured);
                out.println();
                out.println("Source: ");
                final var source = UI.readChessPosition(sc);
                final var possibleMoves= chessMatch.possibleMoves(source);
                UI.clearScreen();
                UI.printBoard(chessMatch.getPieces(), possibleMoves);

                out.println();
                out.println("Target: ");
                final var target = UI.readChessPosition(sc);

                final var capturedPiece = chessMatch.performChessMove(source, target);

                if (capturedPiece != null) {
                    captured.add(capturedPiece);
                }
            } catch (ChessException | InputMismatchException e) {
                out.println(e.getMessage());
                sc.nextLine();
            }
        }
    }
}
