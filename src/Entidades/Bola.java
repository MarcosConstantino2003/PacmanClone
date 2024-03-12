package Entidades;
import Logica.Game;
public class Bola implements Eatable{
	//Atributos
	boolean eaten = false;
	int puntaje = 10;
	//Constructor
	public Bola() {
		eaten = false;
	}
	//Getter y setter
	public int calcularPuntaje() {
		return puntaje;
	}
	
	public void setEaten(boolean b) {
		eaten = b;
	}
	
	//Actualiza el porcentaje al ser comido.
	public void actualizarPuntaje(Game g) {
		g.puntaje.addScore(this);
		g.player().playMunchSound();
		g.mapa.eatPellet();
	}
	
}
