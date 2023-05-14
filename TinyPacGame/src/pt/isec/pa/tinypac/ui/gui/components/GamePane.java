package pt.isec.pa.tinypac.ui.gui.components;

import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.control.Label;
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
    private  Label pageTitle;

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


        pageTitle = new Label("GAME");
        pageTitle.setStyle("-fx-text-fill: white;" +
                " -fx-font-size: 20px;" +
                " -fx-font-family: 'Arial Black'"
        );

        //groups
        VBox vbox = new VBox(pageTitle);
        vbox.setSpacing(19);

        this.getChildren().addAll(vbox);

        vbox.setAlignment(Pos.TOP_CENTER);
    }

    private void registerHandlers() {

        mainStage.getScene().setOnKeyPressed(keyEvent -> {
            if(keyEvent.getCode() == KeyCode.ESCAPE){
                manager.pause();
                new ModalPause(manager,mainStage,this);

            }
        });

        update();

    }

    private void update() {

    }

}
