// Source code is decompiled from a .class file using FernFlower decompiler.
package GUI;

import Logica.Game;
import java.awt.Color;
import java.awt.Font;
import java.awt.FontFormatException;
import java.awt.Graphics;
import java.io.IOException;

public class TableroPuntaje {
   private int score;
   private Font customFont;
   private Game game;

   public TableroPuntaje(Game game) {
      this.game = game;

      try {
         this.customFont = Font.createFont(0, this.getClass().getResourceAsStream("/img/emulogic.ttf")).deriveFont(18.0F);
      } catch (FontFormatException | IOException var3) {
         var3.printStackTrace();
      }

   }

   public void setScore(int score) {
      this.score = score;
   }

   public void draw(Graphics g) {
      this.score = this.game.puntaje.getScore();
      g.setColor(Color.WHITE);
      g.setFont(this.customFont);
      g.drawString("SCORE: " + this.score, 10, Game.height - 50);
   }
}
