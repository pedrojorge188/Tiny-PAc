package pt.isec.pa.tinypac.model;

import pt.isec.pa.tinypac.gameengine.IGameEngine;
import pt.isec.pa.tinypac.gameengine.IGameEngineEvolve;
import pt.isec.pa.tinypac.model.fsm.TinyPacContext;
import pt.isec.pa.tinypac.model.fsm.TinyPacState;

public class Controller implements IGameEngineEvolve {

    TinyPacContext fsm;

    public Controller() {
        this.fsm = new TinyPacContext();
    }

    @Override
    public void evolve(IGameEngine gameEngine, long currentTime) {
        fsm.action();
    }

    public boolean keyPress(int direction){
        return fsm.keyPress(direction);
    }

    public boolean pause(){
        return fsm.pause();
    }

    public boolean resume(){
        return  fsm.resume();
    }

    public TinyPacState getState(){
        return fsm.getState();
    }

    public int getPacmanLife(){
        return fsm.getPacManLife();
    }

    public int getGameRows(){
        return fsm.getMazeRows();
    }

    public int getGameCols(){
        return fsm.getMazeCols();
    }

    public boolean getFruit(){
        return fsm.getFruit();
    }

    public char[][] getMaze(){
        return fsm.getMaze();
    }
}
