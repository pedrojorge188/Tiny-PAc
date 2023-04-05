package pt.isec.pa.tinypac.model.fsm.states;

import pt.isec.pa.tinypac.model.data.game.GameManager;
import pt.isec.pa.tinypac.model.fsm.TinyPacContext;
import pt.isec.pa.tinypac.model.fsm.TinyPacState;
import pt.isec.pa.tinypac.model.fsm.TinyPacStateAdapter;

public class MoveGhostState extends TinyPacStateAdapter {

    public MoveGhostState(TinyPacContext context, GameManager game) {
        super(context, game);
        System.out.println("Estado 3");
    }

    @Override
    public TinyPacState getState() {
        return TinyPacState.MOVE_GHOST;
    }

    @Override
    public boolean keyPress(int direction) {
        game.movePacman(direction);
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
