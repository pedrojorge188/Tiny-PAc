package pt.isec.pa.tinypac.ui.gui.components;

import javafx.application.Platform;
import javafx.geometry.Pos;
import javafx.scene.control.Label;
import javafx.scene.media.Media;
import javafx.scene.media.MediaPlayer;
import javafx.stage.Stage;
import pt.isec.pa.tinypac.model.Controller;

import java.io.File;

public class StateInfoHeader extends Label {
    private static int counter = 0;
    private Controller manager;
    private Stage mainStage;
    private Media media1, media2, media3;
    MediaPlayer start, vulnerable, death;

    public StateInfoHeader(Stage mainStage, Controller manager) {

        super(" ");
        counter = 0;
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

                if(counter == 0){
                    String audioFilePath = "src/pt/isec/pa/tinypac/ui/gui/resources/sounds/start.mp3";
                    File audioFile = new File(audioFilePath);
                    String audioFileUri = audioFile.toURI().toString();

                    media1 = new Media(audioFileUri);
                    start = new MediaPlayer(media1);
                    start.play();

                }

                counter ++;

            }

            case PAUSE_STATE -> {
                this.setText("GAME PAUSED");

            }

            case VULNERABLE_GHOST -> {

                this.setText("VULNERABLE GHOSTS ("+manager.getVulnerable()+")" );

                if(counter == 0) {
                    String audioFilePath = "src/pt/isec/pa/tinypac/ui/gui/resources/sounds/vulnerable.mp3";
                    File audioFile = new File(audioFilePath);
                    String audioFileUri = audioFile.toURI().toString();

                    media2 = new Media(audioFileUri);
                    vulnerable = new MediaPlayer(media2);
                    vulnerable.play();
                }
                counter ++;

            }

            case GAME_OVER -> {

                if(counter == 0) {
                    String audioFilePath = "src/pt/isec/pa/tinypac/ui/gui/resources/sounds/Death.mp3";
                    File audioFile = new File(audioFilePath);
                    String audioFileUri = audioFile.toURI().toString();

                    media3 = new Media(audioFileUri);
                    death = new MediaPlayer(media3);
                    death.play();
                }
                counter ++;

                this.setText("YOU LOSE - PRESS KEY TO CONTINUE");

            }

            case GAME_WIN -> {
                this.setText("YOU WIN - PRESS KEY TO CONTINUE");

            }

            default -> {
                counter = 0;
                this.setText("PAC MAN");

            }
        }

    }

}
