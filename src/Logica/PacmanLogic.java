package Logica;

import java.awt.Rectangle;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;

import Entidades.Character;
import Entidades.Eatable;
import Entidades.Ghost;
import Entidades.Superbola;
import Entidades.Tile;
import GUI.Map;

// PacmanLogic class represents the logic and behavior of the Pacman character
public class PacmanLogic extends Rectangle implements KeyListener, Character {
	// Directional flags
	public boolean right, left, up, down;

	// Number of lives
	public int vidas;

	// Current tile coordinates
	private int tileX, tileY;

	// Time tracking for movement delay
	private long lastMoveTime = System.currentTimeMillis();
	private int moveDelay = 250;

	// Reference to the game instance
	private Game game;

	// Current tile reference
	Tile currentTile;

	// Sound player for Pacman actions
	SoundPlayer player;

	// Flags for invincibility, death, and key presses
	private boolean invencible, dead;
	private boolean invencibleKeyPressed, vidasKeyPressed, scareGhostsPressed, isActive;

	// Constructor
	public PacmanLogic(Game game) {
		Map map = game.mapa;
		// Initialize lives, position, and size
		vidas = 3;
		int x = map.spawnpoint().x + 5;
		int y = map.spawnpoint().y + 7;
		setBounds(x, y, 24, 24);
		tileX = x / Tile.tileSizeX;
		tileY = y / Tile.tileSizeY;
		this.game = game;
		currentTile = game.mapa.grid()[tileY][tileX];
		// Reset directional flags
		resetDirectionFlags();
		player = new SoundPlayer();
		invencible = false;
		dead = false;
	}

	// Method to update Pacman's position and check for collisions and food
	public void tick() {
		move();
		tileX = x / Tile.tileSizeX;
		tileY = y / Tile.tileSizeY;
		Tile currentTile = game.mapa.grid()[tileY][tileX];
		if (currentTile.isFood()) {
			Eatable food = currentTile.getEatable();
			eat(food);
			currentTile.clearFood();
		}
	}

	// Method to handle Pacman movement
	public void move() {
		long currentTime = System.currentTimeMillis();
		if (currentTime - lastMoveTime < moveDelay) {
			return;
		}
		lastMoveTime = currentTime;
		updateTilePosition();
		int nextTileX = tileX;
		int nextTileY = tileY;

		// Move based on directional flags
		if (right) {
			nextTileX++;
			if (nextTileX >= game.mapa.getGridWidth()) {
				nextTileX = 0;
			}
		}
		if (left) {
			nextTileX--;
			if (nextTileX < 0) {
				nextTileX = game.mapa.getGridWidth() - 1;
			}
		}
		if (up) {
			nextTileY--;
		}
		if (down) {
			nextTileY++;
		}

		// Check for wall collision and update position
	    if (!isWallCollision(nextTileX, nextTileY) && !isGhostCollision(nextTileX, nextTileY)) {
			x = nextTileX * Tile.tileSizeX + 5;
			y = nextTileY * Tile.tileSizeY + 7;
		}
	}
	
	private boolean isGhostCollision(int nextTileX, int nextTileY) {
	    Rectangle pacmanBounds = new Rectangle(nextTileX * Tile.tileSizeX + 5, nextTileY * Tile.tileSizeY + 7, width, height);
	    for (Ghost ghost : game.ghosts) {
	        if (pacmanBounds.intersects(ghost.getBounds())) {
	            return true; // Collision with a ghost detected
	        }
	    }
	    return false; // No collision with any ghosts
	}

	// Method to update Pacman's tile position
	public void updateTilePosition() {
		tileX = x / Tile.tileSizeX;
		tileY = y / Tile.tileSizeY;
	}

	// Method to handle eating actions
	public void eat(Eatable e) {
		
		e.actualizarPuntaje(game);
	}

	// Method to check for wall collision
	public boolean isWallCollision(int tileX, int tileY) {
		if (game.mapa.grid()[tileY][tileX].isWall() || game.mapa.grid()[tileY][tileX].isCage()) {
			return true;
		}
		return false;
	}

	// Getters and setters
	public int getVidas() {
		return vidas;
	}

	public void setVidas(int vidas) {
		this.vidas = vidas;
	}

	// Method to decrease lives
	public void decreaseVidas() {
		vidas--;
		game.getTextoVidas().setVidas(vidas);
		if (vidas == 0)
			game.gameOver();
	}

	// Method to increase lives
	public void increaseVidas() {
		vidas++;
		game.getTextoVidas().setVidas(vidas);
	}

	// Method to toggle invincibility
	public void toggleInvencible() {
		invencible = !invencible;
		player.playPowerUpSound();
	}

	// Method to check if Pacman is invincible
	public boolean isInvencible() {
		return invencible;
	}

	// Method to scare ghosts
	public void scareGhosts() {
		for (Ghost g : game.ghosts)
			g.setAfraid(true);
	}

	// Method to check if Pacman is active
	public boolean isActive() {
		return isActive;
	}

	// Method to set Pacman's activity status
	public void setActive(boolean isActive) {
		this.isActive = isActive;
	}

	// KeyListener methods
	public void keyTyped(KeyEvent e) {
		// Method implementation
	}

	// Method called when a key is pressed
	public void keyPressed(KeyEvent e) {
		resetDirectionFlags(); // Disable all directions before setting the new direction
		if (isActive) {
			switch (e.getKeyCode()) {
			case KeyEvent.VK_RIGHT:
				right = true;
				break;
			case KeyEvent.VK_LEFT:
				left = true;
				break;
			case KeyEvent.VK_UP:
				up = true;
				break;
			case KeyEvent.VK_DOWN:
				down = true;
				break;
			case KeyEvent.VK_R:
				game.reloadLevel();
				break;
			case KeyEvent.VK_ESCAPE:
				game.toMainMenu();
				break;
			case KeyEvent.VK_I:
				if (!invencibleKeyPressed) {
					toggleInvencible();
					invencibleKeyPressed = true;
				}
				break;
			case KeyEvent.VK_L:
				if (!vidasKeyPressed) {
					increaseVidas();
					vidasKeyPressed = true;
					game.player().playOneUpSound();
				}
				break;
			case KeyEvent.VK_K:
				if (!scareGhostsPressed) {
					scareGhosts();
					scareGhostsPressed = true;
				}
				break;
			}
		}
	}

	// Method called when a key is released
	public void keyReleased(KeyEvent e) {
		switch (e.getKeyCode()) {
		case KeyEvent.VK_I:
			invencibleKeyPressed = false;
			break;
		case KeyEvent.VK_L:
			vidasKeyPressed = false;
			break;
		case KeyEvent.VK_K:
			scareGhostsPressed = false;
			break;
		}
	}

	// Method to reset directional flags
	private void resetDirectionFlags() {
		right = false;
		left = false;
		up = false;
		down = false;
	}

	// Method to get the current tile
	public Tile currentTile() {
		return currentTile;
	}

	// Method to move Pacman to the spawn point
	public void movetoSpawnpoint() {
		Map map = game.mapa;
		int x = map.spawnpoint().x + 5;
		int y = map.spawnpoint().y + 7;
		setLocation(x, y);
	}

	// Method to set Pacman's death status
	public void setDead(boolean b) {
		dead = b;
	}
	
	// Method to check if Pacman is dead
	public boolean isDead() {
		return dead;
	}

}
