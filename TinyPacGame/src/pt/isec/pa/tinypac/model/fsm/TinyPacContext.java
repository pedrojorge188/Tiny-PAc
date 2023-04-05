package pt.isec.pa.tinypac.model.fsm;

import pt.isec.pa.tinypac.model.data.game.GameManager;
import pt.isec.pa.tinypac.model.fsm.states.StartGameState;

public class TinyPacContext {

    private GameManager game;
    private ITinyPacState state;

    public TinyPacContext(){
        this.game = new GameManager();
        state = new StartGameState(this,game);
    }

    public TinyPacState getState(){
        return state.getState();
    }

    public void changeState(ITinyPacState newState) {
        this.state = newState;
    }

    public Object getPoints(){
        return game.getPoints();
    }

    public Object getMazeCols(){
        return game.getMazeCols();
    }

    public Object getMazeRows(){
        return game.getMazeRows();
    }

    public  Object getLevel(){
        return game.getLevel();
    }

    public Object getPacManLife(){
        return game.getPacManLife();
    }

    public Object getMaze(){
        return game.getMaze();
    }

    public boolean keyPress(int direction) {
        return state.keyPress(direction);
    }

    public boolean getPacman() {
        return state.getPacman();
    }

    public boolean pacManFinish() {
        return state.pacManFinish();
    }

    public boolean pacManBuff() {
        return state.pacManBuff();
    }

    public boolean pacManKillGhosts() {
        return state.pacManKillGhosts();
    }

}
