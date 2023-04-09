package pt.isec.pa.tinypac.model.fsm;

public interface ITinyPacState {

    TinyPacState getState();
    boolean keyPress(int direction); // START_GAME -> MOVE_PACMAN
    boolean getPacman(); // MOVE_GHOST -> PACMAN_LOST_LIFE
    boolean pacManFinish(); // MOVE_GHOST -> NEXT_LEVEL
    boolean pacManBuff(); // MOVE_GHOST -> VULNERABLE_GHOST
    boolean pacManKillGhosts(); //VULNERABLE_GHOST -> MOVE_GHOST
    boolean timeout();


}
