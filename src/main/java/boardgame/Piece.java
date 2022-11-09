package boardgame;

import lombok.AccessLevel;
import lombok.AllArgsConstructor;
import lombok.Getter;


@AllArgsConstructor(access = AccessLevel.PRIVATE)
public class Piece {

    protected final Position position;

    @Getter(AccessLevel.PROTECTED)
    private final Board board;

    public Piece(final Board board){
        this.position = null;
        this.board = board;
    }

}
