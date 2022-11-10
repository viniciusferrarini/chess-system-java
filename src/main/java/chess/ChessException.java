package chess;

import java.io.Serial;

public class ChessException extends RuntimeException {

    @Serial
    public static final long serialVersionUID = 1L;

    public ChessException(final String message) {
        super(message);
    }
}
