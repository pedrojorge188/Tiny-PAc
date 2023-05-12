package pt.isec.pa.tinypac.model.fsm;

import pt.isec.pa.tinypac.model.data.game.GameManager;
import pt.isec.pa.tinypac.model.fsm.states.*;

/**
 * Interface com todos os metodos necessarios para a gestão da maquina de estados
 */

public interface ITinyPacState {

    TinyPacState getState();

    boolean action(); //evoluir açoes do estado
    boolean keyPress(int direction);
    boolean pause();
    boolean resume();

    /**
     * Função create state, corresponde a uma "factory" para a criação das instancias correspondente aos estados da máquina de estados
     */

    static ITinyPacState createState(TinyPacState type, TinyPacContext context, GameManager game){

        return switch (type) {
            case START_GAME -> new StartGameState(context,game);
            case MOVE_PACMAN -> new MovePacmanState(context,game);
            case MOVE_GHOST -> new MoveGhostState(context,game);
            case PAUSE_STATE -> new PauseState(context,game);
            case GAME_OVER -> new GameOverState(context,game);
            case GAME_WIN -> new GameWinState(context,game);
            case VULNERABLE_GHOST -> new VulnerableGhostState(context,game);
        };

    }

}
