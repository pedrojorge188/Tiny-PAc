package pt.isec.pa.tinypac.ui.gui.panes;

import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.*;
import javafx.scene.paint.Color;
import javafx.scene.paint.CycleMethod;
import javafx.scene.paint.LinearGradient;
import javafx.scene.paint.Stop;
import javafx.scene.text.Text;
import javafx.stage.Stage;
import pt.isec.pa.tinypac.model.Controller;
import pt.isec.pa.tinypac.ui.gui.components.MainBtn;

/**
 * Pane representativa do TOP 5
 */

public class CreditsPane extends StackPane {

    private static String logo_path = "pt/isec/pa/tinypac/ui/gui/resources/img/ISEC.png";
    private Controller manager;
    private Stage mainStage;
    private LinearGradient gradient;
    private BackgroundFill fill;
    private Background background;
    private Text year, name, curso, uc, number;
    private MainBtn exitBtn;
    private Stop[] limits;
    private VBox vbox;

    public CreditsPane(Stage mainStage, Controller manager) {

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

        exitBtn = new MainBtn("BACK");
        exitBtn.setTranslateY(-20);

        name = new Text("Nome:  Pedro Rodrigues Jorge");
        name.setStyle("-fx-fill: #ffffff;" +
                " -fx-font-size: 15px;"  );

        uc = new Text("Unidade Curricular: Programação Avançada");
        uc.setStyle("-fx-fill: #ffffff;" +
                " -fx-font-size: 15px;" );

        curso = new Text("Licenciatura em Engenharia Informática");
        curso.setStyle("-fx-fill: #ffffff;" +
                " -fx-font-size: 17px;" +
                " -fx-font-family: 'Arial Black';" +
                " -fx-font-weight: bold;");

        number = new Text("N: a2021142041");
        number.setStyle("-fx-fill: #ffffff;" +
                " -fx-font-size: 15px;" );


        year = new Text("2022/2023");

        year.setStyle("-fx-fill: #eae9e9;" +
                " -fx-font-size: 12px;" );


        Image logo = new Image(logo_path);
        ImageView imageView = new ImageView(logo);
        imageView.setFitWidth(220);
        imageView.setFitHeight(220);
        imageView.setPreserveRatio(true);

        vbox = new VBox(imageView, curso, uc, name, number, year);
        vbox.setAlignment(Pos.TOP_CENTER);
        vbox.setSpacing(30);
        vbox.setPadding(new Insets(20));
        vbox.setFillWidth(true);
        vbox.setMaxWidth(600);
        vbox.setMaxHeight(250);
        vbox.setStyle("-fx-background-color:  #032672;"
                + "-fx-border-color: #ffffff;"
                + "-fx-border-radius: 3px;"
                + "-fx-border-width: 2px;"
                + "-fx-effect: dropshadow(three-pass-box, rgba(0,0,0,0.1), 5, 0.0, 0, 1);");


        this.getChildren().addAll(vbox,exitBtn);
        StackPane.setAlignment(exitBtn, Pos.BOTTOM_CENTER);
        StackPane.setAlignment(vbox, Pos.CENTER);
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
