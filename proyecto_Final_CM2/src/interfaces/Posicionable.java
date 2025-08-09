package interfaces;

import util.Coordenada;

public interface Posicionable {
    Coordenada getPosicion();
    void setPosicion(Coordenada nuevaPosicion);
}