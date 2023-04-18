package pt.isec.pa.tinypac.model.fsm;

import pt.isec.pa.tinypac.model.data.game.GameManager;
import pt.isec.pa.tinypac.model.fsm.states.*;

public interface ITinyPacState {

    TinyPacState getState();
    void action();
    boolean keyPress(int direction); // START_GAME -> MOVE_PACMAN
    boolean getPacman(); // MOVE_GHOST -> PACMAN_LOST_LIFE
    boolean pacManFinish(); // MOVE_GHOST -> NEXT_LEVEL
    boolean pacManBuff(); // MOVE_GHOST -> VULNERABLE_GHOST
    boolean pacManKillGhosts(); //VULNERABLE_GHOST -> MOVE_GHOST
    boolean timeout();

    static ITinyPacState createState(TinyPacState type, TinyPacContext context, GameManager game){


        return switch (type) {
            case START_GAME -> new StartGameState(context,game);
            case MOVE_PACMAN -> new MovePacmanState(context,game);
            case MOVE_GHOST -> new MoveGhostState(context,game);
            case NEXT_LEVEL -> new NextLevelState(context,game);
            case GAME_OVER -> new GameOverState(context,game);
            //case GAME_WIN -> new GameWinState(context,game);
            case VULNERABLE_GHOST -> new VulnerableGhostState(context,game);
            case GAME_WIN -> null;
        };

    }

}
