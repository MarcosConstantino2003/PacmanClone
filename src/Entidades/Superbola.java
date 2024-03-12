package Entidades;

import Logica.Game;

public class Superbola extends Bola {
	int score = 50;
	public int calcularPuntaje() {
		return score;
	}
	public void actualizarPuntaje(Game g) {
		for (Ghost ghost : g.ghosts)
			ghost.setAfraid(true);
		g.puntaje.addScore(this);
		g.player().playPowerUpSound();
		g.mapa.eatPellet();
	}
}
