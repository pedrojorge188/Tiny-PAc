package pt.isec.pa.tinypac.ui.gui.log;

import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.ListView;
import javafx.scene.layout.BorderPane;
import javafx.stage.Screen;
import javafx.stage.Stage;
import javafx.stage.WindowEvent;
import pt.isec.pa.tinypac.model.Controller;
import pt.isec.pa.tinypac.utils.Messages;


/**
 * Stage para debug
 */

public class ModelStage extends Stage {
    Messages model;
    Controller manager;
    public ModelStage(Stage stage, Controller manager) {

        this.model = Messages.getInstance();
        this.manager = manager;

        setScene(new Scene(new LogPane(),500,500));
        setX(Screen.getPrimary().getVisualBounds().getWidth()-500);
        setY(0);

        stage.addEventFilter(
                WindowEvent.WINDOW_CLOSE_REQUEST,
                windowEvent -> close()
        );

        show();
    }

    private class LogPane extends BorderPane {
        Button btnRefresh;
        ListView<String> lstLogs;
        private LogPane() {
            createViews();
            registerHandlers();
            update();
        }

        private void createViews() {
            lstLogs = new ListView<>();
            setCenter(lstLogs);
        }

        private void registerHandlers() {

            manager.addPropertyChangeListener(Controller.PROP_GAME, evt -> {
                update();
            });

        }

        private void update() {

            lstLogs.getItems().addAll(model.listLogs());
        }
    }
}