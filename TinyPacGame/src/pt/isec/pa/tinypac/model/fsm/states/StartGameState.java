package pt.isec.pa.tinypac.model.fsm.states;

import pt.isec.pa.tinypac.gameengine.GameEngine;
import pt.isec.pa.tinypac.gameengine.IGameEngine;
import pt.isec.pa.tinypac.model.data.game.GameManager;
import pt.isec.pa.tinypac.model.fsm.TinyPacContext;
import pt.isec.pa.tinypac.model.fsm.TinyPacState;
import pt.isec.pa.tinypac.model.fsm.TinyPacStateAdapter;

public class StartGameState extends TinyPacStateAdapter {


    public StartGameState(TinyPacContext context, GameManager game) {
        super(context, game);

        try{
            gameEngine = new GameEngine();
            gameEngine.start(290);

            System.out.println("ESTADO 1");
            game.fillGame();

            if(game.getPacManLife() < 3){
                new MovePacmanState(context,game);
            }

        }catch (Exception e){}

    }


    @Override
    public TinyPacState getState() {
        return TinyPacState.START_GAME;
    }

    @Override
    public boolean keyPress(int direction) {

        TinyPacStateAdapter.direction = direction;
        changeState(new MovePacmanState(context,game));
        return true;
    }

    @Override
    public void evolve(IGameEngine gameEngine, long currentTime) {

    }
}
