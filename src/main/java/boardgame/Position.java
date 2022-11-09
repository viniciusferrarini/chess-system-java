package boardgame;

import lombok.AllArgsConstructor;
import lombok.Getter;

@Getter
@AllArgsConstructor
public class Position {

    private final int row;
    private final int column;

    @Override
    public String toString() {
        return row + " - " + column;
    }
}
