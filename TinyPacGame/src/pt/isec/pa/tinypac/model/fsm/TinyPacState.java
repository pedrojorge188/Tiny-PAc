package pt.isec.pa.tinypac.model.fsm;

/**
 * Enumeração dos estados do jogo
 */

public enum TinyPacState {
    START_GAME, // estado onde e esperado o clieque da tecla
    MOVE_PACMAN, //estado onde o pacman pode andar tranquilamente mas ainda nao existe fantasmas em moviemnto
    MOVE_GHOST, // estado onde os fantasmas saem da caverna e iniciam o movimento
    PAUSE_STATE, // estado de pausa
    VULNERABLE_GHOST, // estado onde os fantasmas ficam vulneraveis
    GAME_OVER,
    GAME_WIN
}
