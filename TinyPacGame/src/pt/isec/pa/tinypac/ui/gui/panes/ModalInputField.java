package pt.isec.pa.tinypac.ui.gui.panes;

import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.layout.*;
import javafx.scene.paint.Color;
import javafx.scene.paint.CycleMethod;
import javafx.scene.paint.LinearGradient;
import javafx.scene.paint.Stop;
import javafx.stage.Modality;
import javafx.stage.Stage;
import javafx.stage.StageStyle;
import pt.isec.pa.tinypac.model.Controller;
import pt.isec.pa.tinypac.ui.gui.components.MainBtn;

public class ModalInputField {



    private Controller manager;
    private Stage mainStage;
    private LinearGradient gradient;
    private BackgroundFill fill;
    private Background background;
    private Stop[] limits;
    private MainBtn btnSubmit;
    private Stage modalStage;
    TextField inputField;
    private Label label;
    private VBox vbox;
    private StackPane init;

    public ModalInputField(Controller controller, Stage mainStage, StackPane init, String title) {

        this.label = new Label(title);
        this.init = init;
        this.manager = controller;
        this.mainStage = mainStage;
        createViews();
        registerHandlers();
        update();

    }

    /**
     * Cria os componentes inicias deste pane
     */

    private void createViews() {

        modalStage = new Stage();
        modalStage.initStyle(StageStyle.UNDECORATED);

        modalStage.setResizable(false);
        modalStage.setMinHeight(200);
        modalStage.setMinWidth(200);
        modalStage.initModality(Modality.APPLICATION_MODAL);
        modalStage.initOwner(mainStage.getScene().getWindow());

        limits = new Stop[]{
                new Stop(0, Color.BLACK),
                new Stop(1, Color.BLUE)
        };

        gradient = new LinearGradient(0, 0, 2, 1, true, CycleMethod.NO_CYCLE, limits);
        fill = new BackgroundFill(gradient, CornerRadii.EMPTY, Insets.EMPTY);
        background = new Background(fill);

        label.setStyle("-fx-font-family: 'Arial Black'; -fx-text-fill: White; -fx-font-size: 15px");
        vbox = new VBox();

        inputField = new TextField();
        inputField.setStyle("-fx-background-color: #032672;" +
                " -fx-text-fill: white; " +
                "-fx-border-color: #CCCCCC; " +
                "-fx-border-radius: 5px; " +
                "-fx-padding: 5px; " +
                "-fx-font-size: 14px;");

        btnSubmit = new MainBtn("Exit");

        vbox.getChildren().addAll(label, inputField, btnSubmit);

        vbox.setSpacing(15);
        vbox.setPadding(new Insets(20));
        vbox.setAlignment(Pos.CENTER);
        vbox.setBackground(background);


        vbox.setStyle("-fx-border-width: 2px; -fx-border-color: WHITE;");

        Scene modalScene = new Scene(vbox, 300,
                250);

        modalStage.setScene(modalScene);

    }

    private void registerHandlers() {

        btnSubmit.setOnAction(e -> {
            String myName = inputField.getText();
            if(myName != null){
                manager.addToLeaderboard(myName);
                manager.disableGameRoles();
                modalStage.close();
                init.getChildren().add(new RootPane(mainStage,manager));
                init.setEffect(null);
            }
        });

    }

    private void update() {

        modalStage.show();

    }

}
