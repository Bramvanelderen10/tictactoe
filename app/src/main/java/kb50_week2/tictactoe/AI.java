package kb50_week2.tictactoe;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

/**
 * Created by Bram on 5/4/2015.
 */
public class AI {


    private Game game;

    public AI(Game game) {
        this.game = game;
    }

    public void determineNextMove() {
        if (this.game.isFinished()) {
            return;
        }
        List<Square> squares = this.game.getSquares();


        Square win = this.finisherMove("O");
        if (win != null) {

            this.game.setSquareValue(win.getId(), "O");

            return;
        }


        Square blockSquare = this.finisherMove("X");
        if (blockSquare != null) {
            this.game.setSquareValue(blockSquare.getId(), "O");

            return;
        }

        //Fallback stupid AI
        List<Square> openSquares = new ArrayList<>();

        for (Square square : squares) {
            if (square.canEdit()) openSquares.add(square);
        }
        Square square = openSquares.get((new Random()).nextInt(openSquares.size()));

        this.game.setSquareValue(square.getId(), "O");
    }

    public Square finisherMove(String value) {
        Square result = null;

        List<Square> squares = this.game.getSquares();
        for (int [] i : Game.WIN_CONDITIONS) {

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
