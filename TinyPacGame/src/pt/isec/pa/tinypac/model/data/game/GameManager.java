package pt.isec.pa.tinypac.model.data.game;
import pt.isec.pa.tinypac.model.IConst;
import pt.isec.pa.tinypac.model.data.Balls.Ball;
import pt.isec.pa.tinypac.model.data.Balls.BigBall;
import pt.isec.pa.tinypac.model.data.Fruit.Fruit;
import pt.isec.pa.tinypac.model.data.Maze;
import pt.isec.pa.tinypac.model.data.elements.Void;
import pt.isec.pa.tinypac.model.data.elements.*;
import pt.isec.pa.tinypac.model.data.game.interfaces.GameConsts;
import pt.isec.pa.tinypac.model.data.ghost.*;
import pt.isec.pa.tinypac.model.data.pacman.PacMan;

import java.io.Serializable;
import java.util.HashSet;
import java.util.Set;

/**
 * Class GameManager:
 * Esta classe tem a função de gerir todos os elementos do jogo de maneira direnta, armazenado os fantasmas,
 * pacman e outros elementos necessários
 */

public class GameManager implements GameConsts , IConst, Serializable {

        private static int eat_to_fruit = 0;
        private int level;
        private int stage;
        private int points;
        private Maze current_maze;
        private Set<Ghost> ghost_list;
        private PacMan pacman;
        private Fruit fruit;
        private Set <Ball> balls_list;
        private Set <WrapZone> portals;

        public GameManager(){

            this.level = 1;
            this.stage = 1;
            this.points = 0;
            this.current_maze = GameLevel.getLevel(this.stage).getValue2();

            ghost_list = new HashSet<>();
            balls_list = new HashSet<>();
            portals = new HashSet<>();
            pacman = new PacMan(0,0);
            fruit = new Fruit(0,0);

        }

        /**
         * Construtor por cópia da class GameManager
         * -> Este construtor pode ser utilizado para testes e para verificações especificas utilizando a classe
         * sem comprometer os dados da instancia atual do jogo
         */

        public GameManager(GameManager g){

            this.level = g.level;
            this.stage = g.stage;
            this.points = g.getPoints();
            this.current_maze = g.current_maze;
            ghost_list = new HashSet<>(g.ghost_list);
            balls_list = new HashSet<>(g.balls_list);
            portals = new HashSet<>(g.portals);
            pacman = new PacMan(g.pacman);
            fruit = g.fruit;

        }

        /**
         * Função que tem o objetivo de corrigir algum erro que possa haver na sobreposição de objetos no contexto do maze
         */

        private void maze_correction(){

            for(Ghost e: ghost_list){
                current_maze.set(e.getSpawn_y(),e.getSpawn_x(),new GhostSpawn());
            }

            for (Ball e: balls_list){

                if(e.getPoints() == 1)
                    current_maze.set(e.getY(),e.getX(),new BallElement());
                else
                    current_maze.set(e.getY(),e.getX(),new BigBallElement());

            }

            for(WrapZone e: portals){
                current_maze.set(e.getY(),e.getX(),e);
            }
        }

        /**
         * Função que irá mover cada instancia de fantasmas presentes no jogo
         */
        public void moveGhost(){

            maze_correction();

            for (Ghost e: ghost_list){

                current_maze.set(e.getY(),e.getX(),new Void());

                e.move(current_maze,pacman);

                current_maze.set(e.getY(),e.getX(),e);
            }


        }

        /**
         *  Função que permite receber a cópia da Lista de fantasmas a serem manipulados durante o jogo
         * @return cópia do HashSet dos fantasmas utilizados no desenrrolar do jogo
         */

        public HashSet<Ghost> getGhost(){
            HashSet<Ghost> cpy = new HashSet<>(ghost_list) ;
            return cpy;
        }

        /**
         *  Função para receber a cópia do pacman
         * @return Cópia da instancia do Pacman atual do jogo
         */

        public PacMan getPacman(){
                PacMan cpy = new PacMan(pacman);
                return cpy;
            }

        /**
         * Esta função tem o objetivo de saber se o pacman comeu alguma comida especial
         * @return numero de alimentos especiais a serem utilizados no jogo
         */

        public int getBuff(){

            int result = 0;

            for(Ball e : balls_list){
                if(e.getPoints() > 1){
                    result++;
                }
            }

            return result;

        }

        public void setPoints(int value){
            this.points += value;
        }

        /**
         * Função para alterar a vulnerabilidade dos fantasmas
         * @param value
         */

        public void toogleGhostsStatus(boolean value){
            for (Ghost e: ghost_list){
                     e.toggleVulnerability(value);
            }
        }

        /**
         * Função para movimentar o pacman bem como gerir algumas verificações necessárias,
         * como se este comeu a fruta e etc.
         * @param direction
         * @return retorna verdadeiro caso o pacman tenha-se movido
         */

        public boolean movePacman(int direction) {

            BallElement b = new BallElement();
            Void void_element = new Void();
            WrapZone wrapzone = new WrapZone(0,0);
            Wall w = new Wall();

            for(WrapZone e : portals)
                current_maze.set(e.getY(), e.getX(), wrapzone);

            switch (direction){

                case UP:
                    if(current_maze.get(pacman.getY()-1,pacman.getX()).getSymbol()!= w.getSymbol()) {
                        if(current_maze.get(pacman.getY()-1,pacman.getX()).getSymbol() == wrapzone.getSymbol()){
                            for(WrapZone e:portals)
                                if(e.getX() == pacman.getX() && e.getY() == pacman.getY()-1)
                                    wrapzone = e;
                            for(WrapZone e : portals)
                                if(e.getId() != wrapzone.getId()){
                                    current_maze.set(pacman.getY(),pacman.getX(),void_element);
                                    pacman.setX(e.getX());
                                    pacman.setY(e.getY());
                                }

                        }else{
                            if(current_maze.get(pacman.getY(),pacman.getY()).getSymbol() != FRUIT || current_maze.get(pacman.getY(),pacman.getX()).getSymbol() != WRAP_ZONE){
                                current_maze.set(pacman.getY(),pacman.getX(),void_element);
                            }
                            pacman.moveUp();
                        }
                    }else{
                        return false;
                    }
                    break;

                case LEFT:
                    if(current_maze.get(pacman.getY(),pacman.getX()-1).getSymbol() != w.getSymbol()) {
                        if(current_maze.get(pacman.getY(),pacman.getX()-1).getSymbol() == wrapzone.getSymbol()){
                            for(WrapZone e:portals)
                                if(e.getX() == pacman.getX() -1&& e.getY() == pacman.getY())
                                    wrapzone = e;
                            for(WrapZone e : portals)
                                if(e.getId() != wrapzone.getId()){
                                    current_maze.set(pacman.getY(),pacman.getX(),void_element);
                                    pacman.setX(e.getX());
                                    pacman.setY(e.getY());
                                }

                        }else{
                            if(current_maze.get(pacman.getY(),pacman.getY()).getSymbol() != FRUIT || current_maze.get(pacman.getY(),pacman.getX()).getSymbol() != WRAP_ZONE){
                                current_maze.set(pacman.getY(),pacman.getX(),void_element);
                            }
                            pacman.moveLeft();
                        }
                    }else{
                        return false;
                    }
                    break;

                case DOWN:
                    if(current_maze.get(pacman.getY()+1,pacman.getX()).getSymbol() != w.getSymbol()){
                        if(current_maze.get(pacman.getY()+1,pacman.getX()).getSymbol() == wrapzone.getSymbol()){
                            for(WrapZone e:portals)
                                if(e.getX() == pacman.getX() && e.getY() == pacman.getY()+1)
                                    wrapzone = e;
                            for(WrapZone e : portals)
                                if(e.getId() != wrapzone.getId()){
                                    current_maze.set(pacman.getY(),pacman.getX(),void_element);
                                    pacman.setX(e.getX());
                                    pacman.setY(e.getY());
                                }

                        }else{
                            if(current_maze.get(pacman.getY(),pacman.getY()).getSymbol() != FRUIT || current_maze.get(pacman.getY(),pacman.getX()).getSymbol() != WRAP_ZONE){
                                current_maze.set(pacman.getY(),pacman.getX(),void_element);
                            }
                            pacman.moveDown();
                        }

                    }else{
                        return false;
                    }

                    break;

                case RIGHT:
                    if(current_maze.get(pacman.getY(),pacman.getX()+1).getSymbol() != w.getSymbol()){
                        if(current_maze.get(pacman.getY(),pacman.getX()+1).getSymbol() == wrapzone.getSymbol()){
                            for(WrapZone e:portals)
                                if(e.getX() == pacman.getX()+1 && e.getY() == pacman.getY())
                                    wrapzone = e;
                            for(WrapZone e : portals)
                                if(e.getId() != wrapzone.getId()){
                                    current_maze.set(pacman.getY(),pacman.getX(),void_element);
                                    pacman.setX(e.getX());
                                    pacman.setY(e.getY());
                                }

                        }else{
                            if(current_maze.get(pacman.getY(),pacman.getY()).getSymbol() != FRUIT || current_maze.get(pacman.getY(),pacman.getX()).getSymbol() != WRAP_ZONE){
                                current_maze.set(pacman.getY(),pacman.getX(),void_element);
                            }
                            pacman.moveRight();
                        }
                    }else{
                        return false;
                    }

                    break;
            }


            for(Ball e:balls_list){
                if (e.getX() == pacman.getX() && e.getY() == pacman.getY()){

                    if(eat_to_fruit == FRUIT_SPAWN){
                        if(!fruit.getStatus()){
                            fruit.setActive(true);
                            eat_to_fruit = 0;
                        }
                        eat_to_fruit = 0;
                    }else{
                        eat_to_fruit++;
                    }

                    current_maze.set(e.getY(),e.getX(),pacman);

                    this.points += e.getPoints();
                    balls_list.remove(e);
                    break;
                }


            }

            if(pacman.getX() == fruit.getX() && pacman.getY() == fruit.getY() && fruit.getStatus()){
                points += fruit.getPoints();
                fruit.setActive(false);
            }

            current_maze.set(pacman.getY(),pacman.getX(),pacman);

            return true;
        }


        /**
         * esta função gere a inicialização do labirinto e das instancias necessárias para o desenrrolar do jogo.
         */

        public boolean fillGame(){

            int counter = 0;
            ghost_list.clear();
            balls_list.clear();
            portals.clear();

            char [][] tmp = current_maze.getMaze();

            for (int i = 0; i < this.getMazeRows(); i++) {
                for (int j = 0; j < this.getMazeCols(); j++) {

                    if(tmp[i][j] == SPAWN2){

                        ghost_list.add(new Inky(j,i));
                        ghost_list.add(new Pinky(j,i));
                        ghost_list.add(new Blinky(j,i));
                        ghost_list.add(new Clyde(j,i));

                        for(Ghost e : ghost_list) {
                            e.setMovement_speed(this.level *1);
                            e.setVulnerability_time(e.getVulnerability_time()-this.level);
                        }

                    }else
                    if(tmp[i][j] == SPAWN){

                        PacMan p = new PacMan(this.pacman);
                        pacman = new PacMan(j,i);
                        pacman.setLives(p.getLives());
                        current_maze.set(pacman.getY(),pacman.getX(),pacman);

                    }else if(tmp[i][j] == BALL){

                        counter ++;
                        balls_list.add(new Ball(j,i));

                    }else if(tmp[i][j] == BIG_BALL){

                        counter ++;
                        balls_list.add(new BigBall(j,i));

                    }else if(tmp[i][j] == FRUIT){

                        fruit = new Fruit(fruit,j,i);

                    }else if(tmp[i][j] == WRAP_ZONE){

                        portals.add(new WrapZone(j,i));

                    }
                }

            }

            return true;

        }

        /**
         * Funçao para alterar o nivel do jogo
         * @return retorna verdadeiro caso seja possivel efetuar a mudança de nivel
         */

        public boolean setLevel() {

            if(level > 20 || level < 0)
                return false;

            this.level += 1;

            if(!GameLevel.getLevel(level).getValue1()){
                current_maze = GameLevel.getLevel(this.stage).getValue2();
                return true;
            }

            this.stage += 1;
            current_maze = GameLevel.getLevel(this.level).getValue2();

            return true;
        }

        public int getLevel() {
            return level;
        }

        public int getPoints() {
            return points;
        }

        /**
         * Incrementa os pontos
         * @param value
         */

        public void incPoints(int value) {
            this.points += value;
        }

        public int getPacManLife(){
            return pacman.getLives();
        }

        public void removePacManLife(){

            current_maze.set(pacman.getY(),pacman.getX(),new Void());
            pacman.reset();
            maze_correction();

            for(Ghost e: ghost_list){
                current_maze.set(e.getY(),e.getX(),new Void());
                e.reset();
                current_maze.set(e.getY(),e.getX(),new GhostSpawn());
            }
        }

        public char[][] getMaze(){
            return current_maze.getMaze();
        }

        public int getMazeRows(){
            return GameLevel.getLevelRows(this.stage);
        }

        public int getMazeCols(){
            return GameLevel.getLevelCols(this.stage);
        }

        /**
         * Função para verificar se pode ser efetuada a mudança do nível
         * @return retorna verdadeiro caso o pacman já tenha comido todos os alimentos do nivel atual
         */

        public boolean stepLevelState(){

            int counter = 0;

            for(int i = 0; i < getMazeRows(); i++){
                for(int j= 0; j < getMazeCols(); j++){
                    if(current_maze.get(i,j).getSymbol() == BALL || current_maze.get(i,j).getSymbol() == BIG_BALL )
                        counter++;
                }
            }

            if(counter == 0){
                return true;
            }else{
                return false;
            }

        }

        /**
         * Função responsavel por verificar o estado da fruta presente no labirinto
         * @return
         */
        public boolean getFruitStatus(){
                return fruit.getStatus();
            }

}