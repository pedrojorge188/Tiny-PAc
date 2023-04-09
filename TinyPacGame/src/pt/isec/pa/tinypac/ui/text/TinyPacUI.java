package pt.isec.pa.tinypac.ui.text;

import com.googlecode.lanterna.TerminalSize;
import com.googlecode.lanterna.TextColor;
import com.googlecode.lanterna.graphics.TextGraphics;
import com.googlecode.lanterna.input.KeyStroke;
import com.googlecode.lanterna.screen.Screen;
import com.googlecode.lanterna.screen.TerminalScreen;
import com.googlecode.lanterna.terminal.DefaultTerminalFactory;
import com.googlecode.lanterna.terminal.Terminal;
import pt.isec.pa.tinypac.model.data.ghost.Ghost;
import pt.isec.pa.tinypac.model.data.pacman.PacMan;
import pt.isec.pa.tinypac.model.fsm.TinyPacContext;
import pt.isec.pa.tinypac.model.fsm.TinyPacState;

import java.io.IOException;
import java.util.HashSet;

public class TinyPacUI {

    private TinyPacContext fsm;
    Terminal terminal;
    Screen screen;
    TextGraphics tg;

    public TinyPacUI(TinyPacContext fsm) throws IOException {

        this.fsm = fsm;
        TerminalSize size = new TerminalSize(80, 60);
        DefaultTerminalFactory terminalFactory = new DefaultTerminalFactory().setInitialTerminalSize(size);
        terminal = terminalFactory.createTerminal();
        screen = new TerminalScreen(terminal);
        tg = screen.newTextGraphics();

    }

    public void start() throws IOException {

        screen.startScreen();
        boolean keepRunning = true;

        while(keepRunning){

            screen.refresh();
            KeyStroke keyPassed = terminal.pollInput();

            if(fsm.getState() == TinyPacState.START_GAME){
                tg.setBackgroundColor(TextColor.ANSI.BLACK);
                tg.setForegroundColor(TextColor.ANSI.YELLOW_BRIGHT);
                tg.putString(25,3,"PRESS KEY TO START");
            }else if(fsm.getState() == TinyPacState.GAME_OVER){
                screen.refresh();
                tg.setBackgroundColor(TextColor.ANSI.RED);
                tg.putString(25,3,"                    ");
                tg.putString(25,3,"GAME_OVER");
            }else{
                tg.setBackgroundColor(TextColor.ANSI.BLACK);
                tg.putString(25,3,"                    ");
            }

            if(keyPassed != null){
                switch (keyPassed.getKeyType()){
                    case Escape -> keepRunning = false;
                    case ArrowUp -> fsm.keyPress(1);
                    case ArrowDown -> fsm.keyPress(2);
                    case ArrowLeft -> fsm.keyPress(3);
                    case ArrowRight -> fsm.keyPress(4);
                }
            }

            screen.refresh();
            tg.setBackgroundColor(TextColor.ANSI.BLUE);
            tg.setForegroundColor(TextColor.ANSI.WHITE);

            tg.putString(30,1,"PAC-MAN");
            showMaze();

            screen.refresh();
            tg.setBackgroundColor(TextColor.ANSI.BLACK);
            tg.setForegroundColor(TextColor.ANSI.WHITE);

        }

        screen.readInput();
        screen.stopScreen();

    }


    private void showMaze() throws IOException {

        screen.refresh();

        try {
            char[][] m = (char[][]) fsm.getMaze();

            for (int i = 0; i < (int)fsm.getMazeRows(); i++) {
                for (int j = 0; j < (int)fsm.getMazeCols(); j++) {
                    if(m[i][j] == 'x'){
                        tg.setBackgroundColor(TextColor.ANSI.BLUE);
                        tg.setForegroundColor(TextColor.ANSI.WHITE);
                        tg.putString(j+20, i+4, " ");
                    }else if(m[i][j] == 'y'){
                        tg.setBackgroundColor(TextColor.ANSI.BLACK);
                        tg.setForegroundColor(TextColor.ANSI.WHITE);
                        tg.putString(j+20, i+4, " ");
                    }else if(m[i][j] == 'W'){
                        tg.setBackgroundColor(TextColor.ANSI.GREEN);
                        tg.putString(j+20, i+4, " ");
                    }else if(m[i][j] == 'o'){
                        tg.setBackgroundColor(TextColor.ANSI.BLACK);
                        tg.setForegroundColor(TextColor.ANSI.YELLOW);
                        tg.putString(j+20, i+4, ".");
                    }else if(m[i][j] == 'O'){
                        tg.setBackgroundColor(TextColor.ANSI.BLACK);
                        tg.setForegroundColor(TextColor.ANSI.YELLOW);
                        tg.putString(j+20, i+4, String.valueOf(m[i][j]));
                    }else if(m[i][j] == 'F'){

                        if((Boolean) fsm.getFruit()){
                            tg.setBackgroundColor(TextColor.ANSI.RED);
                            tg.putString(j+20, i+4," ");
                        }else{
                            tg.setBackgroundColor(TextColor.ANSI.BLACK);
                            tg.putString(j+20, i+4," ");
                        }

                    }else{
                        tg.setBackgroundColor(TextColor.ANSI.BLACK);
                        tg.setForegroundColor(TextColor.ANSI.WHITE);
                        tg.putString(j+20, i+4," ");
                    }
                }
            }



            for(Ghost e : (HashSet<Ghost>) fsm.getGhosts()){
                if(fsm.getState() == TinyPacState.MOVE_GHOST){
                    tg.setBackgroundColor(TextColor.ANSI.CYAN);
                    tg.setForegroundColor(TextColor.ANSI.WHITE);
                    tg.putString(e.getX()+20, e.getY()+4, " ");
                }
            }


            tg.setBackgroundColor(TextColor.ANSI.YELLOW_BRIGHT);
            tg.setForegroundColor(TextColor.ANSI.WHITE);
            tg.putString(((PacMan)fsm.getPacmanModel()).getX()+20, ((PacMan)fsm.getPacmanModel()).getY()+4, " ");


            tg.setBackgroundColor(TextColor.ANSI.BLACK);
            tg.setForegroundColor(TextColor.ANSI.GREEN);

            tg.putString(30, (int)fsm.getMazeRows()+8, "POINTS:" + (int)fsm.getPoints());
            tg.putString(30, (int)fsm.getMazeRows()+9, "PACMAN LIVES:" + (int)fsm.getPacManLife());
            tg.putString(30, (int)fsm.getMazeRows()+7, "lEVEL:" + (int)fsm.getLevel());

    } catch (Exception e) {
            tg.setBackgroundColor(TextColor.ANSI.RED);
            tg.putString(15, 4,  "Erro ao carregar modelos");

        }

        screen.refresh();

    }



}

