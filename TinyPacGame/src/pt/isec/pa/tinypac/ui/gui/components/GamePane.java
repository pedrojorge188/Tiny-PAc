package pt.isec.pa.tinypac.ui.gui.components;

import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.control.Label;
import javafx.scene.effect.GaussianBlur;
import javafx.scene.input.KeyCode;
import javafx.scene.layout.*;
import javafx.scene.paint.Color;
import javafx.scene.paint.CycleMethod;
import javafx.scene.paint.LinearGradient;
import javafx.scene.paint.Stop;
import javafx.stage.Stage;
import pt.isec.pa.tinypac.model.Controller;


/**
 * Página onde é mostrado o jogo
 */
public class GamePane extends StackPane {

    private  Stage mainStage;
    private  Controller manager;
    private LinearGradient gradient;
    private BackgroundFill fill;
    private Background background;
    private Stop[] limits;
    private  Label status,StateInfos;

    public GamePane(Stage mainStage, Controller manager) {

        this.mainStage = mainStage;
        this.manager = manager;
        createViews();
        registerHandlers();
        update();

    }


    /**
     * Cria os componentes inicias deste pane
     */

    private void createViews() {

        limits = new Stop[]{
                new Stop(0, Color.BLACK),
                new Stop(1, Color.BLUE)
        };
        gradient = new LinearGradient(0, 0, 2, 1, true, CycleMethod.NO_CYCLE, limits);
        fill = new BackgroundFill(gradient, CornerRadii.EMPTY, Insets.EMPTY);
        background = new Background(fill);

        this.setBackground(background);
        this.setBackground(background);


        StateInfos = new Label(" ");
        StateInfos.setTranslateY(60);

        status = new Label();
        status.setPrefWidth(Integer.MAX_VALUE);
        status.setPadding(new Insets(20));
        status.setStyle("-fx-text-fill: white; -fx-background-color: #1f1e1e; -fx-font-size: 16; -fx-font-family: 'Courier New'; -fx-padding: 20");
        status.setBorder(
                new Border(
                        new BorderStroke(Color.BLACK,
                                BorderStrokeStyle.SOLID,
                                CornerRadii.EMPTY,
                                BorderWidths.DEFAULT)
                )
        );

        status.setAlignment(Pos.CENTER);

        this.getChildren().addAll(StateInfos,status);

        StackPane.setAlignment(StateInfos,Pos.TOP_CENTER);
        StackPane.setAlignment(status,Pos.BOTTOM_CENTER);

    }

    private void registerHandlers() {

        mainStage.getScene().setOnKeyPressed(keyEvent -> {

            update();

            if(keyEvent.getCode() == KeyCode.ESCAPE){

                manager.pause();
                new ModalPause(manager,mainStage,this);
                GaussianBlur blur = new GaussianBlur(10);
                this.setEffect(blur);

            }

        });


    }

    private void update() {

        switch (manager.getState()){
            case START_GAME -> {
                StateInfos.setText("PRESS KEY TO START!");
                StateInfos.setStyle("-fx-text-fill: YELLOW;" +
                        " -fx-font-size: 15px;" +
                        " -fx-font-family: 'Arial Black'"
                );
            }
            case VULNERABLE_GHOST -> {
                StateInfos.setText("VULNERABLE GHOSTS");
                StateInfos.setStyle("-fx-text-fill: CYAN;" +
                        " -fx-font-size: 15px;" +
                        " -fx-font-family: 'Arial Black'"
                );
            }
            case GAME_OVER -> {
                StateInfos.setText("GAME OVER");
                StateInfos.setStyle("-fx-text-fill: RED;" +
                        " -fx-font-size: 15px;" +
                        " -fx-font-family: 'Arial Black'"
                );
            }
            case GAME_WIN -> {
                StateInfos.setText("YOU WIN");
                StateInfos.setStyle("-fx-text-fill: GREEN;" +
                        " -fx-font-size: 15px;" +
                        " -fx-font-family: 'Arial Black'"
                );
            }
            default -> {
                StateInfos.setText(" ");
                StateInfos.setStyle("-fx-text-fill: GREEN;" +
                        " -fx-font-size: 15px;" +
                        " -fx-font-family: 'Arial Black'"
                );
            }
        }

        status.setText("Level: "+manager.getLevel()+"\tPacman Life:"+manager.getPacmanLife()+" ❤\t\tPoints:"+manager.getPoints());

    }

}
