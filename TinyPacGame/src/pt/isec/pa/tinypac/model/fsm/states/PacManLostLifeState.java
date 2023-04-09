package pt.isec.pa.tinypac.model.fsm.states;

import pt.isec.pa.tinypac.gameengine.IGameEngine;
import pt.isec.pa.tinypac.model.data.game.GameManager;
import pt.isec.pa.tinypac.model.fsm.TinyPacContext;
import pt.isec.pa.tinypac.model.fsm.TinyPacState;
import pt.isec.pa.tinypac.model.fsm.TinyPacStateAdapter;

public class PacManLostLifeState extends TinyPacStateAdapter {

    public PacManLostLifeState(TinyPacContext context, GameManager game) {
        super(context, game);
        System.out.println("ESTADO PACMAN_LOST_LIFE");
        timeout();
    }

    @Override
    public boolean timeout() {

        if(game.getPacManLife() == 0){
            context.changeState(new GameOverState(context,game));
        }else{

            gameEngine.stop();
            gameEngine.waitForTheEnd();
            context.changeState(new StartGameState(context,game));
        }

        return true;
    }

    @Override
    public void evolve(IGameEngine gameEngine, long currentTime) {

    }

    @Override
    public TinyPacState getState() {
       return TinyPacState.PACAMAN_LOST_LIFE;
    }
}
