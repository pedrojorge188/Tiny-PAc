package pt.isec.pa.tinypac.model.data.Fruit;

/**
 * Classe Fruit:
 * Gere as instancias de frutas
 */

public class Fruit implements IFruit{

    private int points;
    private int time;
    private Boolean active;
    private int x,y;

    public Fruit(int x, int y) {
        this.points = 0;
        this.time = TIME;
        this.active = false;
        this.x = x;
        this.y = y;
    }

    public Fruit(Fruit _new, int x, int y) {
        this.points = _new.points + POINTS;
        this.time = TIME;
        this.active = false;
        this.x = x;
        this.y = y;
    }

    public int getPoints() {
        return this.points;
    }

    public  void setPoints(){
        this.points += POINTS;
    }

    public int getTime() {
        return this.time;
    }

    public void setActive(Boolean active) {
        this.active = active;
    }

    public Boolean getStatus(){
        return active;
    }

    public void setX(int x) {
        this.x = x;
    }

    public void setY(int y) {
        this.y = y;
    }

    public int getX() {
        return x;
    }

    public int getY() {
        return y;
    }

    @Override
    public String toString() {
        return "Fruit {" +
                "points=" + points +
                "\ttime=" + time +
                "\tactive=" + active +
                '}';
    }
}
