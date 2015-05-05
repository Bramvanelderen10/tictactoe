package kb50_week2.tictactoe;

/**
 * Created by Bram on 5/6/2015.
 */
public class Game {
    public enum GameStates{
        ONGOING,
        PLAYFIELD_FULL,
        PLAYER_WON,
        AI_WON,
    }

    private GameStates gamestate;

    public Game(){
        gamestate = GameStates.ONGOING;
    }

    public GameStates getGamestate() {
        return gamestate;
    }

    public void setGamestate(GameStates gamestate) {
        this.gamestate = gamestate;
    }
}
