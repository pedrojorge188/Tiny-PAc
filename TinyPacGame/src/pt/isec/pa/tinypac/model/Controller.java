package pt.isec.pa.tinypac.model;

import pt.isec.pa.tinypac.gameengine.IGameEngine;
import pt.isec.pa.tinypac.gameengine.IGameEngineEvolve;
import pt.isec.pa.tinypac.model.fsm.TinyPacContext;

public class Controller implements IGameEngineEvolve {

    TinyPacContext fsm;

    public Controller(TinyPacContext fsm) {
        this.fsm = fsm;
    }

    @Override
    public void evolve(IGameEngine gameEngine, long currentTime) {
        fsm.action();
    }
}
