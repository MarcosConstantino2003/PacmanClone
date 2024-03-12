// Source code is decompiled from a .class file using FernFlower decompiler.
package GUI;

import Logica.Game;
import java.awt.Color;
import java.awt.Font;
import java.awt.FontFormatException;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.awt.image.BufferedImage;
import java.io.IOException;

public class Rules implements KeyListener {
   private Font customFont;
   private Game game;
   private boolean isActive = false;

   public Rules(Game game) throws IOException {
      this.game = game;

      try {
         this.customFont = Font.createFont(0, this.getClass().getResourceAsStream("/img/emulogic.ttf")).deriveFont(24.0F);
      } catch (FontFormatException var3) {
         var3.printStackTrace();
      } catch (IOException var4) {
         var4.printStackTrace();
      }

   }

   public void render(Graphics g) {
      BufferedImage buffer = new BufferedImage(Game.width, Game.height, 1);
      Graphics2D g2d = buffer.createGraphics();
      g.setColor(Color.BLACK);
      g.fillRect(0, 0, Game.width, Game.height);
      g.setColor(Color.YELLOW);
      g.setFont(this.customFont);
      g.drawString("Reglas:", 50, 100);
      Font text = this.customFont.deriveFont(10.0F);
      g.setColor(Color.WHITE);
      g.setFont(text);
      g.drawString("-> Presiona 'R' para reinciar el nivel", 30, 120);
      g.drawString("-> Presiona ESC para volver al menu en cualquier momento.", 30, 140);
      g.drawString("-> Las bolitas dan 10 puntos.", 30, 160);
      g.drawString("-> Las superbolas dan 50 puntos y 5s de power-up.", 30, 180);
      g.drawString("-> Comerse un fantasma da 200 puntos.", 30, 200);
      g.setColor(Color.RED.darker());
      g.setFont(this.customFont);
      g.drawString("CHEATS:", 50, 240);
      g.setColor(Color.WHITE);
      g.setFont(text);
      g.drawString("-> Presiona 'I' para alternar invencibilidad.", 30, 260);
      g.drawString("-> Presiona 'L' para sumarte una vida.", 30, 280);
      g.drawString("-> Presiona 'K' para hacer a los fantasmas comestibles.", 30, 300);
      Font copyright = this.customFont.deriveFont(12.0F);
      g.setColor(Color.WHITE);
      g.setFont(copyright);
      g.drawString("Marcos Constantino \u00a9 TDP 2024", 180, Game.height - 70);
      g.dispose();
   }

   public void keyPressed(KeyEvent e) {
      if (this.isActive) {
         int key = e.getKeyCode();
         if (key == 27) {
            this.game.toMainMenu();
         } else if (key == 27) {
            this.game.toMainMenu();
         }
      }

   }

   public void keyReleased(KeyEvent e) {
   }

   public void keyTyped(KeyEvent e) {
   }

   public void setActive(boolean active) {
      this.isActive = active;
   }

   public boolean isActive() {
      return this.isActive;
   }
}
