package Entidades;

import java.awt.Rectangle;

public class Tile extends Rectangle {
	boolean isWall = false;
	boolean isCage = false;
	boolean isSuper = false;
	boolean isFood = false;
	boolean isSpawnpoint = false;
	boolean isLeft = false;
	boolean isRight = false;
	Eatable eatable; // Field to store the eatable object associated with the tile
	public static int tileSizeX = 32;
	public static int tileSizeY = 36;

	public Tile(char c) {
		setBounds(x, y, tileSizeX, tileSizeY);
		isWall = c == 'x';
		isCage = c == 'c';
		isSuper = c == 'y';
		isFood = c == '.' || isSuper;
		isSpawnpoint = c == 'p';
		isLeft = c == 'l';
		isRight = c == 'r';
		if (isFood) {
			if (isSuper) {
				eatable = new Superbola(); // Assuming SuperPellet is a class implementing the Eatable interface
			} else {
				eatable = new Bola(); // Assuming Pellet is a class implementing the Eatable interface
			}
		}
	}

	public boolean isWall() {
		return isWall;
	}

	public boolean isCage() {
		return isCage;
	}

	public boolean isSuper() {
		return isSuper;
	}

	public boolean isFood() {
		return isFood;
	}

	public boolean isSpawnpoint() {
		return isSpawnpoint;
	}


	public Eatable getEatable() {
		return eatable;
	}

	public void clearFood() {
		isFood = false;
		isSuper = false;
	}

	public boolean isLeft() {
		return isLeft;
	}

	public boolean isRight() {
		return isRight;
	}

}
