package pt.isec.pa.tinypac.ui.gui.components;


import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.control.Label;
import javafx.scene.effect.GaussianBlur;
import javafx.scene.layout.*;
import javafx.scene.paint.Color;
import javafx.scene.paint.CycleMethod;
import javafx.scene.paint.LinearGradient;
import javafx.scene.paint.Stop;
import javafx.stage.Stage;
import pt.isec.pa.tinypac.model.Controller;

/**
 * Pane principal do javafx , este é o jogo è iniciado
 */
public class RootPane extends StackPane {

    private Controller manager;
    private Stage mainStage;
    private MainBtn btn1,btn2,btn3,btn4;
    private Label pageTitle;
    private LinearGradient gradient;
    private BackgroundFill fill;
    private Background background;
    private Stop[] limits;

    public RootPane(Stage mainStage, Controller manager) {

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


        btn1 = new MainBtn("Start Game");
        btn2 = new MainBtn("TOP 5");
        btn3 = new MainBtn("Credits");
        btn4 = new MainBtn("Exit");

        pageTitle = new Label("TINY-PAC GAME");
        pageTitle.setStyle("-fx-text-fill: white;" +
                " -fx-font-size: 50px;" +
                " -fx-font-family: 'Arial Black'"
        );

        //groups
        VBox vbox = new VBox(pageTitle,btn1,btn2,btn3,btn4);
        vbox.setSpacing(19);

        this.getChildren().addAll(vbox);

        vbox.setAlignment(Pos.CENTER);

    }

    private void registerHandlers() {

        btn1.setOnAction(actionEvent -> {

            if(manager.verifyGameRestore()){

                new ModalRestore("Restore last Game?","Yes", "NO", manager, mainStage, this);
                GaussianBlur blur = new GaussianBlur(10);
                this.setEffect(blur);

            }else{

                GamePane gamePage = new  GamePane(mainStage,manager);
                this.getChildren().add(gamePage);

            }

        });

        btn4.setOnAction(actionEvent -> {

            mainStage.close();
        });



        update();

    }

    private void update() {

    }
}
