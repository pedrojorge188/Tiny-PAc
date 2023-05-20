package pt.isec.pa.tinypac.ui.gui.panes;

import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.control.Label;
import javafx.scene.layout.Background;
import javafx.scene.layout.BackgroundFill;
import javafx.scene.layout.CornerRadii;
import javafx.scene.layout.StackPane;
import javafx.scene.paint.Color;
import javafx.scene.paint.CycleMethod;
import javafx.scene.paint.LinearGradient;
import javafx.scene.paint.Stop;
import javafx.stage.Stage;
import pt.isec.pa.tinypac.model.Controller;
import pt.isec.pa.tinypac.ui.gui.components.MainBtn;

/**
 * Pane representativa do TOP 5
 */

public class LeaderBoardPane extends StackPane {

    private Controller manager;
    private Stage mainStage;
    private LinearGradient gradient;
    private BackgroundFill fill;
    private Background background;
    private Label title;
    private MainBtn exitBtn;
    private Stop[] limits;

    public LeaderBoardPane(Stage mainStage, Controller manager) {

        this.mainStage = mainStage;
        this.manager = manager;

        createViews();
        registerHandlers();
        update();

    }


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

        title = new Label("TOP 5");
        title.setStyle("-fx-text-fill: WHITE;" +
                " -fx-font-size: 35px;" +
                " -fx-font-family: 'Arial Black'"
        );
        title.setTranslateY(20);
        title.setAlignment(Pos.TOP_CENTER);
        exitBtn = new MainBtn("BACK");
        exitBtn.setTranslateY(-20);
        this.getChildren().addAll(title,exitBtn);

        StackPane.setAlignment(title,Pos.TOP_CENTER);
        StackPane.setAlignment(exitBtn, Pos.BOTTOM_CENTER);
    }

    private void registerHandlers() {

        manager.addPropertyChangeListener(Controller.PROP_TOP5, evt -> {
            update();
        });

        exitBtn.setOnAction(actionEvent -> {

            RootPane root = new RootPane(mainStage,manager);
            this.getChildren().clear();
            this.getChildren().add(root);

        });

    }

    private void update(){

    }

}
