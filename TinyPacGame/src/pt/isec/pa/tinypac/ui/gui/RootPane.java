package pt.isec.pa.tinypac.ui.gui;


import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.control.Label;
import javafx.scene.layout.*;
import javafx.scene.paint.Color;
import javafx.scene.paint.CycleMethod;
import javafx.scene.paint.LinearGradient;
import javafx.scene.paint.Stop;
import pt.isec.pa.tinypac.ui.gui.graphicElements.MainBtn;

/**
 * Pane principal do javafx , este é o jogo è iniciado
 */
public class RootPane extends StackPane {

    private MainBtn btn1,btn2,btn3,btn4;
    private Label pageTitle;
    private LinearGradient gradient;
    private BackgroundFill fill;
    private Background background;
    private Stop[] limits;

    public RootPane() {

        createViews();
        registerHandlers();
        update();

    }

    /**
     * Cria os componentes inicias deste pane
     */

    private void createViews() {
        //page

        limits = new Stop[]{
                new Stop(0, Color.BLACK),
                new Stop(1, Color.BLUE)
        };
        gradient = new LinearGradient(0, 0, 2, 1, true, CycleMethod.NO_CYCLE, limits);
        fill = new BackgroundFill(gradient, CornerRadii.EMPTY, Insets.EMPTY);
        background = new Background(fill);
        this.setBackground(background);

        //items


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



    }

    private void update() {

    }
}
