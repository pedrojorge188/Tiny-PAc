package pt.isec.pa.tinypac.model.fsm.states;

import pt.isec.pa.tinypac.model.data.game.GameManager;
import pt.isec.pa.tinypac.model.fsm.TinyPacContext;
import pt.isec.pa.tinypac.model.fsm.TinyPacState;
import pt.isec.pa.tinypac.model.fsm.TinyPacStateAdapter;
import pt.isec.pa.tinypac.utils.Messages;

/**
 * Estado MoveGhostState:
 * Este estado liberta os fantasmas das casas iniciais,
 * sempre que o pacman comer todos os alimentos do maze então o estado será alterado para o nextLevel,
 * sempre que o pacman perder uma vida e esta for maior que zero então o jogo iniciará de novo, caso
 * contrario entao o jogador perde o jogo.
 */

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


    private boolean getPacman() {
        if(game.getPacManLife() > 0){
            game.removePacManLife();
            changeState(TinyPacState.START_GAME);

        }else{
            changeState(TinyPacState.GAME_OVER);
        }
        return true;
    }

    private boolean pacManFinish() {

        if(game.setLevel()){
            direction = 0;
            if(game.getLevel() == 20){
                num_balls = game.getBuff();
                changeState(TinyPacState.GAME_WIN);
            }else{
                num_balls = game.getBuff();
                changeState(TinyPacState.START_GAME);
            }
        }

        return true;
    }


    private boolean pacManBuff() {

        changeState(TinyPacState.VULNERABLE_GHOST);
        return true;

    }

    @Override
    public boolean action() {

        game.movePacman(direction);
        game.moveGhost();

        if(game.stepLevelState())
            pacManFinish();
        else if(scope_counter != game.getPacManLife()){
            getPacman();

        }else if(num_balls != game.getBuff()){
            pacManBuff();
        }

        return false;
    }


}