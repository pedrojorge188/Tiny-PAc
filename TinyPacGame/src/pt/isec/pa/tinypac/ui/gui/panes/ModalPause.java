package pt.isec.pa.tinypac.ui.gui.panes;

import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.Label;
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


/**
 * Componente default para modal do jogo, pode ser utilizada varias vezes
 */
public class ModalPause {

    private Controller manager;
    private Stage mainStage;
    private LinearGradient gradient;
    private BackgroundFill fill;
    private Background background;
    private Stop[] limits;
    private MainBtn btn1, btn2, btn3;
    private Stage modalStage;
    private Label label;
    private VBox vbox;
    private HBox hbox;
    private Scene modalScene;
    private StackPane init;


    public ModalPause(Controller controller, Stage mainStage, StackPane init) {

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
        modalStage.initOwner(mainStage);

        limits = new Stop[]{
                new Stop(0, Color.BLACK),
                new Stop(1, Color.BLUE)
        };

        gradient = new LinearGradient(0, 0, 2, 1, true, CycleMethod.NO_CYCLE, limits);
        fill = new BackgroundFill(gradient, CornerRadii.EMPTY, Insets.EMPTY);
        background = new Background(fill);

        label = new Label("GAME PAUSED");
        label.setStyle("-fx-font-family: 'Arial Black'; -fx-text-fill: White; -fx-font-size: 15px");
        vbox = new VBox();

        btn1 = new MainBtn("PLAY");
        btn2 = new MainBtn("SAVE");
        btn3 = new MainBtn("EXIT");

        vbox.getChildren().addAll(label, btn1, btn2, btn3);

        vbox.setSpacing(15);
        vbox.setAlignment(Pos.CENTER);
        vbox.setBackground(background);


        vbox.setStyle("-fx-border-width: 2px; -fx-border-color: WHITE;");

        Scene modalScene = new Scene(vbox, 300,
                250);

        modalStage.setScene(modalScene);

    }

    private void registerHandlers() {

        btn1.setOnAction(actionEvent -> {
            manager.resume();
            modalStage.close();
            init.setEffect(null);
        });

        btn2.setOnAction(actionEvent -> {
            manager.saveGame();
            manager.resume();
            modalStage.close();
            init.setEffect(null);
        });

        btn3.setOnAction(actionEvent -> {
            manager.disableGameRoles();
            init.getChildren().add(new RootPane(mainStage,manager));
            modalStage.close();
            init.setEffect(null);
        });

        update();

    }

    private void update() {

        modalStage.show();

    }



}
