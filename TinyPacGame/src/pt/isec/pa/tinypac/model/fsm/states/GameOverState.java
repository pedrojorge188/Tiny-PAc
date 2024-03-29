package pt.isec.pa.tinypac.model.fsm.states;
import pt.isec.pa.tinypac.model.data.game.GameManager;
import pt.isec.pa.tinypac.model.fsm.TinyPacContext;
import pt.isec.pa.tinypac.model.fsm.TinyPacState;
import pt.isec.pa.tinypac.model.fsm.TinyPacStateAdapter;
import pt.isec.pa.tinypac.utils.Messages;

/**
 * Estado GameOverState:
 * este estado representa o fim do jogo como uma derrota.
 */

public class GameOverState extends TinyPacStateAdapter{


    public GameOverState(TinyPacContext context, GameManager game) {
        super(context, game);
        Messages.getInstance().clearLogs();
        Messages.getInstance().addLog("ESTADO-> GAME_OVER");
    }

    @Override
    public boolean action() {
        return false;
    }


    @Override
    public boolean resume() {
        return false;
    }

    @Override
    public TinyPacState getState() {
        return TinyPacState.GAME_OVER;
    }

    @Override
    public boolean keyPress(int direction) {
        return true;
    }



}