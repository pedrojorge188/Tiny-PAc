package pt.isec.pa.tinypac.model.data.pacman;

public class PacMan implements IPacMan{

    private int x, y;
    private int direction;
    private int movement_speed;
    private int lives;

    public PacMan(int x, int y){
        this.x = x;
        this.y = y;
        this.movement_speed = SPEED_DEFAULT;
        this.direction = 0;
        this.lives = N_LIVES;
    }

    public PacMan(PacMan pacman){
        this.x = pacman.x;
        this.y = pacman.y;
        this.movement_speed = pacman.movement_speed;
        this.direction = pacman.direction;
        this.lives = pacman.lives;
    }

    //Movement methods
    public void moveUp()  {
        this.y -= movement_speed;
    }

    public void moveDown() {
        this.y += movement_speed;
    }

    public void moveLeft()  {
        this.x -= movement_speed;
    }

    public void moveRight(){
        this.x += movement_speed;
    }

    public int getX() {
        return x;
    }

    public void setX(int x) {
        this.x = x;
    }

    public int getY() {
        return y;
    }

    public void setY(int y) {
        this.y = y;
    }

    public int getDirection() {
        return direction;
    }

    public void setDirection(int direction) {
        this.direction = direction;
    }

    public int getSpeed() {
        return movement_speed;
    }

    public void setMovement_speed(int movement_speed) {
        this.movement_speed = movement_speed;
    }

    public int getLives() {
        return lives;
    }

    public void removeLife(){
        lives--;
    }
    public void getDamage() {
        if(N_LIVES > 0)
            lives--;
    }

    @Override
    public String toString() {
        return "PacMan{" +
                "x=" + x +
                ", y=" + y +
                ", direction=" + direction +
                ", movement_speed=" + movement_speed +
                ", lives=" + lives +
                '}';
    }

    public void setLives(int lives) {
        this.lives = lives;
    }
}