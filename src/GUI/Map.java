// Source code is decompiled from a .class file using FernFlower decompiler.
package GUI;

import Entidades.Tile;
import java.awt.Color;
import java.awt.Graphics;
import java.util.Scanner;

public class Map {
   private Tile[][] grid;
   private char[][] gridbase;
   private Tile cage;
   private Tile spawnpoint;
   private int spawnpointX;
   private int spawnpointY;
   private int cageX;
   private int cageY;
   private int pelletCount;

   public Tile[][] grid() {
      return this.grid;
   }

   public char[][] gridbase() {
      return this.gridbase;
   }

   public Map(String path) {
      Scanner scanner = new Scanner(this.getClass().getResourceAsStream(path));
      int rows = 0;
      int cols = 0;

      while(scanner.hasNextLine()) {
         ++rows;
         String line = scanner.nextLine();
         if (cols == 0) {
            cols = line.length();
         }
      }

      scanner.close();
      this.gridbase = new char[rows][cols];
      scanner = new Scanner(this.getClass().getResourceAsStream(path));

      int i;
      for(i = 0; i < rows; ++i) {
         String line = scanner.nextLine();

         for(int j = 0; j < cols; ++j) {
            this.gridbase[i][j] = line.charAt(j);
         }
      }

      scanner.close();
      this.grid = new Tile[rows][cols];

      for(i = 0; i < rows; ++i) {
         for(int j = 0; j < cols; ++j) {
            char c = this.gridbase[i][j];
            this.grid[i][j] = new Tile(c);
            if (this.grid[i][j].isCage()) {
               this.cage = this.grid[i][j];
               this.cageX = j;
               this.cageY = i;
               this.cage.setLocation(j * Tile.tileSizeX, i * Tile.tileSizeY);
            }

            if (this.grid[i][j].isSpawnpoint()) {
               this.spawnpoint = this.grid[i][j];
               this.spawnpointX = j;
               this.spawnpointY = i;
               this.spawnpoint.setLocation(j * Tile.tileSizeX, i * Tile.tileSizeY);
            }
         }
      }

      this.calcularComida();
   }

   public void printGrid(char[][] grid) {
      char[][] var5 = grid;
      int var4 = grid.length;

      for(int var3 = 0; var3 < var4; ++var3) {
         char[] row = var5[var3];
         char[] var9 = row;
         int var8 = row.length;

         for(int var7 = 0; var7 < var8; ++var7) {
            char cell = var9[var7];
            System.out.print(cell + " ");
         }

         System.out.println();
      }

   }

   public void drawLevel(Graphics g) {
      int tileSizeX = Tile.tileSizeX;
      int tileSizeY = Tile.tileSizeY;

      for(int row = 0; row < this.grid.length; ++row) {
         for(int col = 0; col < this.grid[row].length; ++col) {
            int x = col * tileSizeX;
            int y = row * tileSizeY;
            if (this.grid[row][col].isWall()) {
               g.setColor(Color.BLUE.darker().darker().darker());
               g.fillRect(x, y, tileSizeX, tileSizeY);
            } else if (this.grid[row][col].isCage()) {
               g.setColor(Color.BLACK);
               g.fillRect(x, y, tileSizeX, tileSizeY);
               g.setColor(Color.WHITE);
               g.drawRect(x, y, tileSizeX - 1, tileSizeY - 1);
            } else if (this.grid[row][col].isSuper()) {
               g.setColor(Color.ORANGE);
               g.fillOval(x + 4, y + 4, tileSizeX - 10, tileSizeY - 15);
            } else if (this.grid[row][col].isFood()) {
               g.setColor(Color.YELLOW);
               g.fillOval(x + 7, y + 7, tileSizeX / 4, tileSizeY / 4);
            } else {
               g.setColor(Color.BLACK);
               g.fillOval(x + 7, y + 7, tileSizeX / 4, tileSizeY / 4);
            }
         }
      }

   }

   private void calcularComida() {
      this.pelletCount = 0;

      for(int row = 0; row < this.grid.length; ++row) {
         for(int col = 0; col < this.grid[row].length; ++col) {
            Tile t = this.grid[row][col];
            if (t.isFood()) {
               ++this.pelletCount;
            }
         }
      }

   }

   public void eatPellet() {
      --this.pelletCount;
   }

   public int pellets() {
      return this.pelletCount;
   }

   public Tile cage() {
      return this.cage;
   }

   public Tile spawnpoint() {
      return this.spawnpoint;
   }

   public int getGridWidth() {
      return this.grid.length > 0 ? this.grid[0].length : 0;
   }

   public int getGridHeight() {
      return this.grid.length;
   }
}
