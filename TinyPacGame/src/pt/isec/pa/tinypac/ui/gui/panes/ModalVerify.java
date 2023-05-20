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
public class ModalVerify {

    private Controller manager;
    private Stage mainStage;
    private LinearGradient gradient;
    private BackgroundFill fill;
    private Background background;
    private Stop[] limits;
    private MainBtn btn1, btn2;
    private Stage modalStage;
    private Label label;
    private VBox vbox;
    private HBox hbox;
    private StackPane init;


    public ModalVerify(Controller controller, Stage mainStage, StackPane init) {

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

        label = new Label("DO YOU WANT TO LEAVE!?");
        label.setStyle("-fx-font-family: 'Arial Black'; -fx-text-fill: White; -fx-font-size: 15px");
        vbox = new VBox();

        btn1 = new MainBtn("YES");
        btn2 = new MainBtn("NO");
        hbox = new HBox(btn1,btn2);
        hbox.setSpacing(10);
        hbox.setStyle("-fx-padding: 20px");

        vbox.getChildren().addAll(label,hbox);
        vbox.setSpacing(20);
        vbox.setAlignment(Pos.CENTER);
        vbox.setBackground(background);


        vbox.setStyle("-fx-border-width: 2px; -fx-border-color: WHITE;");

        Scene modalScene = new Scene(vbox, 300,
                250);

        modalStage.setScene(modalScene);

    }

    private void registerHandlers() {

        btn1.setOnAction(actionEvent -> {
            manager.disableGameRoles();
            modalStage.close();
            init.getChildren().add(new RootPane(mainStage,manager));
            init.setEffect(null);

        });

        btn2.setOnAction(actionEvent -> {
            modalStage.close();
            manager.resume();
            init.setEffect(null);
        });

        update();

    }

    private void update() {

        modalStage.show();

    }



}
