import pt.isec.pa.tinypac.gameengine.GameEngine;
import pt.isec.pa.tinypac.model.Controller;
import pt.isec.pa.tinypac.model.fsm.TinyPacContext;
import pt.isec.pa.tinypac.ui.text.TinyPacUI;

import java.io.IOException;

public class Main {
    public static void main(String[] args) throws IOException, InterruptedException {

    GameEngine gameEngine = new GameEngine();

    TinyPacContext fsm = new TinyPacContext();
    Controller controller = new Controller(fsm);
    TinyPacUI ui = new TinyPacUI(fsm);
    gameEngine.registerClient(controller);
    gameEngine.start(300);

    ui.start();
    gameEngine.waitForTheEnd();

    }
}