package boardgame;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;

@Getter @Setter
@AllArgsConstructor
public class Position {

    private int row;
    private int column;

    @Override
    public String toString() {
        return row + " - " + column;
    }

    public void setValues(final int row, final int column) {
        this.row = row;
        this.column = column;
    }
}
