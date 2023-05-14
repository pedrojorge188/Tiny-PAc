package pt.isec.pa.tinypac.ui.gui.components;

import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.control.Label;
import javafx.scene.effect.GaussianBlur;
import javafx.scene.effect.Glow;
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
    private  Label pageTitle,status,StateInfos;

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

        status = new Label();
        status.setPrefWidth(Integer.MAX_VALUE);
        status.setPadding(new Insets(20));
        status.setStyle("-fx-text-fill: white; -fx-background-color: #1f1e1e; -fx-font-size: 16; -fx-font-family: 'Courier New'");
        status.setBorder(
                new Border(
                        new BorderStroke(Color.BLACK,
                                BorderStrokeStyle.SOLID,
                                CornerRadii.EMPTY,
                                BorderWidths.DEFAULT)
                )
        );
        status.setAlignment(Pos.CENTER);
        Glow glow = new Glow();
        glow.setLevel(0.2);
        status.setEffect(glow);
        status.setLineSpacing(1);
        //groups
        VBox vbox = new VBox(pageTitle);
        vbox.setAlignment(Pos.TOP_CENTER);
        vbox.setSpacing(19);
        vbox.setTranslateY(60);

        this.getChildren().addAll(vbox,status);

        StackPane.setAlignment(vbox,Pos.TOP_CENTER);
        StackPane.setAlignment(status,Pos.BOTTOM_CENTER);

    }

    private void registerHandlers() {

        mainStage.getScene().setOnKeyPressed(keyEvent -> {

            if(keyEvent.getCode() == KeyCode.ESCAPE){

                manager.pause();
                new ModalPause(manager,mainStage,this);
                GaussianBlur blur = new GaussianBlur(10);
                this.setEffect(blur);

            }

            update();


        });


    }

    private void update() {

        status.setText("Level: "+manager.getLevel()+"\tPacman Life:"+manager.getPacmanLife()+" ❤\t\tPoints:"+manager.getPoints());

    }

}
