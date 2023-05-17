package pt.isec.pa.tinypac.ui.gui.components;

import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.control.Label;
import javafx.scene.layout.*;
import javafx.scene.paint.Color;
import javafx.stage.Stage;
import pt.isec.pa.tinypac.model.Controller;

public class FooterGamePage extends Label {

    private Controller manager;
    private Stage mainStage;

    public FooterGamePage(Stage mainStage, Controller manager) {

        super();
        this.manager = manager;
        this.mainStage = mainStage;

        createViews();
        registerHandlers();
        update();
    }

    /**
     * Cria os componentes inicias deste pane
     */

    private void createViews() {

        this.setPrefWidth(Integer.MAX_VALUE);
        this.setPadding(new Insets(20));
        this.setStyle("-fx-text-fill: white;" +
                " -fx-background-color: #1f1e1e; " +
                "-fx-font-size: 16;" +
                " -fx-font-family: 'Courier New';" +
                " -fx-padding: 20");
        this.setBorder(
                new Border(
                        new BorderStroke(Color.BLACK,
                                BorderStrokeStyle.SOLID,
                                CornerRadii.EMPTY,
                                BorderWidths.DEFAULT)
                )
        );


        this.setAlignment(Pos.CENTER);
    }

    private void registerHandlers() {

        manager.addPropertyChangeListener(Controller.PROP_GAME, evt -> {
            update();
        });

    }

    private void update() {

        this.setText("Level: "+manager.getLevel()+"\tPacman Life:"+manager.getPacmanLife()+" ❤\t\tPoints:"+manager.getPoints());
    }

}
