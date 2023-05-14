package pt.isec.pa.tinypac.model.fsm;

import pt.isec.pa.tinypac.model.data.game.GameManager;

/**
 * Classe do adaptavel aos estados gestores do jogo
 */

public abstract class TinyPacStateAdapter implements ITinyPacState{

    protected static GameManager game;
    protected static int direction = 0;
    protected static TinyPacState stop_state = null;
    protected TinyPacContext context;

    public TinyPacStateAdapter(TinyPacContext context, GameManager game){
        TinyPacStateAdapter.game = game;
        this.context = context;
    }

    protected void changeState(TinyPacState newState){
        context.changeState(ITinyPacState.createState(newState,this.context,game));
    }


    @Override
    public abstract boolean action();

    @Override
    public boolean keyPress(int direction) {
        GameManager test = new GameManager(game);

        if(test.movePacman(direction)){

            TinyPacStateAdapter.direction = direction;

        }
        return true;
    }

    @Override
    public boolean pause() {
        stop_state = this.getState();
        changeState(TinyPacState.PAUSE_STATE);
        return true;
    }

    @Override
    public boolean resume() {

        return false;
    }
}