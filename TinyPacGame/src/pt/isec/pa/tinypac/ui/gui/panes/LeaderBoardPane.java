package pt.isec.pa.tinypac.ui.gui.panes;

import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.control.Label;
import javafx.scene.layout.*;
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
    private Label title, top1, top2, top3, top4, top5;
    private MainBtn exitBtn;
    private Stop[] limits;
    private VBox main_content;

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

        top1 = new Label(" ");
        top1.setStyle("-fx-text-fill: WHITE;" +
                " -fx-font-size: 20px;" +
                " -fx-font-family: 'Arial Black'"
        );
        top2 = new Label(" ");
        top2.setStyle("-fx-text-fill: WHITE;" +
                " -fx-font-size: 20px;" +
                " -fx-font-family: 'Arial Black'"
        );
        top3 = new Label(" ");
        top3.setStyle("-fx-text-fill: WHITE;" +
                " -fx-font-size: 20px;" +
                " -fx-font-family: 'Arial Black'"
        );
        top4 = new Label(" ");
        top4.setStyle("-fx-text-fill: WHITE;" +
                " -fx-font-size: 20px;" +
                " -fx-font-family: 'Arial Black'"
        );
        top5 = new Label(" ");
        top5.setStyle("-fx-text-fill: WHITE;" +
                " -fx-font-size: 20px;" +
                " -fx-font-family: 'Arial Black'"
        );

        exitBtn = new MainBtn("BACK");
        exitBtn.setTranslateY(-20);

        main_content = new VBox(10);
        main_content.getChildren().addAll(top1,top2,top3,top4,top5);
        main_content.setPadding(new Insets(20));
        main_content.setAlignment(Pos.CENTER);
        main_content.setPadding(new Insets(20));
        main_content.setFillWidth(true);
        main_content.setMaxWidth(600);
        main_content.setMaxHeight(250);
        main_content.setStyle("-fx-background-color:  #032672;"
                + "-fx-border-color: #ffffff;"
                + "-fx-border-radius: 3px;"
                + "-fx-border-width: 2px;"
                + "-fx-effect: dropshadow(three-pass-box, rgba(0,0,0,0.1), 5, 0.0, 0, 1);");

        this.getChildren().addAll(title,main_content,exitBtn);
        StackPane.setAlignment(main_content, Pos.CENTER);
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
        top1.setText("#1\t\t"+manager.top_name(1)+"\t"+manager.top_score(1)+"\tpoints");
        top2.setText("#2\t\t"+manager.top_name(2)+"\t"+manager.top_score(2)+"\tpoints");
        top3.setText("#3\t\t"+manager.top_name(3)+"\t"+manager.top_score(3)+"\tpoints");
        top4.setText("#4\t\t"+manager.top_name(4)+"\t"+manager.top_score(4)+"\tpoints");
        top5.setText("#5\t\t"+manager.top_name(5)+"\t"+manager.top_score(5)+"\tpoints");
    }

}
