package chess;

import boardgame.BoardException;

public class ChessException extends BoardException {

    public static final long serialVersionUID = 1L;

    public ChessException(final String message) {
        super(message);
    }
}
