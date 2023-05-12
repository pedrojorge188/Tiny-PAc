package pt.isec.pa.tinypac.ui.gui.graphicElements;

import javafx.geometry.Insets;
import javafx.scene.control.Button;
import javafx.scene.layout.Background;
import javafx.scene.layout.BackgroundFill;
import javafx.scene.layout.CornerRadii;
import javafx.scene.paint.Color;
import javafx.scene.shape.StrokeType;

public class MainBtn extends Button {

private double[] stm = {5.0, 2.0};
private StrokeType strokeType = StrokeType.CENTERED;

    private String bkg;
    private String bkc;

    public MainBtn(String name) {

        bkg = "-fx-background-color: #032672;";
        bkc = "-fx-text-fill: #ffff;";
        this.setText(name);

        createViews();
        registerHandlers();
        update();
    }

    private void createViews() {

        this.setBackground(new Background(new BackgroundFill(Color.WHITE, CornerRadii.EMPTY, Insets.EMPTY)));
        this.setStyle(bkg+
                 bkc+
                " -fx-font-size: 15px;" +
                " -fx-padding: 10px 20px;" +
                " -fx-background-radius: 6px;" +
                " -fx-border-color: #ffffff; -fx-border-width: 1px; -fx-border-radius: 5px;" +
                " -fx-font-family: 'Arial Black' "
        );
        this.setPrefWidth(150);
        this.setPrefHeight(50);

    }

    private void registerHandlers() {

        this.setOnMouseMoved(actionEvent -> {

            bkg = "-fx-background-color: #b0be05;";
            bkc = "-fx-text-fill: #032672;";
            update();

        });

        this.setOnMouseExited(actionEvent -> {

            bkg = "-fx-background-color: #032672;";
            bkc = "-fx-text-fill: #ffff;";
            update();

        });

    }

    private void update() {

        this.setStyle(bkg+
                bkc+
                " -fx-font-size: 15px;" +
                " -fx-padding: 10px 20px;" +
                " -fx-background-radius: 6px;" +
                " -fx-border-color: #ffffff; -fx-border-width: 1px; -fx-border-radius: 5px;" +
                " -fx-font-family: 'Arial Black' "
        );

    }

}
