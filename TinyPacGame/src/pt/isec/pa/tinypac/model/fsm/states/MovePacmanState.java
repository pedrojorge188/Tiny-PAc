package pt.isec.pa.tinypac.model.fsm.states;

import pt.isec.pa.tinypac.model.data.game.GameManager;
import pt.isec.pa.tinypac.model.fsm.TinyPacContext;
import pt.isec.pa.tinypac.model.fsm.TinyPacState;
import pt.isec.pa.tinypac.model.fsm.TinyPacStateAdapter;

import java.util.Timer;
import java.util.TimerTask;

public class MovePacmanState extends TinyPacStateAdapter {

    private Timer timer;

    public MovePacmanState(TinyPacContext context, GameManager game) {
        super(context, game);
        timer = new Timer();
        this.timer.schedule(new TimerTask() {
                                @Override
                                public void run() {
                                    context.changeState(new MoveGhostState(context,game));
                                }
                            }
                , 5000);
    }

    @Override
    public TinyPacState getState() {
        return TinyPacState.MOVE_PACMAN;
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
