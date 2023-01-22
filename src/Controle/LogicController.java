package Controle;

import Sokoban.Sokoban.SokobanRuntimeException;

public interface LogicController {
    public void takeGameAction(InputToken dir) throws SokobanRuntimeException;
}
