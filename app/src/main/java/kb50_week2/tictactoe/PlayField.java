package kb50_week2.tictactoe;

import android.widget.Button;

import java.lang.reflect.Array;
import java.util.ArrayList;
import java.util.List;

/**
 * Created by Bram on 5/4/2015.
 */
public class PlayField {
    private List<Square> squares = new ArrayList<>();

    private Game game;

    public static final int[][]WIN_CONDITIONS = {
            //Rows
            {0, 1, 2},
            {3, 4, 5},
            {6, 7, 8},

            //Columns
            {0, 3, 6},
            {1, 4, 7},
            {2, 5, 8},

            //Diagonals
            {0, 4, 8},
            {2, 4, 6}
    };

    public PlayField() {
        for (int i = 0; i < 9; i++) {
            squares.add(new Square(i));
        }
        this.game = new Game();
    }

    public boolean reset(List<Button> buttons) {
        for (Square square : this.squares) {
            square.reset();
        }
        this.updatePlayfield(buttons);

        return true;
    }

    public void updatePlayfield(List<Button> buttons) {
        for (Button button : buttons) {
            int id = -1;
            switch (button.getId()) {
                case R.id.button0:
                    id = 0;
                    break;
                case R.id.button1:
                    id = 1;
                    break;
                case R.id.button2:
                    id = 2;
                    break;
                case R.id.button3:
                    id = 3;
                    break;
                case R.id.button4:
                    id = 4;
                    break;
                case R.id.button5:
                    id = 5;
                    break;
                case R.id.button6:
                    id = 6;
                    break;
                case R.id.button7:
                    id = 7;
                    break;
                case R.id.button8:
                    id = 8;
                    break;
            }

            Square square = null;
            for (Square temp_square : this.squares) {
                if (temp_square.getId() == id) {
                    square = temp_square;
                }
            }

            if (square != null) {
                button.setText(square.getValue());
            }
        }
    }

    public boolean setSquareValue(int id, String value) {
        if (this.isFinished()) return false;
        for (Square temp_square : this.squares) {
            if (temp_square.getId() == id && temp_square.canEdit()) {
                temp_square.setValue(value);

                return true;
            }
        }
        return false;
    }

    public List<Square> getSquares() {
        return squares;
    }

    public boolean isFinished() {
        boolean filled = true;

        for (Square square : this.squares) {
            if (square.canEdit()) {
                filled =  false;
            }
        }
        if (filled) {
            this.game.setGamestate(Game.GameStates.PLAYFIELD_FULL);
            return true;
        }

        boolean result = false;
        for (int [] i : WIN_CONDITIONS) {
            String value = null;
            boolean temp_result = true;
            for (int x : i) {
                if ((value == null || value.equals(this.squares.get(x).getValue())) && !this.squares.get(x).getValue().equals("")) {
                    value = this.squares.get(x).getValue();
                } else {
                    temp_result = false;
                }
            }

            //Game is won
            if (temp_result) {
                if (value.equals("X")) this.game.setGamestate(Game.GameStates.PLAYER_WON);
                if (value.equals("O")) this.game.setGamestate(Game.GameStates.AI_WON);

                result = true;
            }

        }

        return result;
    }

    public Game.GameStates getWinner() {

        return this.game.getGamestate();
    }
}
