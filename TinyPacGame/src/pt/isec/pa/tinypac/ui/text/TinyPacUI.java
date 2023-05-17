package pt.isec.pa.tinypac.ui.text;

import com.googlecode.lanterna.TerminalSize;
import com.googlecode.lanterna.TextColor;
import com.googlecode.lanterna.graphics.TextGraphics;
import com.googlecode.lanterna.input.KeyStroke;
import com.googlecode.lanterna.screen.Screen;
import com.googlecode.lanterna.screen.TerminalScreen;
import com.googlecode.lanterna.terminal.DefaultTerminalFactory;
import com.googlecode.lanterna.terminal.Terminal;
import pt.isec.pa.tinypac.model.fsm.TinyPacContext;
import pt.isec.pa.tinypac.model.fsm.TinyPacState;
import pt.isec.pa.tinypac.utils.Messages;

import java.io.IOException;

/**
 * Classe representatica da interface de texto para a META 1 do desenvolvimento do trabalho.
 */

public class TinyPacUI {

    private TinyPacContext fsm;
    private Terminal terminal;
    private Screen screen;
    private TextGraphics tg;
    private TinyPacState current_state;

    public TinyPacUI(TinyPacContext fsm) throws IOException {

        this.fsm = fsm;
        current_state = fsm.getState();

        TerminalSize size = new TerminalSize(80, 60);
        DefaultTerminalFactory terminalFactory = new DefaultTerminalFactory().setInitialTerminalSize(size);
        terminalFactory.setTerminalEmulatorTitle("TinyPAC GAME - Trabalho Prático - Programação Avançada 2022 / 2023");
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
            keepRunning = keyActions(keyPassed);

            screen.refresh();

            stateMachineInfos();

            tg.setBackgroundColor(TextColor.ANSI.BLACK);
            tg.setForegroundColor(TextColor.ANSI.GREEN);


            tg.putString(25, (int)fsm.getMazeRows()+8, "POINTS:" + (int)fsm.getPoints());
            tg.putString(25, (int)fsm.getMazeRows()+9, "PACMAN LIVES:" + (int)fsm.getPacManLife());
            tg.putString(25, (int)fsm.getMazeRows()+7, "lEVEL:" + (int)fsm.getLevel());


            screen.refresh();
            tg.setBackgroundColor(TextColor.ANSI.BLUE);
            tg.setForegroundColor(TextColor.ANSI.WHITE);
            tg.putString(30,1,"PAC-MAN");

            showMaze();

            screen.refresh();
            tg.setBackgroundColor(TextColor.ANSI.BLACK);
            tg.setForegroundColor(TextColor.ANSI.WHITE);



        }

        screen.stopScreen();

    }

    private void stateMachineInfos(){

        if(fsm.getState() != current_state){
            TinyPacState tmp = fsm.getState();
            if(tmp == TinyPacState.START_GAME){
                screen.clear();
            }
            System.out.println(Messages.getInstance().listLogs());
            current_state = fsm.getState();
        }

        switch(fsm.getState()){

            case START_GAME -> {

                tg.setBackgroundColor(TextColor.ANSI.BLACK);
                tg.setForegroundColor(TextColor.ANSI.YELLOW_BRIGHT);
                tg.putString(27,3,"PRESS KEY TO START");

            }

            case GAME_OVER -> {

                tg.setBackgroundColor(TextColor.ANSI.BLACK);
                tg.setForegroundColor(TextColor.ANSI.RED);
                tg.putString(27,3,"GAME OVER");

            }
            case GAME_WIN -> {
                tg.setBackgroundColor(TextColor.ANSI.BLACK);
                tg.setForegroundColor(TextColor.ANSI.GREEN_BRIGHT);
                tg.putString(27,3,"YOU WIN!");
            }
            case VULNERABLE_GHOST -> {
                tg.setBackgroundColor(TextColor.ANSI.BLACK);
                tg.setForegroundColor(TextColor.ANSI.CYAN);
                tg.putString(27,3,"Vulnerable Ghosts!");
            }
            case PAUSE_STATE -> {
                tg.setBackgroundColor(TextColor.ANSI.BLACK);
                tg.setForegroundColor(TextColor.ANSI.CYAN);
                tg.putString(27,3,"Game Paused!");
            }
            default -> {

                tg.setBackgroundColor(TextColor.ANSI.BLACK);
                tg.putString(27,3,"                    ");

            }

        }

    }

    private boolean keyActions(KeyStroke keyPassed){

        boolean keepRunning = true;

        if(keyPassed != null){
            switch (keyPassed.getKeyType()){
                case Escape -> {
                    fsm.keyPress(5);
                        keepRunning = false;}
                case ArrowUp -> fsm.keyPress(1);
                case ArrowDown -> fsm.keyPress(2);
                case ArrowLeft -> fsm.keyPress(3);
                case ArrowRight -> fsm.keyPress(4);
                case Enter -> fsm.pause();
                case Backspace -> fsm.resume();
            }
        }

        return keepRunning;

    }

    private void showMaze() throws IOException {

        screen.refresh();

        try {
            char[][] m = (char[][]) fsm.getMaze();

            /*
            for (int i = 0; i < (int)fsm.getMazeRows(); i++) {
                for (int j = 0; j < (int)fsm.getMazeCols(); j++) {
                    if(m[i][j] == 'x'){
                        tg.setBackgroundColor(TextColor.ANSI.BLUE);
                        tg.setForegroundColor(TextColor.ANSI.WHITE);
                        tg.putString(j+25, i+4, " ");
                    }else if(m[i][j] == 'y'){
                        tg.setBackgroundColor(TextColor.ANSI.BLACK);
                        tg.setForegroundColor(TextColor.ANSI.WHITE);
                        tg.putString(j+25, i+4, " ");
                    }else if(m[i][j] == 'W'){
                        tg.setBackgroundColor(TextColor.ANSI.WHITE);
                        tg.putString(j+25, i+4, " ");
                    }else if(m[i][j] == 'o'){
                        tg.setBackgroundColor(TextColor.ANSI.BLACK);
                        tg.setForegroundColor(TextColor.ANSI.YELLOW);
                        tg.putString(j+25, i+4, ".");
                    }else if(m[i][j] == 'O'){
                        tg.setBackgroundColor(TextColor.ANSI.BLACK);
                        tg.setForegroundColor(TextColor.ANSI.YELLOW);
                        tg.putString(j+25, i+4, String.valueOf(m[i][j]));
                    }else if(m[i][j] == 'F'){

                        if((Boolean) fsm.getFruit()){
                            tg.setBackgroundColor(TextColor.ANSI.RED_BRIGHT);
                            tg.putString(j+25, i+4," ");
                        }else{
                            tg.setBackgroundColor(TextColor.ANSI.BLACK);
                            tg.putString(j+25, i+4," ");
                        }

                    }else if(m[i][j] == 'P'){
                        tg.setBackgroundColor(TextColor.ANSI.YELLOW_BRIGHT);
                        tg.setForegroundColor(TextColor.ANSI.WHITE);
                        tg.putString(j+25, i+4, " ");

                    }else if(m[i][j] == 'I'){
                        if(current_state == TinyPacState.VULNERABLE_GHOST){
                            tg.setBackgroundColor(TextColor.ANSI.CYAN);
                            tg.setForegroundColor(TextColor.ANSI.WHITE);
                            tg.putString(j+25, i+4," ");
                        }else{
                            tg.setBackgroundColor(TextColor.ANSI.GREEN_BRIGHT);
                            tg.setForegroundColor(TextColor.ANSI.WHITE);
                            tg.putString(j+25, i+4," ");
                        }
                    }else if(m[i][j] == 'B'){
                        if(current_state == TinyPacState.VULNERABLE_GHOST){
                            tg.setBackgroundColor(TextColor.ANSI.CYAN);
                            tg.setForegroundColor(TextColor.ANSI.WHITE);
                            tg.putString(j+25, i+4," ");
                        }else{
                            tg.setBackgroundColor(TextColor.ANSI.RED);
                            tg.setForegroundColor(TextColor.ANSI.WHITE);
                            tg.putString(j+25, i+4," ");
                        }
                    }else if(m[i][j] == 'K'){
                        if(current_state == TinyPacState.VULNERABLE_GHOST){
                            tg.setBackgroundColor(TextColor.ANSI.CYAN);
                            tg.setForegroundColor(TextColor.ANSI.WHITE);
                            tg.putString(j+25, i+4," ");
                        }else{
                            tg.setBackgroundColor(TextColor.ANSI.WHITE_BRIGHT);
                            tg.setForegroundColor(TextColor.ANSI.WHITE);
                            tg.putString(j+25, i+4," ");
                        }
                    }else if(m[i][j] == 'C'){
                        if(current_state == TinyPacState.VULNERABLE_GHOST){
                            tg.setBackgroundColor(TextColor.ANSI.CYAN);
                            tg.setForegroundColor(TextColor.ANSI.WHITE);
                            tg.putString(j+25, i+4," ");
                        }else{
                            tg.setBackgroundColor(TextColor.ANSI.MAGENTA);
                            tg.setForegroundColor(TextColor.ANSI.WHITE);
                            tg.putString(j+25, i+4," ");
                        }
                    } else{
                        tg.setBackgroundColor(TextColor.ANSI.BLACK);
                        tg.setForegroundColor(TextColor.ANSI.WHITE);
                        tg.putString(j+25, i+4," ");
                    }
                }
            }
                */
    } catch (Exception e) {

            tg.setBackgroundColor(TextColor.ANSI.RED);
            tg.putString(30, 4,  "Erro ao carregar modelos");

        }

        screen.refresh();

    }



}

