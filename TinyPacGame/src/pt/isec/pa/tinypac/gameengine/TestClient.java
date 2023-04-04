package pt.isec.pa.tinypac.gameengine;

public class TestClient implements IGameEngineEvolve {
    private int count = 0;

    @Override
    public void evolve(IGameEngine gameEngine, long currentTime) {
        System.out.printf("[%d] %d\n", currentTime, ++count);
        if (count >= 20) gameEngine.stop();
    }

    public int getCount() {
        return count;
    }
}
