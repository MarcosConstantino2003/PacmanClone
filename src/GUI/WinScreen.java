// Source code is decompiled from a .class file using FernFlower decompiler.
package GUI;

import Logica.Game;
import java.awt.Color;
import java.awt.Font;
import java.awt.FontFormatException;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.image.BufferedImage;
import java.awt.image.ImageObserver;
import java.io.IOException;

public class WinScreen {
   private Font customFont;
   private int puntaje;
   private Game game;

   public WinScreen(Game game) {
      this.game = game;
      this.puntaje = game.puntaje.getScore();

      try {
         this.customFont = Font.createFont(0, this.getClass().getResourceAsStream("/img/emulogic.ttf")).deriveFont(46.0F);
      } catch (FontFormatException | IOException var3) {
         var3.printStackTrace();
      }

   }

   public void render(Graphics g) {
      BufferedImage buffer = new BufferedImage(Game.width, Game.height, 1);
      Graphics2D g2d = buffer.createGraphics();
      g2d.setColor(Color.BLACK);
      g2d.setFont(this.customFont);
      g2d.fillRect(0, 0, Game.width, Game.height);
      g2d.setColor(Color.WHITE);
      g2d.drawString("YOU WIN!", 10, Game.height - 100);
      Font score = this.customFont.deriveFont(24.0F);
      g2d.setFont(score);
      this.puntaje = this.game.puntaje.getScore();
      g2d.drawString("FINAL SCORE: " + this.puntaje, 130, 100);
      if (this.puntaje == this.game.puntaje.getHighscore()) {
         g2d.drawString("NEW HIGHSCORE!", 130, 150);
      }

      Font subtexto = this.customFont.deriveFont(16.0F);
      g2d.setFont(subtexto);
      g2d.drawString("Marcos Constantino \u00a9 TDP 2024", 10, Game.height - 50);
      Graphics2D g2dScreen = (Graphics2D)g;
      g2dScreen.drawImage(buffer, 0, 0, (ImageObserver)null);
      g2d.dispose();
   }
}
