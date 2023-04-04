package pt.isec.pa.tinypac.ui.text;

import com.googlecode.lanterna.TerminalSize;
import com.googlecode.lanterna.TextColor;
import com.googlecode.lanterna.graphics.TextGraphics;
import com.googlecode.lanterna.input.KeyStroke;
import com.googlecode.lanterna.screen.Screen;
import com.googlecode.lanterna.screen.TerminalScreen;
import com.googlecode.lanterna.terminal.DefaultTerminalFactory;
import com.googlecode.lanterna.terminal.Terminal;
import pt.isec.pa.tinypac.model.data.game.GameManager;

import java.io.IOException;

public class TinyPacUI {

    Terminal terminal;
    Terminal options;
    Screen screen, options_screen;
    TextGraphics tg, op;

    public TinyPacUI() throws IOException {

        TerminalSize size = new TerminalSize(80, 60);
        DefaultTerminalFactory terminalFactory = new DefaultTerminalFactory().setInitialTerminalSize(size);
        terminal = terminalFactory.createTerminal();
        screen = new TerminalScreen(terminal);
        tg = screen.newTextGraphics();
    }

    public void start() throws IOException {

        GameManager gm = new GameManager();
        screen.startScreen();
        boolean keepRunning = true;

        while(keepRunning){
            KeyStroke keyPassed = terminal.pollInput();

            if(keyPassed != null){
                switch (keyPassed.getKeyType()){
                    case Escape:
                        keepRunning = false;
                }
            }

            screen.refresh();
            tg.setBackgroundColor(TextColor.ANSI.BLUE);
            tg.setForegroundColor(TextColor.ANSI.WHITE);

            tg.putString(30,1,"PAC-MAN");

            showMaze(gm);

            tg.setBackgroundColor(TextColor.ANSI.BLACK);
            tg.setForegroundColor(TextColor.ANSI.WHITE);

        }

        screen.readInput();
        screen.stopScreen();

    }


    private void showMaze(GameManager gm) throws IOException {

        screen.refresh();

        try {
            gm.setLevel(gm.getLevel());
            gm.fillGame();
            char[][] m = gm.getMaze();

            for (int i = 0; i < gm.getMazeRows(); i++) {
                for (int j = 0; j < gm.getMazeCols(); j++) {
                    if(m[i][j] == 'x'){
                        tg.setBackgroundColor(TextColor.ANSI.BLUE);
                        tg.setForegroundColor(TextColor.ANSI.WHITE);
                        tg.putString(j+20, i+4, " ");
                    }else if(m[i][j] == 'y'){
                        tg.setBackgroundColor(TextColor.ANSI.BLACK);
                        tg.setForegroundColor(TextColor.ANSI.WHITE);
                        tg.putString(j+20, i+4, " ");
                    }else if(m[i][j] == 'Y'){
                        tg.setBackgroundColor(TextColor.ANSI.YELLOW);
                        tg.setForegroundColor(TextColor.ANSI.WHITE);
                        tg.putString(j+20, i+4, " ");
                    }else if(m[i][j] == 'W'){
                        tg.setBackgroundColor(TextColor.ANSI.GREEN);
                        tg.setForegroundColor(TextColor.ANSI.BLACK);
                        tg.putString(j+20, i+4, "W");
                    }else if(m[i][j] == 'o'){
                        tg.setBackgroundColor(TextColor.ANSI.BLACK);
                        tg.setForegroundColor(TextColor.ANSI.YELLOW);
                        tg.putString(j+20, i+4, ".");
                    }else if(m[i][j] == 'O'){
                        tg.setBackgroundColor(TextColor.ANSI.BLACK);
                        tg.setForegroundColor(TextColor.ANSI.YELLOW_BRIGHT);
                        tg.putString(j+20, i+4, String.valueOf(m[i][j]));
                    }else if(m[i][j] == 'F'){
                        tg.setBackgroundColor(TextColor.ANSI.RED);
                        tg.setForegroundColor(TextColor.ANSI.BLACK);
                        tg.putString(j+20, i+4," ");
                    }else if(m[i][j] == 'M'){
                        tg.setBackgroundColor(TextColor.ANSI.BLACK);
                        tg.setForegroundColor(TextColor.ANSI.WHITE);
                        tg.putString(j+20, i+4,String.valueOf(m[i][j]));
                    }
                }
            }

        } catch (Exception e) {
            tg.setBackgroundColor(TextColor.ANSI.RED);
            tg.putString(15, 4, "Mapa do nivel " + gm.getLevel() + " está mal estruturado!");
        }

        screen.refresh();

    }



}

