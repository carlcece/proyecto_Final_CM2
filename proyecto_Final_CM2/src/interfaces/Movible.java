package interfaces;

import main.Isla;
import util.Coordenada;

public interface Movible {
    Coordenada moverse(Isla isla, Coordenada posicionActual);
}