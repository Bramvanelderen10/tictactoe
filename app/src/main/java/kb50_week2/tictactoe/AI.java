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


        Square win = this.finisherMove("O");
        if (win != null) {

            this.playField.setSquareValue(win.getId(), "O");

            return;
        }


        Square blockSquare = this.finisherMove("X");
        if (blockSquare != null) {
            this.playField.setSquareValue(blockSquare.getId(), "O");

            return;
        }

        //Fallback stupid AI
        List<Square> openSquares = new ArrayList<>();

        for (Square square : squares) {
            if (square.canEdit()) openSquares.add(square);
        }
        Square square = openSquares.get((new Random()).nextInt(openSquares.size()));

        this.playField.setSquareValue(square.getId(), "O");
    }

    public Square finisherMove(String value) {
        Square result = null;

        List<Square> squares = this.playField.getSquares();
        for (int [] i : PlayField.WIN_CONDITIONS) {

            List<Square> opponentSquares = new ArrayList<>();
            List<Square> emptySquares = new ArrayList<>();
            for (int x : i) {

                if (squares.get(x).getValue().equals(value)) {
                    opponentSquares.add(squares.get(x));
                } else if(squares.get(x).canEdit()) {
                    emptySquares.add(squares.get(x));
                }
            }
            if (opponentSquares.size() == 2 && emptySquares.size() ==1 ) {
                result = emptySquares.get(0);
            }
        }

        return result;
    }

}
