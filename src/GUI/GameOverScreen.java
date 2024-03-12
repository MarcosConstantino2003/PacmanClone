// Source code is decompiled from a .class file using FernFlower decompiler.
package GUI;

import Logica.Game;
import Logica.SoundPlayer;
import java.awt.Color;
import java.awt.Font;
import java.awt.FontFormatException;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.image.BufferedImage;
import java.awt.image.ImageObserver;
import java.io.IOException;

public class GameOverScreen {
   private Font customFont;
   private SoundPlayer player;

   public GameOverScreen() {
      try {
         this.customFont = Font.createFont(0, this.getClass().getResourceAsStream("/img/emulogic.ttf")).deriveFont(46.0F);
      } catch (FontFormatException | IOException var2) {
         var2.printStackTrace();
      }

   }

   public void render(Graphics g) {
      BufferedImage buffer = new BufferedImage(Game.width, Game.height, 1);
      Graphics2D g2d = buffer.createGraphics();
      g2d.setColor(Color.BLACK);
      g2d.setFont(this.customFont);
      g2d.fillRect(0, 0, Game.width, Game.height);
      g2d.setColor(Color.WHITE);
      g2d.drawString("GAME OVER!", 10, Game.height - 100);
      Font subtexto = this.customFont.deriveFont(16.0F);
      g2d.setFont(subtexto);
      g2d.drawString("Marcos Constantino \u00a9 TDP 2024", 10, Game.height - 50);
      Graphics2D g2dScreen = (Graphics2D)g;
      g2dScreen.drawImage(buffer, 0, 0, (ImageObserver)null);
      g2d.dispose();
   }
}
