package pt.isec.pa.tinypac.model.fsm.states;

import pt.isec.pa.tinypac.gameengine.IGameEngine;
import pt.isec.pa.tinypac.model.data.game.GameManager;
import pt.isec.pa.tinypac.model.fsm.TinyPacContext;
import pt.isec.pa.tinypac.model.fsm.TinyPacState;
import pt.isec.pa.tinypac.model.fsm.TinyPacStateAdapter;

public class GameOverState extends TinyPacStateAdapter{


    public GameOverState(TinyPacContext context, GameManager game) {
        super(context, game);
        System.out.println("ESTADO 5");
        gameEngine.stop();
        gameEngine.waitForTheEnd();
    }

    @Override
    public TinyPacState getState() {
        return TinyPacState.GAME_OVER;
    }

    @Override
    public boolean keyPress(int direction) {
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

    @Override
    public void evolve(IGameEngine gameEngine, long currentTime) {

    }


}
