package Entidades;

import java.awt.Graphics;
import java.awt.Rectangle;

import GUI.GhostGraphic;
import GUI.Map;
import Logica.Game;
import Logica.GhostLogic;

public class Ghost extends Rectangle implements Character, Eatable {
    private GhostLogic logica;
    private GhostGraphic grafica;

    public Ghost(Map map, Game game, int id) {
        logica = new GhostLogic(map, game, id);
        grafica = new GhostGraphic(logica, id);
    }

    public void tick() {
        logica.tick();
    }

    public void render(Graphics g) {
        grafica.render(g, logica.x, logica.y, logica.width, logica.height, logica.currentDirection(), logica.isAfraid(), logica.getId());
    }

	@Override
	public int calcularPuntaje() {
		return logica.calcularPuntaje();
	}

	@Override
	public void actualizarPuntaje(Game g) {
		logica.actualizarPuntaje(g);
	}

	@Override
	public void move() {
		logica.move();
	}

	@Override
	public Tile currentTile() {
		return logica.currentTile();
	}

	@Override
	public boolean isWallCollision(int tileX, int tileY) {
		return logica.isWallCollision(tileX, tileY);
	}

	@Override
	public void updateTilePosition() {
		logica.updateTilePosition();
	}

	public boolean isAfraid() {
		return logica.isAfraid();
	}

	public void setAfraid(boolean b) {
		logica.setAfraid(b);
	}

	public void setSpawned(boolean b) {
		logica.setSpawned(b);
	}
	
	
}
