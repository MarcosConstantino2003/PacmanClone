// Source code is decompiled from a .class file using FernFlower decompiler.
package GUI;

import Logica.Game;
import Logica.SoundPlayer;
import java.awt.Color;
import java.awt.Font;
import java.awt.FontFormatException;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.awt.image.BufferedImage;
import java.awt.image.ImageObserver;
import java.io.IOException;

public class LevelSelector implements KeyListener {
   private static final String[] MENU_OPTIONS = new String[]{"Nivel 1", "Nivel 2", "Nivel 3"};
   private int selectedOption = 0;
   private Font customFont;
   private Game game;
   SoundPlayer player;
   private boolean isActive = false;

   public LevelSelector(Game game) throws IOException {
      this.game = game;
      this.player = new SoundPlayer();

      try {
         this.customFont = Font.createFont(0, this.getClass().getResourceAsStream("/img/emulogic.ttf")).deriveFont(16.0F);
      } catch (FontFormatException var3) {
         var3.printStackTrace();
      } catch (IOException var4) {
         var4.printStackTrace();
      }

   }

   public SoundPlayer player() {
      return this.player;
   }

   public void render(Graphics g) {
      BufferedImage buffer = new BufferedImage(Game.width, Game.height, 1);
      Graphics2D g2d = buffer.createGraphics();
      g2d.setColor(Color.BLACK);
      g2d.fillRect(0, 0, Game.width, Game.height);
      g2d.setColor(Color.WHITE);
      Font titulo = this.customFont.deriveFont(48.0F);
      g2d.setColor(Color.YELLOW);
      g2d.setFont(titulo);
      g2d.drawString("NIVELES", 190, 100);
      Font copyright = this.customFont.deriveFont(12.0F);
      g2d.setColor(Color.WHITE);
      g2d.setFont(copyright);
      g2d.drawString("Marcos Constantino \u00a9 TDP 2024", 180, Game.height - 70);
      g2d.setFont(this.customFont);

      for(int i = 0; i < MENU_OPTIONS.length; ++i) {
         if (i == this.selectedOption) {
            g2d.setColor(Color.YELLOW);
         } else {
            g2d.setColor(Color.WHITE);
         }

         int stringWidth = g2d.getFontMetrics().stringWidth(MENU_OPTIONS[i]);
         g2d.drawString(MENU_OPTIONS[i], Game.width / 2 - stringWidth / 2, Game.height / 2 + i * 30 - 30);
      }

      Graphics2D g2dScreen = (Graphics2D)g;
      g2dScreen.drawImage(buffer, 0, 0, (ImageObserver)null);
      g2d.dispose();
   }

   public void selectNextOption() {
      this.selectedOption = (this.selectedOption + 1) % MENU_OPTIONS.length;
   }

   public void selectPreviousOption() {
      this.selectedOption = (this.selectedOption - 1 + MENU_OPTIONS.length) % MENU_OPTIONS.length;
   }

   public int getSelectedOption() {
      return this.selectedOption;
   }

   public void keyPressed(KeyEvent e) {
      if (this.isActive) {
         int key = e.getKeyCode();
         if (key == 38) {
            this.selectPreviousOption();
            this.game.player().playTouchSound();
         } else if (key == 40) {
            this.selectNextOption();
            this.game.player().playTouchSound();
         } else if (key == 10) {
            if (this.getSelectedOption() == 0) {
               this.game.reloadLevel(1);
            } else if (this.getSelectedOption() == 1) {
               this.game.reloadLevel(2);
            } else if (this.getSelectedOption() == 2) {
               this.game.reloadLevel(3);
            }

            if (key == 27) {
               this.game.toMainMenu();
            }
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
