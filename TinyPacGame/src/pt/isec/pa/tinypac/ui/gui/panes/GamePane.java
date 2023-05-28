package pt.isec.pa.tinypac.ui.gui.panes;

import javafx.application.Platform;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.control.Label;
import javafx.scene.effect.DropShadow;
import javafx.scene.effect.GaussianBlur;
import javafx.scene.input.KeyCode;
import javafx.scene.input.KeyEvent;
import javafx.scene.layout.*;
import javafx.scene.paint.Color;
import javafx.scene.paint.CycleMethod;
import javafx.scene.paint.LinearGradient;
import javafx.scene.paint.Stop;
import javafx.stage.Stage;
import pt.isec.pa.tinypac.model.Controller;
import pt.isec.pa.tinypac.model.fsm.TinyPacState;
import pt.isec.pa.tinypac.ui.gui.components.FooterGamePage;
import pt.isec.pa.tinypac.ui.gui.components.StateInfoHeader;


/**
 * Página onde é mostrado o jogo
 */



public class GamePane extends StackPane {

    private  Stage mainStage;
    private FooterGamePage footer;
    private  Controller manager;
    private LinearGradient gradient;
    private BackgroundFill fill;
    private Background background;
    private Stop[] limits;
    private  Label StateInfos;

    private GridPane maze;

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
                new Stop(1, Color.BLACK)
        };

        gradient = new LinearGradient(2, 0, 2, 1, true, CycleMethod.NO_CYCLE, limits);
        fill = new BackgroundFill(gradient, CornerRadii.EMPTY, Insets.EMPTY);
        background = new Background(fill);


        this.setBackground(background);
        this.setBackground(background);


        StateInfos = new StateInfoHeader(mainStage,manager);
        footer = new FooterGamePage(mainStage, manager);
        StateInfos.setTranslateY(60);

        maze = new MazePane(mainStage,manager);

        maze.setPrefHeight(5000);
        maze.setPrefWidth(5000);
        this.getChildren().addAll(StateInfos,maze,footer);

        StackPane.setAlignment(StateInfos,Pos.TOP_CENTER);
        StackPane.setAlignment(footer,Pos.BOTTOM_CENTER);
        StackPane.setAlignment(maze,Pos.CENTER);

        DropShadow shadow = new DropShadow();
        maze.setEffect(shadow);

    }

    private void registerHandlers() {

        manager.addPropertyChangeListener(Controller.PROP_TOP5, evt -> {

            Platform.runLater(new Runnable() {
                @Override
                public void run() {
                    try {
                        if(manager.getState() == TinyPacState.GAME_OVER || manager.getState() == TinyPacState.GAME_WIN)
                        update();
                    }catch (Exception e){
                        System.out.println("error opening new map");
                    }
                }
            });

        });

        mainStage.getScene().addEventFilter( KeyEvent.KEY_PRESSED, keyEvent -> {

            if(keyEvent.getCode()==KeyCode.ESCAPE){

                manager.pause();
                new ModalPause(manager,mainStage,this);
                GaussianBlur blur = new GaussianBlur(10);
                this.setEffect(blur);

            }else if(keyEvent.getCode().equals(KeyCode.UP)){

                manager.keyPress(1);

            }else if(keyEvent.getCode().equals(KeyCode.DOWN)){

                manager.keyPress(2);

            }else if(keyEvent.getCode().equals(KeyCode.LEFT)){

                manager.keyPress(3);

            }else if(keyEvent.getCode().equals(KeyCode.RIGHT)){

                manager.keyPress(4);

            }

        });

    }


    private void update() {

        if(manager.verifyLeaderBoard()){
            new ModalInputField(manager,mainStage,this, manager.getState().toString());
            GaussianBlur blur = new GaussianBlur(10);
            this.setEffect(blur);
        }
    }

}
