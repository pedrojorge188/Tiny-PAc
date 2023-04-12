package pt.isec.pa.tinypac.model.fsm.states;

import pt.isec.pa.tinypac.gameengine.IGameEngine;
import pt.isec.pa.tinypac.model.data.game.GameManager;
import pt.isec.pa.tinypac.model.fsm.TinyPacContext;
import pt.isec.pa.tinypac.model.fsm.TinyPacState;
import pt.isec.pa.tinypac.model.fsm.TinyPacStateAdapter;
import pt.isec.pa.tinypac.utils.Messages;

public class MoveGhostState extends TinyPacStateAdapter {

    private int scope_counter;

    public MoveGhostState(TinyPacContext context, GameManager game) {
        super(context, game);

        gameEngine.registerClient(this);
        Messages.getInstance().clearLogs();
        Messages.getInstance().addLog("ESTADO-> MOVE_GHOST");

        scope_counter = game.getPacManLife();
    }

    @Override
    public TinyPacState getState() {
        return TinyPacState.MOVE_GHOST;
    }


    @Override
    public boolean getPacman() {
        if(game.getPacManLife() > 0){
            gameEngine.stop(); gameEngine.waitForTheEnd();
            changeState(TinyPacState.START_GAME);

        }else{
            gameEngine.stop();
            gameEngine.waitForTheEnd();
            changeState(TinyPacState.GAME_OVER);
        }
        return true;
    }

    @Override
    public boolean pacManFinish() {
        gameEngine.stop();
        gameEngine.waitForTheEnd();
        changeState(TinyPacState.NEXT_LEVEL);
        return true;
    }

    @Override
    public boolean pacManBuff() {
        return false;
    }


    @Override
    public void evolve(IGameEngine gameEngine, long currentTime) {

       // game.moveGhost();

        if(game.stepLevelState())
            pacManFinish();

        if(scope_counter != game.getPacManLife()){
            getPacman();
        }

    }
}