package pt.isec.pa.tinypac.model.fsm.states;

import pt.isec.pa.tinypac.model.data.game.GameManager;
import pt.isec.pa.tinypac.model.fsm.TinyPacContext;
import pt.isec.pa.tinypac.model.fsm.TinyPacState;
import pt.isec.pa.tinypac.model.fsm.TinyPacStateAdapter;
import pt.isec.pa.tinypac.utils.Messages;

public class MoveGhostState extends TinyPacStateAdapter {

    private int scope_counter;
    private int num_balls;

    public MoveGhostState(TinyPacContext context, GameManager game) {
        super(context, game);

        Messages.getInstance().clearLogs();
        Messages.getInstance().addLog("ESTADO-> MOVE_GHOST");

        scope_counter = game.getPacManLife();
        num_balls = game.getBuff();

    }

    @Override
    public TinyPacState getState() {
        return TinyPacState.MOVE_GHOST;
    }


    @Override
    public boolean getPacman() {
        if(game.getPacManLife() > 0){
            changeState(TinyPacState.START_GAME);

        }else{
            changeState(TinyPacState.GAME_OVER);
        }
        return true;
    }

    @Override
    public boolean pacManFinish() {
        changeState(TinyPacState.NEXT_LEVEL);
        return true;
    }

    @Override
    public boolean pacManBuff() {

        changeState(TinyPacState.VULNERABLE_GHOST);
        return true;

    }

    @Override
    public void action() {

        game.movePacman(direction);
        game.moveGhost();

        if(game.stepLevelState())
            pacManFinish();

        if(scope_counter != game.getPacManLife()){
            getPacman();
        }

        if(num_balls != game.getBuff()){
            pacManBuff();
        }

    }

}