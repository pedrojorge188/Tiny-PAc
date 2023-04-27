package pt.isec.pa.tinypac.model.data;

import java.io.Serializable;

public interface IMazeElement extends Serializable {

    static final long serialVersionUID = 1L;

    char getSymbol(); // returns the symbol of this element
    // The list of symbols is available
    // in the description of this work
}