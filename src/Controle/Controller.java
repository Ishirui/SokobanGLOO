package Controle;

import Sokoban.Sokoban.SokobanRuntimeException;

public interface Controller {
    public void onInput(InputToken dir) throws SokobanRuntimeException;
}
