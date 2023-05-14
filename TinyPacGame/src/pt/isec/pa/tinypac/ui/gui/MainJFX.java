package pt.isec.pa.tinypac.ui.gui;

import javafx.application.Application;
import javafx.scene.Scene;
import javafx.stage.Stage;
import pt.isec.pa.tinypac.gameengine.GameEngine;
import pt.isec.pa.tinypac.model.Controller;
import pt.isec.pa.tinypac.ui.gui.log.ModelStage;
import pt.isec.pa.tinypac.ui.gui.components.RootPane;

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
        gameEngine.start(300);
    }

    @Override
    public void start(Stage stage) throws Exception {

        ms = new ModelStage(stage);

        stage.setTitle("DEIS-ISEC-PA");
        stage.setFullScreen(false);     //é iniciado em fullscreen
        stage.setResizable(false);      //nao deixa alterar a dimensao da tela
        stage.centerOnScreen();


        RootPane root = new RootPane(stage,manager);

        Scene initial_scene = new Scene(root,1000,720);
        stage.setScene(initial_scene);
        stage.show();

        stage.setOnHidden(e ->{
            ms.close();
        });
    }

    @Override
    public void stop() throws Exception {

        //gameEngine.waitForTheEnd();
    }
}
