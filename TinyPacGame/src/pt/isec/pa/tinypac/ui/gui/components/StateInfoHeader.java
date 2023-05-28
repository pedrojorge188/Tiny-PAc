package pt.isec.pa.tinypac.ui.gui.components;

import javafx.application.Platform;
import javafx.geometry.Pos;
import javafx.scene.control.Label;
import javafx.stage.Stage;
import pt.isec.pa.tinypac.model.Controller;

public class StateInfoHeader extends Label {

    private Controller manager;
    private Stage mainStage;

    public StateInfoHeader(Stage mainStage, Controller manager) {

        super(" ");
        this.manager = manager;
        this.mainStage = mainStage;

        createViews();
        registerHandlers();
        update();
    }

    /**
     * Cria os componentes inicias deste pane
     */

    private void createViews() {

        this.setAlignment(Pos.CENTER);
        this.setStyle("-fx-text-fill: white;" +
                " -fx-background-color: #032672; " +
                "-fx-padding: 20px;" +
                "-fx-font-size: 20;" +
                " -fx-font-family: 'Courier New';" +
                " -fx-padding: 20;"  +
                " -fx-border-width: 2px 1px 2px 1px;" +
                "-fx-border-radius: 10px;" +
                " -fx-border-color: white;" +
                "-fx-background-radius: 10px");
    }

    private void registerHandlers() {

        manager.addPropertyChangeListener(Controller.PROP_GAME_INFO, evt -> {
            Platform.runLater(new Runnable() {
                @Override
                public void run() {
                    update();
                }
            });
        });

    }

    private void update() {

        switch (manager.getState()){
            case START_GAME -> {
                this.setText("PRESS KEY TO START!");
            }

            case PAUSE_STATE -> {
                this.setText("GAME PAUSED");

            }

            case VULNERABLE_GHOST -> {
                this.setText("VULNERABLE GHOSTS ("+manager.getVulnerable()+")" );

            }

            case GAME_OVER -> {

                this.setText("YOU LOSE - PRESS KEY TO CONTINUE");

            }

            case GAME_WIN -> {
                this.setText("YOU WIN - PRESS KEY TO CONTINUE");

            }

            default -> {
                this.setText("PAC MAN");

            }
        }

    }

}
