package pt.isec.pa.tinypac.model.fsm.states;

import pt.isec.pa.tinypac.model.data.game.GameManager;
import pt.isec.pa.tinypac.model.fsm.TinyPacContext;
import pt.isec.pa.tinypac.model.fsm.TinyPacState;
import pt.isec.pa.tinypac.model.fsm.TinyPacStateAdapter;

public class StartGameState extends TinyPacStateAdapter {


    public StartGameState(TinyPacContext context, GameManager game) {
        super(context, game);
        game.fillGame();
    }

    @Override
    public TinyPacState getState() {
        return TinyPacState.START_GAME;
    }

    @Override
    public boolean keyPress(int direction) {
        game.movePacman(direction);
        changeState(new MovePacmanState(context,game));
        return true;
    }


    @Override
    public boolean getPacman() {
        return false;
    }

    @Override
    public boolean pacManFinish() {
        return false;
    }

    @Override
    public boolean pacManBuff() {
        return false;
    }

    @Override
    public boolean pacManKillGhosts() {
        return false;
    }

}
