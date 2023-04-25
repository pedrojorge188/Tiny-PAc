package pt.isec.pa.tinypac.model.fsm;

/**
 * Enumeração dos estados do jogo
 */

public enum TinyPacState {
    START_GAME,
    MOVE_PACMAN,
    MOVE_GHOST,
    VULNERABLE_GHOST,
    NEXT_LEVEL,
    GAME_OVER,
    GAME_WIN
}
