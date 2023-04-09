package pt.isec.pa.tinypac.model.fsm.states;

import pt.isec.pa.tinypac.gameengine.IGameEngine;
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
        System.out.println("ESTADO MOVE_PACMAN");
        timer = new Timer();

        gameEngine.registerClient(this);

        this.timer.schedule(new TimerTask() {
                                @Override
                                public void run() {
                                    timeout();
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
        TinyPacStateAdapter.direction = direction;
        return true;
    }

    @Override
    public boolean timeout() {
        context.changeState(new MoveGhostState(context,game));
        return true;
    }


    @Override
    public void evolve(IGameEngine gameEngine, long currentTime) {
        if(getState() == TinyPacState.START_GAME || getState() == TinyPacState.GAME_OVER)
            gameEngine.unregisterClient(this);
        game.movePacman(direction);
    }
}
