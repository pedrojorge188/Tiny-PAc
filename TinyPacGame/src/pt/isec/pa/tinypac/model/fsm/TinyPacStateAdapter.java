package pt.isec.pa.tinypac.model.fsm;

import pt.isec.pa.tinypac.model.data.game.GameManager;
import pt.isec.pa.tinypac.utils.Messages;

import java.io.FileOutputStream;
import java.io.ObjectOutputStream;

/**
 * Classe do adaptavel aos estados gestores do jogo
 */

public abstract class TinyPacStateAdapter implements ITinyPacState{

    protected static GameManager game;
    protected static int direction = 0;
    protected TinyPacContext context;

    public TinyPacStateAdapter(TinyPacContext context, GameManager game){
        TinyPacStateAdapter.game = game;
        this.context = context;
    }

    protected void changeState(TinyPacState newState){
        context.changeState(ITinyPacState.createState(newState,this.context,game));
    }

    private void save(){

        try(FileOutputStream file = new FileOutputStream("files/save.dat");
            ObjectOutputStream oos = new ObjectOutputStream(file);){

            oos.writeObject(game);
            Messages.getInstance().clearLogs();
            Messages.getInstance().addLog("ESTADO-> START_GAME");

        }catch(Exception e){
            e.printStackTrace();
        }

    }

    @Override
    public abstract void action();

    @Override
    public boolean keyPress(int direction) {
        GameManager test = new GameManager(game);

        if(direction == 5){
            this.save();
        }

        if(test.movePacman(direction)){

            TinyPacStateAdapter.direction = direction;

        }
        return true;
    }

    @Override
    public boolean getPacman() {
        return false;
    }

    @Override
    public boolean pacManFinish() {
        return false;
    }

    @Override
    public boolean pacManBuff() {
        return false;
    }

    @Override
    public boolean timeout() {
        return false;
    }
}