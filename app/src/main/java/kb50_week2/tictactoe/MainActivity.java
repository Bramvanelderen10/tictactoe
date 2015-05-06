package kb50_week2.tictactoe;

import android.app.FragmentManager;
import android.app.FragmentTransaction;
import android.content.res.Configuration;
import android.support.v7.app.ActionBarActivity;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import java.util.ArrayList;


public class MainActivity extends ActionBarActivity {
    private Game game;
    private GameFragment gameFragment;
    private AI ai;
    private ArrayList<Button> buttons = new ArrayList<>();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_portrait);

        buttons.add((Button)findViewById(R.id.button0));
        buttons.add((Button)findViewById(R.id.button1));
        buttons.add((Button)findViewById(R.id.button2));
        buttons.add((Button)findViewById(R.id.button3));
        buttons.add((Button)findViewById(R.id.button4));
        buttons.add((Button)findViewById(R.id.button5));
        buttons.add((Button)findViewById(R.id.button6));
        buttons.add((Button)findViewById(R.id.button7));
        buttons.add((Button)findViewById(R.id.button8));

        Button resetButton = (Button)findViewById(R.id.resetButton);
        resetButton.setVisibility(View.GONE);

        this.gameFragment = null;
        FragmentManager fm = getFragmentManager();

        this.gameFragment = (GameFragment)fm.findFragmentByTag("gameFragment");
        if (this.gameFragment == null) {
            this.game = new Game();
            this.gameFragment = new GameFragment();
            this.gameFragment.setGame(game);

            FragmentTransaction ft = fm.beginTransaction();
            ft.add(this.gameFragment, "gameFragment");
            ft.commit();
        } else {
            this.game = this.gameFragment.getGame();
        }
        this.game.updatePlayfield(this.buttons);
        this.ai = new AI(game);
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_main, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();

        //noinspection SimplifiableIfStatement
        if (id == R.id.action_settings) {
            return true;
        }

        return super.onOptionsItemSelected(item);
    }

    public void onClick(View v) {
        int id = -1;
        switch (v.getId()) {
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

        if (this.game.setSquareValue(id, "X")) {
            this.ai.determineNextMove();
        }
        this.game.updatePlayfield(this.buttons);
        if (this.game.isFinished()) {
            Button resetButton = (Button)findViewById(R.id.resetButton);
            resetButton.setVisibility(View.VISIBLE);

            TextView textView = (TextView) findViewById(R.id.decision);
            textView.setVisibility(View.GONE);

            Game.GameStates winner = this.game.getGamestate();
            switch (winner) {
                case AI_WON:
                    textView.setText(R.string.win_machine);
                    textView.setVisibility(View.VISIBLE);
                    break;
                case PLAYER_WON:
                    textView.setText(R.string.win_player);
                    textView.setVisibility(View.VISIBLE);
            }
        }
    }

    public void reset(View v) {
        this.game.reset(this.buttons);Button resetButton = (Button)findViewById(R.id.resetButton);
        resetButton.setVisibility(View.GONE);

        TextView textView = (TextView) findViewById(R.id.decision);
        textView.setVisibility(View.GONE);
    }
}
