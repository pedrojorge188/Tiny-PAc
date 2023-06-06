package pt.isec.pa.tinypac.model;

import pt.isec.pa.tinypac.gameengine.IGameEngine;
import pt.isec.pa.tinypac.gameengine.IGameEngineEvolve;
import pt.isec.pa.tinypac.model.data.LeaderBoard;
import pt.isec.pa.tinypac.model.data.game.GameManager;
import pt.isec.pa.tinypac.model.data.ghost.Ghost;
import pt.isec.pa.tinypac.model.data.pacman.PacMan;
import pt.isec.pa.tinypac.model.fsm.TinyPacContext;
import pt.isec.pa.tinypac.model.fsm.TinyPacState;
import pt.isec.pa.tinypac.utils.Messages;

import java.beans.PropertyChangeListener;
import java.beans.PropertyChangeSupport;
import java.io.*;
import java.util.HashSet;

/**
 * Manager do projeto
 */

public class Controller implements IGameEngineEvolve {

    private TinyPacContext fsm;
    private GameManager game;
    private PropertyChangeSupport pcs;
    private static int direction_manager;
    private LeaderBoard leaderBoard;
    public static final String PROP_MAZE = "maze_property";
    public static final String PROP_GAME_INFO = "info_property";
    public static final String PROP_LOG = "logs_property";
    public static final String PROP_TOP5 = "leaderboard_property";

    public Controller() {

        game = new GameManager();
        this.fsm = new TinyPacContext(game);
        pcs = new PropertyChangeSupport(this);

        File fileO = new File("files/leaderboard.dat");

        try(FileInputStream file = new FileInputStream(fileO);
            ObjectInputStream ois = new ObjectInputStream(file);){

            leaderBoard = (LeaderBoard) ois.readObject();

        }catch(Exception e){

            leaderBoard = new LeaderBoard();

        }
    }


    public void addPropertyChangeListener(String proprety, PropertyChangeListener listener){
        pcs.addPropertyChangeListener(proprety, listener);
    }

    @Override
    public void evolve(IGameEngine gameEngine, long currentTime) {

        pcs.firePropertyChange(PROP_MAZE,null,null);
        pcs.firePropertyChange(PROP_GAME_INFO,null,null);
        fsm.action();

    }

    /**
     * Verifica se existe algum jogo para restaurar
     * @return
     */

    public boolean verifyGameRestore(){
        File fileO = new File("files/save.dat");

        try(FileInputStream file = new FileInputStream(fileO);
            ObjectInputStream ois = new ObjectInputStream(file);){

            return true;

        }catch(Exception e){

            return false;
        }


    }

    /**
     * Verifica se podemos adicionar o jogador atual à leaderboard ou não
     * @return
     */
    public boolean verifyLeaderBoard(){

        return leaderBoard.verifyRequirements(game.getPoints());

    }

    /**
     * retorna o nome do jogador do top indicado no parametro
     * @param top
     * @return
     */
    public String top_name(int top){
       return leaderBoard.lead_name(top);
    }

    /**
     * Indica os pontos do jogador representado no top indicado no parametro de entrada
     * @param top
     * @return
     */

    public int top_score(int top){
        return leaderBoard.lead_score(top);
    }

    /**
     * Adicionar jogador ao top 5
     * @param name
     * @return
     */
    public boolean addToLeaderboard(String name){

        if(leaderBoard.addRequirements(name,game.getPoints())){

            try(FileOutputStream file = new FileOutputStream("files/leaderboard.dat");
                ObjectOutputStream oos = new ObjectOutputStream(file);){

                oos.writeObject(leaderBoard);
                Messages.getInstance().clearLogs();
                Messages.getInstance().addLog("LEADER BOARD CHANGED");

            }catch(Exception e){

                e.printStackTrace();
                return false;

            }finally {
                pcs.firePropertyChange(PROP_TOP5,null,null);
                return true;
            }

        }else{
            return false;
        }

    }

    public boolean saveGame(){

        /**
         * Função que salva o jogo no ficheiro binario (c/serialization)
         */
        try(FileOutputStream file = new FileOutputStream("files/save.dat");
            ObjectOutputStream oos = new ObjectOutputStream(file);){

            oos.writeObject(game);
            Messages.getInstance().clearLogs();
            Messages.getInstance().addLog("GAME SAVED");

        }catch(Exception e){

            e.printStackTrace();
            return false;

        }finally {
            pcs.firePropertyChange(PROP_LOG,null,null);
        }

        return true;
    }

    /**
     * Elimina o ficheiro do jogo passado, caso o jogador chame esta função significa que este nao deseja restaurar o jogo passado
     * @return
     */
    public boolean deleteCacheFiles(){

        File fileO = new File("files/save.dat");
        try(FileInputStream file = new FileInputStream(fileO);
            ObjectInputStream ois = new ObjectInputStream(file);){


        }catch(Exception e){

            return false;
        }

        fileO.delete();

        return true;

    }

    /**
     * Retaurar o jogo passado
     * @return
     */
    public boolean restoreGame(){

        File fileO = new File("files/save.dat");

        try(FileInputStream file = new FileInputStream(fileO);
            ObjectInputStream ois = new ObjectInputStream(file);){

            game = (GameManager) ois.readObject();
            fsm.replaceGameManager(game);
            fsm.disableFsm();

            Messages.getInstance().clearLogs();
            Messages.getInstance().addLog("GAME RESTORE");

        }catch(Exception e){


            this.game = new GameManager();
            fsm.replaceGameManager(game);
            return false;

        }finally {
            pcs.firePropertyChange(PROP_MAZE,null,null);
            pcs.firePropertyChange(PROP_LOG,null,null);
            pcs.firePropertyChange(PROP_GAME_INFO, null , null);
        }

        fileO.delete();
        return true;
    }

    /**
     * Chama a ação da máquina de estados (keyPress)
     * @param direction
     * @return
     */
    public boolean keyPress(int direction){
        pcs.firePropertyChange(PROP_TOP5,null,null);
        pcs.firePropertyChange(PROP_LOG,null,null);
        direction_manager = direction;
        return fsm.keyPress(direction);
    }

    /**
     * Recebe a direção para onde o pacman esta a olhar (isto é util para rodar o pacman na interface gráfica)
     * @return
     */
    public int getPacDirection(){
        return direction_manager;
    }

    /**
     * Chama a ação da maquina de estados (pause)
     * @return
     */
    public boolean pause(){

        pcs.firePropertyChange(PROP_LOG,null,null);
        return fsm.pause();
    }

    /**
     * Chama a ação da maquina de estados (resume)
     * @return
     */
    public boolean resume(){

        pcs.firePropertyChange(PROP_LOG,null,null);
        return  fsm.resume();
    }

    /**
     * recebe o estado de jogo atual (startGame, movePacman, moveGhosts, vulnerableGhosts, pause, result ..)
     * @return
     */
    public TinyPacState getState(){
        return fsm.getState();
    }

    /**
     * Recebe a vida do pacman
     * @return
     */
    public int getPacmanLife(){
        return fsm.getPacManLife();
    }

    /**
     * linhas do tabuleiro do jogo
     * @return
     */
    public int getGameRows(){
        return fsm.getMazeRows();
    }

    /**
     * Colunas do tabuleiro do jogo
     * @return
     */
    public int getGameCols(){
        return fsm.getMazeCols();
    }

    /**
     * Estado da fruta
     * @return
     */
    public boolean getFruit(){
        return fsm.getFruit();
    }

    /**
     * Recebe o tabuleiro com os elementos do jogo
     * @return
     */
    public char[][] getMaze(){
        return fsm.getMaze();
    }

    /**
     * Recebe uma cópia do pacman
     * @return
     */
    public PacMan getPacmanCPY(){
        return fsm.getPacman();
    }

    /**
     * Recebe uma copia dos fantasmas
     * @return
     */
    public HashSet<Ghost> getGhostCPY(){
        return fsm.getGhosts();
    }

    /**
     * Rcebe o nivel do jogo
     * @return
     */
    public int getLevel(){
        return fsm.getLevel();
    }

    /**
     * Recebe os pontos do jogador
     * @return
     */
    public int getPoints(){
       return fsm.getPoints();
    }

    /**
     *
     * verifica o estado de vulnerabilidade (isto vai permitir mostrar o tempo na GUI)
     * @return
     */
    public int getVulnerable(){
        return fsm.getVulnerable();
    }

    /**
     * Coloca a maquina de estados default
     */
    public void resetFsm(){
        fsm.disableFsm();
    }

    /**
     * Reseta todos os atributos do jogo (maquina de estados reiniciada)
     */
    public void disableGameRoles(){

        game = new GameManager();
        fsm.replaceGameManager(game);
        fsm.disableFsm();

    }

}
