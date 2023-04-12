package pt.isec.pa.tinypac.model.fsm.states;

import pt.isec.pa.tinypac.gameengine.IGameEngine;
import pt.isec.pa.tinypac.model.data.game.GameManager;
import pt.isec.pa.tinypac.model.fsm.TinyPacContext;
import pt.isec.pa.tinypac.model.fsm.TinyPacState;
import pt.isec.pa.tinypac.model.fsm.TinyPacStateAdapter;
import pt.isec.pa.tinypac.utils.Messages;

import java.util.Timer;
import java.util.TimerTask;

public class MovePacmanState extends TinyPacStateAdapter {

    private Timer timer;

    public MovePacmanState(TinyPacContext context, GameManager game) {
        super(context, game);

        gameEngine.registerClient(this);
        Messages.getInstance().clearLogs();
        Messages.getInstance().addLog("ESTADO-> MOVE_PACMAN");

        timer = new Timer();
        this.timeout();

    }

    @Override
    public TinyPacState getState() {
        return TinyPacState.MOVE_PACMAN;
    }

    @Override
    public boolean keyPress(int direction) {
        TinyPacStateAdapter.direction = direction;
        return true;
    }

    @Override
    public boolean timeout() {

        this.timer.schedule(new TimerTask() {
                                @Override
                                public void run() {
                                    changeState(TinyPacState.MOVE_GHOST);
                                }
                            }
                , 5000);



        return true;
    }


    @Override
    public void evolve(IGameEngine gameEngine, long currentTime) {

        game.movePacman(direction);
    }
}