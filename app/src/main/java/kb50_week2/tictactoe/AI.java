package kb50_week2.tictactoe;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

/**
 * Created by Bram on 5/4/2015.
 */
public class AI {

    private PlayField playField;

    public AI(PlayField playField) {
        this.playField = playField;
    }

    public void determineNextMove() {
        if (this.playField.isFinished()) {
            return;
        }
        List<Square> squares = this.playField.getSquares();
        List<Square> openSquares = new ArrayList<>();

        for (Square square : squares) {
            if (square.canEdit()) openSquares.add(square);
        }
        Square square = openSquares.get((new Random()).nextInt(openSquares.size()));

        this.playField.setSquareValue(square.getId(), "O");
    }
}
