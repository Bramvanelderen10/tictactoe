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

    private static final int[][][] WIN_CONDITIONS = {
            //Rows
            {{ 0, 0 }, { 0, 1 }, { 0, 2 }},
            {{ 1, 0 }, { 1, 1 }, { 1, 2 }},
            {{ 2, 0 }, { 2, 1 }, { 2, 2 }},

            //Columns
            {{ 0, 0 }, { 1, 0 }, { 2, 0 }},
            {{ 0, 1 }, { 1, 1 }, { 2, 1 }},
            {{ 0, 2 }, { 1, 2 }, { 2, 2 }},

            //Diagonals
            {{ 0, 0 }, { 1, 1 }, { 2, 2 }},
            {{ 2, 0 }, { 1, 1 }, { 0, 2 }}
    };

    public PlayField() {
        for (int i = 0; i < 9; i++) {
            squares.add(new Square(i));
        }
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

    public void setSquareValue(int id, String value) {
        for (Square temp_square : this.squares) {
            if (temp_square.getId() == id) {
                temp_square.setValue(value);
            }
        }
    }

    public List<Square> getSquares() {
        return squares;
    }

    public boolean isFinished() {
        boolean result = true;

        for (Square square : this.squares) {
            if (square.canEdit()) {
                result = false;
            }
        }

        return result;
    }

    public boolean checkWinningConditions() {
        Square[] squareArray = null;
        this.squares.toArray(squareArray);

        return true;
    }
}
