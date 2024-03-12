package Logica;

import java.awt.Rectangle;
import java.util.Timer;
import java.util.TimerTask;
import Entidades.Character;
import Entidades.Eatable;
import Entidades.Tile;
import GUI.Map;

public class GhostLogic extends Rectangle implements Character, Eatable {
	private int tileX;
	private int tileY;
	private long lastMoveTime;
	private int moveDelay = 250;
	private Direction currentDirection;
	private int id;
	private int puntaje;
	private Map map;
	private PacmanLogic pacman;
	private boolean spawned, afraid, warped;
	private Game game;
	private long spawnDelay = 3000;
	private long afraidStartTime;
	private long warpStartTime = 0;
	private long warpDuration = 3000;

	public GhostLogic(Map map, Game game, int id) {
		currentDirection = Direction.UP;
		this.id = id;
		this.map = map;
		this.game = game;
		pacman = game.pacmanlogica;
		puntaje = 200;
		spawned = false;
	}

	public void tick() {
		if (spawned)
			move();
		updateTilePosition();
		if (!spawned && shouldSpawn()) {
			spawn();
			spawned = true;
		}
		if (intersects(pacman.getBounds()) && !pacman.isInvencible()) {
			if (!afraid) {
				killPacman();
			} else {
				die();
			}
		}
		if (afraid && (System.currentTimeMillis() - afraidStartTime) > 5000) {
			setAfraid(false);
		}
	}

	public void die() {
		pacman.eat(this);
		setLocation(map.cage().x + 5, map.cage().y + 7);
		afraid = false;
		Timer resetMoveDelayTimer = new Timer();
		resetMoveDelayTimer.schedule(new TimerTask() {
			public void run() {
				moveDelay = 225;
			}
		}, 5000);
	}

	private boolean shouldSpawn() {
		boolean res = false;
		long currentTime = System.currentTimeMillis();
		if (id == 1)
			res = (currentTime - Game.startTime >= spawnDelay);
		if (id == 2)
			res = (currentTime - Game.startTime >= spawnDelay * 2);
		if (id == 3)
			res = (currentTime - Game.startTime >= spawnDelay * 3);
		if (id == 4)
			res = (currentTime - Game.startTime >= spawnDelay * 4);
		return res;
	}

	public void spawn() {
		int x = map.cage().x + 5;
		int y = map.cage().y + 7;
		setBounds(x, y, 24, 24);
		tileX = x / Tile.tileSizeX;
		tileY = y / Tile.tileSizeY;
		lastMoveTime = System.currentTimeMillis();
		spawned = true;
	}

	public void move() {
		long currentTime = System.currentTimeMillis();
		if (currentTime - lastMoveTime < moveDelay) {
			return;
		}
		lastMoveTime = currentTime;

		int nextTileX = tileX;
		int nextTileY = tileY;

		if (currentTile().isLeft()) {
			warp();
			x = (game.mapa.getGridWidth() - 1) * Tile.tileSizeX - 5;
			currentDirection = Direction.LEFT;
		}
		if (currentTile().isRight()) {
			warp();
			x = Tile.tileSizeX + 5;
			currentDirection = Direction.RIGHT;
		}
		switch (currentDirection) {
		case UP:
			nextTileY--;
			break;
		case DOWN:
			nextTileY++;
			break;
		case LEFT:

			nextTileX--;
			break;
		case RIGHT:

			nextTileX++;
			break;
		}
		Direction oldDir = currentDirection;
		if (!warped) {
			if (!isWallCollision(nextTileX, nextTileY)) {
				x = nextTileX * Tile.tileSizeX + 5;
				y = nextTileY * Tile.tileSizeY + 7;
				tileX = nextTileX;
				tileY = nextTileY;
			}
			changeDirectionRandomly();
			if (!isInHallway()) {
				while (opposite(currentDirection) == oldDir)
					changeDirectionRandomly();
			} else if (map.cage().equals(currentTile())) {
				changeDirectionRandomly();
			} else {
				currentDirection = opposite(oldDir);
			}
		}
		warped = false;
	}

	private void changeDirectionRandomly() {
		int randomNumber = (int) (Math.random() * 4);
		for (int i = 0; i < 4; i++) {
			Direction newDirection = Direction.values()[(randomNumber + i) % 4];
			int nextTileX = tileX;
			int nextTileY = tileY;
			switch (newDirection) {
			case UP:
				nextTileY--;
				break;
			case DOWN:
				nextTileY++;
				break;
			case LEFT:
				nextTileX--;
				break;
			case RIGHT:
				nextTileX++;
				break;
			}
			if (!isWallCollision(nextTileX, nextTileY)) {
				currentDirection = newDirection;
				break;
			}
		}
	}

	private boolean isInHallway() {
		int numDirections = 0;
		if (!isWallCollision(tileX, tileY - 1))
			numDirections++;
		if (!isWallCollision(tileX, tileY + 1))
			numDirections++;
		if (!isWallCollision(tileX - 1, tileY))
			numDirections++;
		if (!isWallCollision(tileX + 1, tileY))
			numDirections++;
		return numDirections == 1;
	}
	

	public void killPacman() {
		  // Move the Ghost to Pacman's location
	    setLocation(pacman.x, pacman.y);
	    
	    // Play death sound
	    game.player().playDeathSound();
	    
	    // Set Pacman's state to dead and decrease lives
	    pacman.setDead(true);
	    pacman.decreaseVidas();
	    game.render();
	    
	    // Pause the game to show the Ghost over Pacman
	    game.pauseGame(3000);
	    
	    // Reset entities after pausing
	    game.resetEntities();
	}

	public boolean isWallCollision(int tileX, int tileY) {
		if (tileX < 0 || tileX >= game.mapa.getGridWidth() || game.mapa.grid()[tileY][tileX].isWall()) {
			return true;
		}
		return false;
	}

	// Enums for defining directions
	public enum Direction {
		UP, DOWN, LEFT, RIGHT
	}

	private Direction opposite(Direction d) {
		Direction opp;
		switch (d) {
		case UP:
			opp = Direction.DOWN;
			break;
		case DOWN:
			opp = Direction.UP;
			break;
		case LEFT:
			opp = Direction.RIGHT;
			break;
		case RIGHT:
			opp = Direction.LEFT;
			break;
		default:
			opp = d; // If no valid direction is provided, return the same direction
			break;
		}
		return opp;
	}

	public void updateTilePosition() {
		tileX = x / Tile.tileSizeX;
		tileY = y / Tile.tileSizeY;
	}


	public Tile currentTile() {
		return game.mapa.grid()[tileY][tileX];
	}

	public int calcularPuntaje() {
		return puntaje;
	}

	public void actualizarPuntaje(Game g) {
		if(afraid) {
			g.puntaje.addScore(this);
			g.player().playGhostSound();
		}
	}

	public void setSpawned(boolean b) {
		spawned = b;
	}

	public void setAfraid(boolean b) {
		afraid = b;
		if (b) {
			moveDelay = 450;
			afraidStartTime = System.currentTimeMillis();
		} else {
			moveDelay = 225;
		}
	}

	public boolean isAfraid() {
		return afraid;
	}

	public void setSpawnDelay(long spawnDelay) {
		this.spawnDelay = spawnDelay;
	}

	public Direction currentDirection() {
		return currentDirection;
	}

	public int getId() {
		return id;
	}

	public void warp() {
		warped = true;
		warpStartTime = System.currentTimeMillis();
		// Increase the move delay to slow down the ghost's movement
		moveDelay = 500; // Example: Increase the move delay to slow down the ghost

		// Schedule a task to reset the warp state after the warp duration
		Timer timer = new Timer();
		timer.schedule(new TimerTask() {
			public void run() {
				// Reset the move delay to its original value
				moveDelay = 250; // Example: Reset the move delay to its original value
			}
		}, warpDuration);
	}
}
