package pt.isec.pa.tinypac.ui.gui;

import javafx.application.Application;
import javafx.scene.Scene;
import javafx.stage.Stage;

/**
 * Classe onde extendemos a application para usarmos o JavaFX
 */

public class MainJFX extends Application {


    @Override
    public void start(Stage stage) throws Exception {


        stage.setTitle("DEIS-ISEC-PA");
        stage.setAlwaysOnTop(true);     //fica sempre por cima de todas as janelas
        stage.setFullScreen(false);     //é iniciado em fullscreen
        stage.setResizable(false);      //nao deixa alterar a dimensao da tela
        stage.centerOnScreen();


        RootPane root = new RootPane();
        Scene initial_scene = new Scene(root,1000,720);
        stage.setScene(initial_scene);

        stage.show();

    }
}
