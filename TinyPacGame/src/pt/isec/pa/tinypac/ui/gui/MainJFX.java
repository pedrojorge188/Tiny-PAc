package pt.isec.pa.tinypac.ui.gui;

import javafx.application.Application;
import javafx.scene.Scene;
import javafx.stage.Stage;
import pt.isec.pa.tinypac.gameengine.GameEngine;
import pt.isec.pa.tinypac.model.Controller;
import pt.isec.pa.tinypac.ui.gui.panes.RootPane;
import pt.isec.pa.tinypac.ui.gui.log.ModelStage;

/**
 * Classe onde extendemos a application para usarmos o JavaFX
 */

public class MainJFX extends Application {

    private ModelStage ms;
    private Controller manager;
    private GameEngine gameEngine;

    public MainJFX(){
        this.manager = new Controller();
        gameEngine = new GameEngine();
    }

    @Override
    public void init() throws Exception {
        gameEngine.registerClient(manager);
        gameEngine.start(340);
    }

    @Override
    public void start(Stage stage) throws Exception {

       // ms = new ModelStage(stage,manager);

        stage.setTitle("DEIS-ISEC-PA");
        stage.setFullScreen(false);     //é iniciado em fullscreen
        stage.setResizable(false);      //nao deixa alterar a dimensao da tela
        stage.centerOnScreen();

        RootPane root = new RootPane(stage,manager);
        Scene initial_scene = new Scene(root,1280,720);
        stage.setScene(initial_scene);

        stage.show();


        Stage stage2 = new Stage();
        RootPane root2 = new RootPane(stage2,manager);
        stage2.setResizable(false);      //nao deixa alterar a dimensao da tela
        Scene initial_scene2 = new Scene(root2,1280,720);
        stage2.setScene(initial_scene2);

        stage2.show();

        stage.setOnHidden(e ->{
            //ms.close();
            //stage2.close();
        });


    }

    @Override
    public void stop() throws Exception {

        //gameEngine.waitForTheEnd();
    }
}
