import pt.isec.pa.tinypac.model.fsm.TinyPacContext;
import pt.isec.pa.tinypac.ui.text.TinyPacUI;

import java.io.IOException;

public class Main {
    public static void main(String[] args) throws IOException, InterruptedException {

    TinyPacContext fsm = new TinyPacContext();
    TinyPacUI ui = new TinyPacUI(fsm);
    ui.start();


    }
}