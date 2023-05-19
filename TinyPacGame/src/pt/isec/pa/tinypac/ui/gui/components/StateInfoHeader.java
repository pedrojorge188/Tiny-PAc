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
                this.setStyle("-fx-text-fill: YELLOW;" +
                        " -fx-font-size: 15px;" +
                        " -fx-font-family: 'Arial Black'"
                );
            }

            case PAUSE_STATE -> {
                this.setText("GAME PAUSED");
                this.setStyle("-fx-text-fill: RED;" +
                        " -fx-font-size: 15px;" +
                        " -fx-font-family: 'Arial Black'"
                );
            }

            case VULNERABLE_GHOST -> {
                this.setText("VULNERABLE GHOSTS");
                this.setStyle("-fx-text-fill: CYAN;" +
                        " -fx-font-size: 15px;" +
                        " -fx-font-family: 'Arial Black'"
                );
            }

            case GAME_OVER -> {
                this.setText("GAME OVER");
                this.setStyle("-fx-text-fill: RED;" +
                        " -fx-font-size: 15px;" +
                        " -fx-font-family: 'Arial Black'"
                );
            }

            case GAME_WIN -> {
                this.setText("YOU WIN");
                this.setStyle("-fx-text-fill: GREEN;" +
                        " -fx-font-size: 15px;" +
                        " -fx-font-family: 'Arial Black'"
                );
            }

            default -> {
                this.setText("PAC MAN");
                this.setStyle("-fx-text-fill: WHITE;" +
                        " -fx-font-size: 25px;" +
                        " -fx-font-family: 'Arial Black'"
                );
            }
        }

    }

}
