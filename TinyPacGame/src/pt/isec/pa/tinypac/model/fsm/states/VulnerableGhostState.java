package pt.isec.pa.tinypac.model.fsm.states;

import pt.isec.pa.tinypac.model.data.game.GameManager;
import pt.isec.pa.tinypac.model.data.ghost.Ghost;
import pt.isec.pa.tinypac.model.fsm.TinyPacContext;
import pt.isec.pa.tinypac.model.fsm.TinyPacState;
import pt.isec.pa.tinypac.model.fsm.TinyPacStateAdapter;
import pt.isec.pa.tinypac.utils.Messages;

/**
 * Estado VulnerableGhostState:
 * Este estado è responsavel por realizar todas as verificações necessárias
 * para o estado onde os fantasmas podem ser mortos pelo pacman
 */

public class VulnerableGhostState extends TinyPacStateAdapter {

    private int scope_counter;
    private static int counter = 0;
    private static int n_ghost_dead = 1;

    public  VulnerableGhostState(TinyPacContext context, GameManager game) {
        super(context, game);

        game.toogleGhostsStatus(true);

        scope_counter = game.getPacManLife();
        Messages.getInstance().clearLogs();
        Messages.getInstance().addLog("ESTADO-> VULNERABLE_GHOST");

    }

    @Override
    public boolean timeout() {

        counter++;
        if(counter == 45 || n_ghost_dead == 5){
            game.toogleGhostsStatus(false);
            counter = 0;
            n_ghost_dead = 1;
            changeState(TinyPacState.MOVE_GHOST);
        }

        return true;
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
    public void action() {

        for(Ghost e : game.getGhost()){
            if(e.getX() == game.getPacman().getX() && e.getY() == game.getPacman().getY() && e.getVulnerability()){
                e.reset();
                game.setPoints(n_ghost_dead*100);
                n_ghost_dead++;
            }
        }

        game.movePacman(direction);
        game.moveGhost();

        if(game.stepLevelState())
            pacManFinish();

        if(scope_counter != game.getPacManLife()){
            getPacman();
        }

        timeout();

    }


    @Override
    public TinyPacState getState() {
        return TinyPacState.VULNERABLE_GHOST;
    }

}