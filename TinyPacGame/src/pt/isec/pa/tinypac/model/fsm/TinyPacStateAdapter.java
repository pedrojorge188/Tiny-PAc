package pt.isec.pa.tinypac.model.fsm;

import pt.isec.pa.tinypac.model.data.game.GameManager;

public abstract class TinyPacStateAdapter implements ITinyPacState{

    protected GameManager game;
    protected TinyPacContext context;

    public TinyPacStateAdapter(TinyPacContext context, GameManager game){
        this.game = game;
        this.context = context;
    }

    protected void changeState(ITinyPacState newState){
        context.changeState(newState);
    }

    @Override
    public boolean keyPress(int direction) {
         return false;
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
