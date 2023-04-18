package pt.isec.pa.tinypac.model.fsm;

import pt.isec.pa.tinypac.gameengine.GameEngine;
import pt.isec.pa.tinypac.gameengine.IGameEngine;
import pt.isec.pa.tinypac.gameengine.IGameEngineEvolve;
import pt.isec.pa.tinypac.model.data.game.GameManager;

public class TinyPacContext implements IGameEngineEvolve{

    private GameManager game;
    private ITinyPacState state;
    private GameEngine gameEngine;

    public TinyPacContext(){
        this.game = new GameManager();
        gameEngine = new GameEngine();
        state = ITinyPacState.createState(TinyPacState.START_GAME,this,game);
        gameEngine.start(250);
        gameEngine.registerClient(this);

    }

    @Override
    public void evolve(IGameEngine gameEngine, long currentTime) {
        state.action();
    }

    public TinyPacState getState(){
        return state.getState();
    }

    public void changeState(ITinyPacState newState) {
        this.state = newState;
    }

    public Object getGhosts(){return  game.getGhost();}

    public Object getPacmanModel(){return  game.getPacman();}

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

    public Object getFruit(){
        return game.getFruitStatus();
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
