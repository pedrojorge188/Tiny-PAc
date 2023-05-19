package pt.isec.pa.tinypac.ui.gui.panes;

import javafx.application.Platform;
import javafx.geometry.Pos;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.GridPane;
import javafx.scene.paint.Color;
import javafx.scene.shape.Circle;
import javafx.scene.shape.Ellipse;
import javafx.scene.shape.Rectangle;
import javafx.scene.shape.Shape;
import javafx.stage.Stage;
import pt.isec.pa.tinypac.model.Controller;
import pt.isec.pa.tinypac.model.data.ghost.Ghost;

public class MazePane extends GridPane {

    private static final int CELL_SIZE = 20 ;

    private final Controller manager;
    private Stage mainStage;

    public MazePane(Stage mainStage, Controller manager){

        super();
        this.mainStage = mainStage;
        this.manager = manager;
        createViews();
        registerHandlers();
        update();
    }


    private void createViews() {

        this.setAlignment(Pos.CENTER);
        this.setStyle("-fx-padding: 10px");

    }
    private void registerHandlers() {


        manager.addPropertyChangeListener(Controller.PROP_MAZE, evt -> {

            Platform.runLater(new Runnable() {
                @Override
                public void run() {
                    update();
                }
            });

        });

    }

    private void update() {
        this.getChildren().clear();

        char [][] maze = manager.getMaze();


        for (int row = 0; row < manager.getGameRows(); row++) {
            for (int col = 0; col < manager.getGameCols(); col++) {

                Shape cellShape = new Rectangle(CELL_SIZE, CELL_SIZE);
                Color cellColor = Color.BLACK;

                switch (maze[row][col]){

                    case 'M' -> {
                        cellShape = new Ellipse(2,2);
                        cellColor = Color.YELLOWGREEN;
                    }
                    case 'W' -> {

                        Image image = new Image("pt/isec/pa/tinypac/ui/gui/resources/img/portal.png");
                        ImageView imageView = new ImageView(image);
                        imageView.setFitHeight(17);
                        imageView.setFitWidth(17);
                        cellShape = new Rectangle(17,17);
                        cellShape.setClip(imageView);
                        cellColor = Color.WHITE;

                    }
                    case 'x' -> {
                        cellColor = Color.DARKBLUE;
                    }

                    case 'p' -> { cellShape = new Ellipse(1,1); cellColor = Color.RED; }
                    case 'o' -> {cellShape = new Circle(3,Color.LIGHTYELLOW); cellColor = Color.LIGHTYELLOW;}
                    case 'O' -> {cellShape = new Circle(6,Color.LIGHTYELLOW); cellColor = Color.LIGHTYELLOW;}
                    case 'F' -> {

                        Image image = new Image("pt/isec/pa/tinypac/ui/gui/resources/img/fruit.png");
                        ImageView imageView = new ImageView(image);
                        imageView.setFitHeight(16);
                        imageView.setFitWidth(16);

                        if (manager.getFruit()){
                            cellShape = new Rectangle(16,16);
                            cellShape.setClip(imageView);
                            cellColor = Color.RED;
                        }
                    }
                }
                setRowIndex(cellShape, row);
                setColumnIndex(cellShape, col);
                this.getChildren().add(cellShape);
                cellShape.setFill(cellColor);
            }
        }

        for(Ghost e : manager.getGhostCPY()){

            Image image = new Image("pt/isec/pa/tinypac/ui/gui/resources/img/ghost.png");
            ImageView imageView = new ImageView(image);
            imageView.setFitHeight(16);
            imageView.setFitWidth(16);

            Rectangle cellShape = new Rectangle(16, 16);
            cellShape.setClip(imageView);

            Color cellColor = Color.RED;

            if(e.getVulnerability()){

                cellColor = Color.CYAN;

            }else if(e.getSymbol() == 'I'){

                cellColor = Color.ORANGERED;

            }else if(e.getSymbol() == 'B'){

                cellColor = Color.ORANGE;

            }else if(e.getSymbol() == 'C'){

                cellColor = Color.GREEN;

            }else if(e.getSymbol() == 'K'){

                cellColor = Color.PINK;

            }



            setRowIndex(cellShape, e.getY());
            setColumnIndex(cellShape, e.getX());
            this.getChildren().add(cellShape);

            cellShape.setFill(cellColor);

        }

        Image image = new Image("pt/isec/pa/tinypac/ui/gui/resources/img/pacman.png");
        ImageView imageView = new ImageView(image);
        imageView.setFitHeight(16);
        imageView.setFitWidth(16);


        if(manager.getPacDirection() == 1) {
            imageView.setRotate(270);

        }else if(manager.getPacDirection() == 2) {
            imageView.setRotate(90);

        }else if(manager.getPacDirection() == 3) {
            imageView.setRotate(180);

        }else if(manager.getPacDirection() == 4) {
            imageView.setRotate(0);
        }


        Rectangle cellShape = new Rectangle(16, 16);
        cellShape.setClip(imageView);


        setRowIndex(cellShape, manager.getPacmanCPY().getY());
        setColumnIndex(cellShape, manager.getPacmanCPY().getX());
        this.getChildren().add(cellShape);

        cellShape.setFill(Color.YELLOW);


    }

}