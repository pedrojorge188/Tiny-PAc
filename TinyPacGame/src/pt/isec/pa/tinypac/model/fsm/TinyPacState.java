package pt.isec.pa.tinypac.model.fsm;

public enum TinyPacState {
    START_GAME,
    MOVE_PACMAN,
    MOVE_GHOST,
    PACAMAN_LOST_LIFE,
    VULNERABLE_GHOST,
    NEXT_LEVEL,
    GAME_OVER,
    GAME_WIN
}
