package pt.isec.pa.tinypac.model.fsm.states;

import pt.isec.pa.tinypac.model.data.game.GameManager;
import pt.isec.pa.tinypac.model.fsm.TinyPacContext;
import pt.isec.pa.tinypac.model.fsm.TinyPacState;
import pt.isec.pa.tinypac.model.fsm.TinyPacStateAdapter;
import pt.isec.pa.tinypac.utils.Messages;

/**
 * Estado movepacmanstate:
 * Este estado é responsavel por inicial o movimento do pacman,
 * assim que um timeout de x segundos chegar ao fim então o estado
 * de jogo será alterado para o move ghost state.
 */

public class MovePacmanState extends TinyPacStateAdapter {

    private static int counter = 0;

    public MovePacmanState(TinyPacContext context, GameManager game) {
        super(context, game);

        Messages.getInstance().clearLogs();
        Messages.getInstance().addLog("ESTADO-> MOVE_PACMAN");

        this.timeout();

    }

    @Override
    public void action() {
        game.movePacman(direction);
        timeout();
    }

    @Override
    public boolean pause() {
        return false;
    }

    @Override
    public TinyPacState getState() {
        return TinyPacState.MOVE_PACMAN;
    }


    private boolean timeout() {

        counter++;
        if(counter == 7){
            counter = 0;
            changeState(TinyPacState.MOVE_GHOST);
        }

        return true;
    }

}