package pt.isec.pa.tinypac.model.data.Balls;

public class BigBall extends Ball{

    public BigBall(int x, int y){
        super(x,y);
        setPoints(10);
    }

    @Override
    public String toString() {
        return "BigBall{" +
                "points=" + points +
                ", x=" + x +
                ", y=" + y +
                '}';
    }
}
