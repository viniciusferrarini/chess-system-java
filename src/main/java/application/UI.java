package application;

import chess.ChessMatch;
import chess.ChessPiece;
import chess.ChessPosition;
import chess.Color;
import lombok.AccessLevel;
import lombok.AllArgsConstructor;

import java.util.Arrays;
import java.util.InputMismatchException;
import java.util.List;
import java.util.Scanner;

import static java.lang.System.out;

@AllArgsConstructor(access = AccessLevel.PRIVATE)
public class UI {

    public static final String ANSI_RESET = "\u001B[0m";
    public static final String ANSI_BLACK = "\u001B[30m";
    public static final String ANSI_RED = "\u001B[31m";
    public static final String ANSI_GREEN = "\u001B[32m";
    public static final String ANSI_YELLOW = "\u001B[33m";
    public static final String ANSI_BLUE = "\u001B[34m";
    public static final String ANSI_PURPLE = "\u001B[35m";
    public static final String ANSI_CYAN = "\u001B[36m";
    public static final String ANSI_WHITE = "\u001B[37m";

    public static final String ANSI_BLACK_BACKGROUND = "\u001B[40m";
    public static final String ANSI_RED_BACKGROUND = "\u001B[41m";
    public static final String ANSI_GREEN_BACKGROUND = "\u001B[42m";
    public static final String ANSI_YELLOW_BACKGROUND = "\u001B[43m";
    public static final String ANSI_BLUE_BACKGROUND = "\u001B[44m";
    public static final String ANSI_PURPLE_BACKGROUND = "\u001B[45m";
    public static final String ANSI_CYAN_BACKGROUND = "\u001B[46m";
    public static final String ANSI_WHITE_BACKGROUND = "\u001B[47m";

    public static void clearScreen() {
        out.print("\033[H\033[2J");
        out.flush();
    }

    public static ChessPosition readChessPosition(Scanner sc) {
        try {
            final var s = sc.nextLine();
            final var column = s.charAt(0);
            final var row = Integer.parseInt(s.substring(1));
            return new ChessPosition(column, row);
        } catch (RuntimeException e) {
            throw new InputMismatchException("Error reading ChessPosition. Valid values are from a1 to h8");
        }
    }

    public static void printMatch(final ChessMatch chessMatch, List<ChessPiece> captured) {
        printBoard(chessMatch.getPieces());
        out.println();
        printCapturedPieces(captured);
        out.println();
        out.println("Turn : " + chessMatch.getTurn());
        if (!chessMatch.isCheckMate()) {
            out.println("Waiting player: " + chessMatch.getCurrentPlayer());
            if (chessMatch.isCheck()) {
                out.println("CHECK!");
            }
        }
        else {
            out.println("CHECKMATE!");
            out.println("Winner: " + chessMatch.getCurrentPlayer());
        }
    }

    public static void printBoard(final ChessPiece[][] pieces) {
        for (int i = 0; i < pieces.length; i++) {
            out.print((8 - i) + " ");
            for (int j = 0; j < pieces.length; j++) {
                printPiece(pieces[i][j], false);
            }
            out.println();
        }
        out.println("  a b c d e f g h");
    }

    public static void printBoard(ChessPiece[][] pieces, boolean[][] possibleMoves) {
        for (int i = 0; i < pieces.length; i++) {
            out.print((8 - i) + " ");
            for (int j = 0; j < pieces.length; j++) {
                printPiece(pieces[i][j], possibleMoves[i][j]);
            }
            out.println();
        }
        out.println("  a b c d e f j h");
    }

    private static void printPiece(final ChessPiece piece, final boolean background) {
        if (background) {
            out.print(ANSI_BLUE_BACKGROUND);
        }
        if (piece == null) {
            out.print("-" + ANSI_RESET);
        } else {
            if (piece.getColor().equals(Color.WHITE)) {
                out.print(ANSI_WHITE + piece + ANSI_RESET);
            } else {
                out.print(ANSI_YELLOW + piece + ANSI_RESET);
            }
        }
        out.print(" ");
    }

    public static void printCapturedPieces(final List<ChessPiece> captured) {
        var white = captured.stream().filter(chessPiece -> chessPiece.getColor().equals(Color.WHITE));
        var black = captured.stream().filter(chessPiece -> chessPiece.getColor().equals(Color.BLACK));
        out.println("Captured pieces: ");
        out.print("White: ");
        out.print(ANSI_WHITE);
        out.println(Arrays.toString(white.toArray()));
        out.print(ANSI_RESET);
        out.print("Black: ");
        out.print(ANSI_YELLOW);
        out.println(Arrays.toString(black.toArray()));
        out.print(ANSI_RESET);
    }

}