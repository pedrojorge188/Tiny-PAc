package pt.isec.pa.tinypac.model.fsm;

import pt.isec.pa.tinypac.model.data.game.GameManager;
import pt.isec.pa.tinypac.model.fsm.states.StartGameState;

/**
 * Contexo da maquina de estados:
 *  - Esta classe gere as dependencias usadas na maquina de estados.
 */

public class TinyPacContext{

    private GameManager game;
    private ITinyPacState state;

    public TinyPacContext(GameManager game){

        this.game = game;

        state = ITinyPacState.createState(TinyPacState.START_GAME,this,game);

    }


    public TinyPacState getState(){
        return state.getState();
    }

    /**
     * Muda de estado para o estado introduzido por paramentro
     * @param newState
     */

    void changeState(ITinyPacState newState) {
        this.state = newState;
    }

    public boolean action(){
        return state.action();
    }

    public boolean keyPress(int direction) {
        return state.keyPress(direction);
    }

    public boolean pause(){
        return state.pause();
    }

    public boolean resume(){
        return state.resume();
    }

    public int getPoints(){
        return game.getPoints();
    }

    public int getMazeCols(){
        return game.getMazeCols();
    }

    public int getMazeRows(){
        return game.getMazeRows();
    }

    public int getLevel(){
        return game.getLevel();
    }

    public int getPacManLife(){
        return game.getPacManLife();
    }

    public char[][] getMaze(){
        return game.getMaze();
    }

    public boolean getFruit(){
        return game.getFruitStatus();
    }

    public void replaceGameManager(GameManager gameManager){
        this.game = gameManager;
    }

    public void disableFsm(){
        this.changeState(new StartGameState(this,game));
    }

    /**
     * indica a direção da tecla introduzida e guarda a numa variavel
     * @param direction
     * @return
     */

}
