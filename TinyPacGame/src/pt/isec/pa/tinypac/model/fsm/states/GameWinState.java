package pt.isec.pa.tinypac.model.fsm.states;

import pt.isec.pa.tinypac.model.data.game.GameManager;
import pt.isec.pa.tinypac.model.fsm.TinyPacContext;
import pt.isec.pa.tinypac.model.fsm.TinyPacState;
import pt.isec.pa.tinypac.model.fsm.TinyPacStateAdapter;
import pt.isec.pa.tinypac.utils.Messages;

/**
 * Estado GameWin:
 * este estado representa o fim de jogo como uma vitoria,
 * onde será possivel realizar as verificações necessarias do top5 e
 * garantias da vitoria do jogo.
 */

public class GameWinState extends TinyPacStateAdapter {

    public GameWinState(TinyPacContext context, GameManager game) {
        super(context, game);
        Messages.getInstance().clearLogs();
        Messages.getInstance().addLog("ESTADO-> GAME_WIN");
    }

    @Override
    public boolean action() {
        return false;
    }

    @Override
    public boolean keyPress(int direction) {
        return false;
    }

    @Override
    public boolean resume() {
        return false;
    }

    @Override
    public TinyPacState getState() {
        return TinyPacState.GAME_WIN;
    }

}
