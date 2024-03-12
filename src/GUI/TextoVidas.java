// Source code is decompiled from a .class file using FernFlower decompiler.
package GUI;

import Logica.Game;
import java.awt.Color;
import java.awt.Font;
import java.awt.FontFormatException;
import java.awt.Graphics;
import java.io.IOException;

public class TextoVidas {
   private int vidas;
   private Font customFont;
   private Game game;

   public TextoVidas(Game game) {
      this.game = game;
      this.vidas = game.pacmanlogica.getVidas();

      try {
         this.customFont = Font.createFont(0, this.getClass().getResourceAsStream("/img/emulogic.ttf")).deriveFont(18.0F);
      } catch (FontFormatException | IOException var3) {
         var3.printStackTrace();
      }

   }

   public void setVidas(int vidas) {
      this.vidas = vidas;
   }

   public void draw(Graphics g) {
      g.setColor(Color.WHITE);
      g.setFont(this.customFont);
      g.drawString("LIVES: " + this.vidas, Game.width - 200, Game.height - 50);
   }
}
