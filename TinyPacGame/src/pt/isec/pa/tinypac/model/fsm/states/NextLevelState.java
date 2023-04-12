package pt.isec.pa.tinypac.model.fsm.states;

import pt.isec.pa.tinypac.gameengine.IGameEngine;
import pt.isec.pa.tinypac.model.data.game.GameManager;
import pt.isec.pa.tinypac.model.fsm.TinyPacContext;
import pt.isec.pa.tinypac.model.fsm.TinyPacState;
import pt.isec.pa.tinypac.model.fsm.TinyPacStateAdapter;
import pt.isec.pa.tinypac.utils.Messages;

public class NextLevelState extends TinyPacStateAdapter {

    public NextLevelState(TinyPacContext context, GameManager game) {
        super(context, game);
        Messages.getInstance().clearLogs();
        Messages.getInstance().addLog("ESTADO-> LEVEL_UP");
    }

    @Override
    public TinyPacState getState() {
        return TinyPacState.NEXT_LEVEL;
    }

    @Override
    public boolean keyPress(int direction) {

        if(game.setLevel()){
            direction = 0;
            changeState(TinyPacState.START_GAME);
        }

        TinyPacStateAdapter.direction = direction;
        return true;
    }

    @Override
    public void evolve(IGameEngine gameEngine, long currentTime) {

    }
}
