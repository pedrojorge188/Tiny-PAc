package pt.isec.pa.tinypac.model.data.Balls;


import java.io.Serializable;

/**
 * Class Ball:
 * Classe que gere as instancias dos alimentos do pacman
 */

public class Ball implements Serializable {

    static final long serialVersionUID = 1L;

    protected int points;
    protected int x,y;

    public Ball(int x, int y){
        points = 1;
        this.x = x;
        this.y = y;
    }

    protected void setPoints(int points) {
        this.points = points;
    }

    public int getPoints(){
        return points;
    }

    public int getX() {
        return x;
    }

    public int getY() {
        return y;
    }

    public void setX(int x) {
        this.x = x;
    }

    public void setY(int y) {
        this.y = y;
    }

    @Override
    public String toString() {
        return "Ball{" +
                "points=" + points +
                ", x=" + x +
                ", y=" + y +
                '}';
    }
}
