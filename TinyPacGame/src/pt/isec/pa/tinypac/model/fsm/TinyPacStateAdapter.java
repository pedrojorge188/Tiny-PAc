package pt.isec.pa.tinypac.model.fsm;

import pt.isec.pa.tinypac.gameengine.IGameEngine;
import pt.isec.pa.tinypac.gameengine.IGameEngineEvolve;
import pt.isec.pa.tinypac.model.data.game.GameManager;

public abstract class TinyPacStateAdapter implements ITinyPacState, IGameEngineEvolve {

    protected static IGameEngine gameEngine ;

    protected static GameManager game;

    protected static int direction = 0;
    protected TinyPacContext context;

    public TinyPacStateAdapter(TinyPacContext context, GameManager game){

        TinyPacStateAdapter.game = game;
        this.context = context;

    }

    protected void changeState(TinyPacState newState){
        context.changeState(ITinyPacState.createState(newState,this.context,game));
    }


    @Override
    public void evolve(IGameEngine gameEngine, long currentTime) {

    }

    @Override
    public boolean keyPress(int direction) {

        System.out.println("clicou");
        TinyPacStateAdapter.direction = direction;
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
    public boolean timeout() {
        return false;
    }
}