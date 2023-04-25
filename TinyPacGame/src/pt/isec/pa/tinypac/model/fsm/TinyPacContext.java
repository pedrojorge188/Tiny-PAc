package pt.isec.pa.tinypac.model.fsm;

import pt.isec.pa.tinypac.gameengine.GameEngine;
import pt.isec.pa.tinypac.gameengine.IGameEngine;
import pt.isec.pa.tinypac.gameengine.IGameEngineEvolve;
import pt.isec.pa.tinypac.model.data.game.GameManager;

/**
 * Contexo da maquina de estados:
 *  - Esta classe gere as dependencias usadas na maquina de estados.
 */

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

    /**
     * Evole da interface IGameEngineEvole, garante a evolução dos elementos do jogo
     * @param gameEngine
     * @param currentTime
     */

    @Override
    public void evolve(IGameEngine gameEngine, long currentTime) {
        state.action();
    }


    public TinyPacState getState(){
        return state.getState();
    }

    /**
     * Muda de estado para o estado introduzido por paramentro
     * @param newState
     */

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

    /**
     * indica a direção da tecla introduzida e guarda a numa variavel
     * @param direction
     * @return
     */
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

}
