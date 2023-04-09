package pt.isec.pa.tinypac.model.fsm.states;

import pt.isec.pa.tinypac.gameengine.IGameEngine;
import pt.isec.pa.tinypac.model.data.game.GameManager;
import pt.isec.pa.tinypac.model.fsm.TinyPacContext;
import pt.isec.pa.tinypac.model.fsm.TinyPacState;
import pt.isec.pa.tinypac.model.fsm.TinyPacStateAdapter;

public class NextLevelState extends TinyPacStateAdapter {

        public NextLevelState(TinyPacContext context, GameManager game) {
            super(context, game);
            System.out.println("ESTADO NEXT_LEVEL");

            gameEngine.stop();
            gameEngine.waitForTheEnd();

            if(game.setLevel()){
                this.changeState(new StartGameState(context,game));
            }

        }

        @Override
        public TinyPacState getState() {
            return TinyPacState.NEXT_LEVEL;
        }

        @Override
        public boolean keyPress(int direction) {
            TinyPacStateAdapter.direction = direction;
            return true;
        }

        @Override
        public void evolve(IGameEngine gameEngine, long currentTime) {

        }
}



