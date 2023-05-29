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
    private Label title, top1_name, top1_score ,
            top2_name, top2_score,
            top3_name, top3_score,
            top4_name, top4_score,
            top5_name, top5_score;
    private MainBtn exitBtn;
    private Stop[] limits;
    private VBox main_content;
    private HBox top1, top2, top3, top4, top5;

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
        top1_name = new Label(" ");
        top1_score = new Label(" ");
        top1_name.setStyle("-fx-text-fill: YELLOW");
        top1_score.setStyle("-fx-text-fill: YELLOW");
        top1 = new HBox(top1_name,top1_score);
        top1.setPadding(new Insets(20));
        top1.setAlignment(Pos.CENTER);
        top1.setStyle(
                " -fx-font-size: 26px;" +
                " -fx-font-family: 'Arial Black'"
        );
        top2_name = new Label(" ");
        top2_score = new Label(" ");
        top2_name.setStyle("-fx-text-fill: WHITE");
        top2_score.setStyle("-fx-text-fill: WHITE");
        top2 = new HBox(top2_name,top2_score);
        top2.setPadding(new Insets(20));
        top2.setAlignment(Pos.CENTER);
        top2.setStyle(
                " -fx-font-size: 20px;" +
                        " -fx-font-family: 'Arial Black'"
        );
        top3_name = new Label(" ");
        top3_score = new Label(" ");
        top3_name.setStyle("-fx-text-fill: WHITE");
        top3_score.setStyle("-fx-text-fill: WHITE");
        top3 = new HBox(top3_name,top3_score);
        top3.setPadding(new Insets(20));
        top3.setAlignment(Pos.CENTER);
        top3.setStyle(
                " -fx-font-size: 20px;" +
                        " -fx-font-family: 'Arial Black'"
        );
        top4_name = new Label(" ");
        top4_score = new Label(" ");
        top4_name.setStyle("-fx-text-fill: WHITE");
        top4_score.setStyle("-fx-text-fill: WHITE");
        top4 = new HBox(top4_name,top4_score);
        top4.setPadding(new Insets(20));
        top4.setAlignment(Pos.CENTER);
        top4.setStyle(
                " -fx-font-size: 20px;" +
                        " -fx-font-family: 'Arial Black'"
        );
        top5_name = new Label(" ");
        top5_score = new Label(" ");
        top5_name.setStyle("-fx-text-fill: WHITE");
        top5_score.setStyle("-fx-text-fill: WHITE");
        top5 = new HBox(top5_name,top5_score);
        top5.setPadding(new Insets(20));
        top5.setAlignment(Pos.CENTER);
        top5.setStyle(
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
        top1_name.setText("#1  \t"+manager.top_name(1));
        top1_score.setText("\t"+manager.top_score(1)+" Points ✰");
        top2_name.setText("#2  \t"+manager.top_name(2));
        top2_score.setText("\t"+manager.top_score(2)+" Points ✰");
        top3_name.setText("#3  \t"+manager.top_name(3));
        top3_score.setText("\t"+manager.top_score(3)+" Points ✰");
        top4_name.setText("#4  \t"+manager.top_name(4));
        top4_score.setText("\t"+manager.top_score(4)+" Points ✰");
        top5_name.setText("#5  \t"+manager.top_name(5));
        top5_score.setText("\t"+manager.top_score(5)+" Points ✰");
    }

}
