// Source code is decompiled from a .class file using FernFlower decompiler.
package GUI;

import Logica.Game;
import java.awt.Color;
import java.awt.Font;
import java.awt.FontFormatException;
import java.awt.Graphics;
import java.io.IOException;

public class Reloj {
   private Game game;
   private long startTime;
   private Font customFont;

   public Reloj(Game game) {
      this.game = game;
      this.startTime = Game.startTime;

      try {
         this.customFont = Font.createFont(0, this.getClass().getResourceAsStream("/img/emulogic.ttf")).deriveFont(18.0F);
      } catch (FontFormatException | IOException var3) {
         var3.printStackTrace();
      }

   }

   public void resetTimer() {
      this.startTime = Game.startTime;
   }

   public void draw(Graphics g) {
      g.setColor(Color.WHITE);
      g.setFont(this.customFont);
      long elapsedTime = System.currentTimeMillis() - this.startTime;
      long seconds = elapsedTime / 1000L % 60L;
      long minutes = elapsedTime / 1000L / 60L;
      String timeFormatted = String.format("%02d:%02d", minutes, seconds);
      g.drawString("TIME: " + timeFormatted, Game.width - 490, Game.height - 50);
      if (this.game.pacmanlogica.isInvencible()) {
         g.drawString("Invencible!", Game.width - 490, Game.height - 100);
      }

   }
}
