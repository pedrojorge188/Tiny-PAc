package pt.isec.pa.tinypac.model.data.elements;

import pt.isec.pa.tinypac.model.data.IMazeElement;

public class WrapZone implements IMazeElement {

    private static int id_counter = 1;
    private int id;
    private int x,y;

    private static int id_generator() {
        return id_counter++;
    }

    public WrapZone(){
        this.id = id_generator();
        x = 0;
        y = 0;

    }

    public WrapZone(int x, int y){
        this.id = id_generator();
        this.x = x;
        this.y = y;
    }

    public int getId() {
        return id;
    }

    public int getY(){
        return y;
    }

    public int getX(){
        return x;
    }


    @Override
    public char getSymbol() {
        return 'W';
    }
}
