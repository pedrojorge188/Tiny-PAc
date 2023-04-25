package pt.isec.pa.tinypac.model.fsm.states;

import pt.isec.pa.tinypac.model.data.game.GameManager;
import pt.isec.pa.tinypac.model.fsm.TinyPacContext;
import pt.isec.pa.tinypac.model.fsm.TinyPacState;
import pt.isec.pa.tinypac.model.fsm.TinyPacStateAdapter;
import pt.isec.pa.tinypac.utils.Messages;

/**
 *  Estado NextLevel:
 *  este estado gere qual será o proximo nivel a ser jogado.
 *  Caso o nivel seja 20 então o jogo mudará de estado para o estado de vitoria "GameWinState"
 */

public class NextLevelState extends TinyPacStateAdapter {

    public NextLevelState(TinyPacContext context, GameManager game) {
        super(context, game);
        Messages.getInstance().clearLogs();
        Messages.getInstance().addLog("ESTADO-> LEVEL_UP");
    }

    @Override
    public void action() { }

    @Override
    public TinyPacState getState() {
        return TinyPacState.NEXT_LEVEL;
    }

    @Override
    public boolean keyPress(int direction) {

        if(game.setLevel()){
            direction = 0;
            if(game.getLevel() == 20){
                changeState(TinyPacState.GAME_WIN);
            }else{
                changeState(TinyPacState.START_GAME);
            }
        }

        return true;
    }

}
