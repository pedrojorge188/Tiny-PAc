package pt.isec.pa.tinypac.model.data.elements;

import pt.isec.pa.tinypac.model.data.IMazeElement;

public class Void implements IMazeElement {

    @Override
    public char getSymbol() {
        return ' ';
    }
}
