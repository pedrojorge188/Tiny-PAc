package pt.isec.pa.tinypac.model.fsm.states;

import pt.isec.pa.tinypac.model.data.game.GameManager;
import pt.isec.pa.tinypac.model.fsm.TinyPacContext;
import pt.isec.pa.tinypac.model.fsm.TinyPacState;
import pt.isec.pa.tinypac.model.fsm.TinyPacStateAdapter;
import pt.isec.pa.tinypac.utils.Messages;

/**
 * Estado onde o jogo fica pausado
 */

public class PauseState extends TinyPacStateAdapter{

    public PauseState(TinyPacContext context, GameManager game) {
        super(context, game);

        Messages.getInstance().clearLogs();
        Messages.getInstance().addLog("ESTADO-> PAUSE_STATE");

    }

    @Override
    public TinyPacState getState() {
        return  TinyPacState.PAUSE_STATE;
    }

    @Override
    public boolean action() {
        return false;
    }

    @Override
    public boolean keyPress(int direction) {
        return false;
    }

    @Override
    public boolean pause() {
        return false;
    }

    @Override
    public boolean resume() {
            changeState(stop_state);
            return true;
    }
}

