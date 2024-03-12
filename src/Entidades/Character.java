package Entidades;

public interface Character {
	void move();
	Tile currentTile();
	boolean isWallCollision(int tileX, int tileY);
	void updateTilePosition();
}
