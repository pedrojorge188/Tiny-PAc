package pt.isec.pa.tinypac.model.fsm.states;

import pt.isec.pa.tinypac.gameengine.IGameEngine;
import pt.isec.pa.tinypac.model.data.game.GameManager;
import pt.isec.pa.tinypac.model.fsm.TinyPacContext;
import pt.isec.pa.tinypac.model.fsm.TinyPacState;
import pt.isec.pa.tinypac.model.fsm.TinyPacStateAdapter;

public class MoveGhostState extends TinyPacStateAdapter {

    private final int scope_counter;

    public MoveGhostState(TinyPacContext context, GameManager game) {
        super(context, game);

        System.out.println("ESTADO 3");
        scope_counter = game.getPacManLife();
        gameEngine.registerClient(this);
        game.moveGhost();
    }

    @Override
    public TinyPacState getState() {
        return TinyPacState.MOVE_GHOST;
    }

    @Override
    public boolean keyPress(int direction) {
        TinyPacStateAdapter.direction = direction;
        return true;
    }

    @Override
    public boolean getPacman() {
        context.changeState(new PacManLostLifeState(context,game));
        return true;
    }

    @Override
    public boolean pacManFinish() {
        if(game.setLevel(game.getLevel()+1))
            new StartGameState(context,game);

        return true;
    }

    @Override
    public boolean pacManBuff() {
        return false;
    }


    @Override
    public void evolve(IGameEngine gameEngine, long currentTime) {
        game.moveGhost();

        if(game.stepLevelState())
            this.pacManFinish();

        if(scope_counter != game.getPacManLife()){
            this.getPacman();
        }

    }
}
