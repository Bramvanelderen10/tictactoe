package kb50_week2.tictactoe;

import android.app.Fragment;
import android.app.FragmentManager;
import android.os.Bundle;

/**
 * Created by Bram on 5/6/2015.
 */
public class GameFragment extends Fragment {
    private Game game;

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        setRetainInstance(true);
    }

    public void setGame(Game game) {
        this.game = game;
    }

    public Game getGame() {
        return this.game;
    }



}
