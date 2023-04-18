package pt.isec.pa.tinypac.model.fsm.states;

import pt.isec.pa.tinypac.model.data.game.GameManager;
import pt.isec.pa.tinypac.model.fsm.TinyPacContext;
import pt.isec.pa.tinypac.model.fsm.TinyPacState;
import pt.isec.pa.tinypac.model.fsm.TinyPacStateAdapter;
import pt.isec.pa.tinypac.utils.Messages;

public class StartGameState extends TinyPacStateAdapter {

    public StartGameState(TinyPacContext context, GameManager game) {
        super(context, game);

        try{
            Messages.getInstance().clearLogs();
            Messages.getInstance().addLog("ESTADO-> START_GAME");
            game.fillGame();

        }catch (Exception e){}


    }

    @Override
    public void action() {

    }

    @Override
    public TinyPacState getState() {
        return TinyPacState.START_GAME;
    }

    @Override
    public boolean keyPress(int direction) {

        TinyPacStateAdapter.direction = direction;
        changeState(TinyPacState.MOVE_PACMAN);
        return true;
    }


}