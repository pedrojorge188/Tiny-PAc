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
public class ModalRestore {

    private Controller manager;
    private Stage mainStage;
    private LinearGradient gradient;
    private BackgroundFill fill;
    private Background background;
    private Stop[] limits;
    private String content,btn1Content, btn2Content;
    private MainBtn btn1, btn2;
    private Stage modalStage;
    private Label label;
    private VBox vbox;
    private HBox hbox;
    private StackPane init;



    public ModalRestore(String txt, String btn1_txt, String btn2_txt , Controller controller, Stage mainStage, StackPane init) {

        this.init = init;
        content = txt; this.btn1Content = btn1_txt; this.btn2Content = btn2_txt;
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

        label = new Label(content);
        label.setStyle("-fx-font-family: 'Arial Black'; -fx-text-fill: White; -fx-font-size: 15px");
        vbox = new VBox();
        hbox = new HBox();

        btn1 = new MainBtn(btn1Content);
        btn2 = new MainBtn(btn2Content);

        hbox.getChildren().addAll(btn1, btn2);
        hbox.setSpacing(15);
        hbox.setStyle("-fx-padding: 15px");
        vbox.getChildren().addAll(label, hbox);

        vbox.setSpacing(15);
        vbox.setAlignment(Pos.CENTER);
        vbox.setBackground(background);


        vbox.setStyle("-fx-border-width: 2px; -fx-border-color: WHITE;");

        Scene modalScene = new Scene(vbox, 250,
                200);
        modalStage.setScene(modalScene);

    }

    private void registerHandlers() {

        btn1.setOnAction(actionEvent -> {
            manager.restoreGame();
            GamePane gamePage = new  GamePane(mainStage,manager);
            init.getChildren().add(gamePage);
            modalStage.close();
            init.setEffect(null);
        });

        btn2.setOnAction(actionEvent -> {

            manager.deleteCacheFiles();
            GamePane gamePage = new  GamePane(mainStage,manager);
            init.getChildren().add(gamePage);
            modalStage.close();
            init.setEffect(null);

        });

        update();

    }

    private void update() {

        modalStage.show();

    }



}
