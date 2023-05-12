import javafx.application.Application;
import pt.isec.pa.tinypac.ui.gui.MainJFX;

import java.io.IOException;

public class Main {
    public static void main(String[] args) throws IOException, InterruptedException {

    //Interface de terminal com lanterna
    /*

        GameEngine gameEngine = new GameEngine();

        TinyPacContext fsm = new TinyPacContext();
        Controller controller = new Controller(fsm);
        TinyPacUI ui = new TinyPacUI(fsm);
        gameEngine.registerClient(controller);
        gameEngine.start(300);

        ui.start();
        gameEngine.waitForTheEnd();
    */

    //Interface GUI

    Application.launch(MainJFX.class,args);

    }
}