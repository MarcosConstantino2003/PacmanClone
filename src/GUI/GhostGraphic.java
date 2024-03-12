// Source code is decompiled from a .class file using FernFlower decompiler.
package GUI;

import Logica.GhostLogic;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.Image;
import java.awt.Rectangle;
import java.awt.image.BufferedImage;
import java.awt.image.ImageObserver;
import java.io.InputStream;
import javax.imageio.ImageIO;

public class GhostGraphic extends Rectangle {
   private Image image;
   private GhostLogic g;
   private int id;

   public GhostGraphic(GhostLogic g, int id) {
      this.g = g;
      this.id = id;
   }

   private GhostLogic.Direction currentDirection() {
      return this.g.currentDirection();
   }

   public void render(Graphics g, int x, int y, int width, int height, GhostLogic.Direction currentDirection, boolean afraid, int id) {
      Graphics2D g2d = (Graphics2D)g;

      try {
         InputStream inputStream;
         BufferedImage image;
         label49:
         switch (id) {
            case 1:
               switch (this.currentDirection()) {
                  case UP:
                     inputStream = this.getClass().getResourceAsStream("/img/rosa_up.png");
                     image = ImageIO.read(inputStream);
                     g.drawImage(image, x, y, width, height, (ImageObserver)null);
                     break label49;
                  case DOWN:
                     inputStream = this.getClass().getResourceAsStream("/img/rosa_down.png");
                     image = ImageIO.read(inputStream);
                     g.drawImage(image, x, y, width, height, (ImageObserver)null);
                     break label49;
                  case LEFT:
                     inputStream = this.getClass().getResourceAsStream("/img/rosa_left.png");
                     image = ImageIO.read(inputStream);
                     g.drawImage(image, x, y, width, height, (ImageObserver)null);
                     break label49;
                  case RIGHT:
                     inputStream = this.getClass().getResourceAsStream("/img/rosa_right.png");
                     image = ImageIO.read(inputStream);
                     g.drawImage(image, x, y, width, height, (ImageObserver)null);
                  default:
                     break label49;
               }
            case 2:
               switch (currentDirection) {
                  case UP:
                     inputStream = this.getClass().getResourceAsStream("/img/rojo_up.png");
                     image = ImageIO.read(inputStream);
                     g.drawImage(image, x, y, width, height, (ImageObserver)null);
                     break label49;
                  case DOWN:
                     inputStream = this.getClass().getResourceAsStream("/img/rojo_down.png");
                     image = ImageIO.read(inputStream);
                     g.drawImage(image, x, y, width, height, (ImageObserver)null);
                     break label49;
                  case LEFT:
                     inputStream = this.getClass().getResourceAsStream("/img/rojo_left.png");
                     image = ImageIO.read(inputStream);
                     g.drawImage(image, x, y, width, height, (ImageObserver)null);
                     break label49;
                  case RIGHT:
                     inputStream = this.getClass().getResourceAsStream("/img/rojo_right.png");
                     image = ImageIO.read(inputStream);
                     g.drawImage(image, x, y, width, height, (ImageObserver)null);
                  default:
                     break label49;
               }
            case 3:
               switch (currentDirection) {
                  case UP:
                     inputStream = this.getClass().getResourceAsStream("/img/celeste_up.png");
                     image = ImageIO.read(inputStream);
                     g.drawImage(image, x, y, width, height, (ImageObserver)null);
                     break label49;
                  case DOWN:
                     inputStream = this.getClass().getResourceAsStream("/img/celeste_down.png");
                     image = ImageIO.read(inputStream);
                     g.drawImage(image, x, y, width, height, (ImageObserver)null);
                     break label49;
                  case LEFT:
                     inputStream = this.getClass().getResourceAsStream("/img/celeste_left.png");
                     image = ImageIO.read(inputStream);
                     g.drawImage(image, x, y, width, height, (ImageObserver)null);
                     break label49;
                  case RIGHT:
                     inputStream = this.getClass().getResourceAsStream("/img/celeste_right.png");
                     image = ImageIO.read(inputStream);
                     g.drawImage(image, x, y, width, height, (ImageObserver)null);
                  default:
                     break label49;
               }
            case 4:
               switch (currentDirection) {
                  case UP:
                     inputStream = this.getClass().getResourceAsStream("/img/naranja_up.png");
                     image = ImageIO.read(inputStream);
                     g.drawImage(image, x, y, width, height, (ImageObserver)null);
                     break;
                  case DOWN:
                     inputStream = this.getClass().getResourceAsStream("/img/naranja_down.png");
                     image = ImageIO.read(inputStream);
                     g.drawImage(image, x, y, width, height, (ImageObserver)null);
                     break;
                  case LEFT:
                     inputStream = this.getClass().getResourceAsStream("/img/naranja_left.png");
                     image = ImageIO.read(inputStream);
                     g.drawImage(image, x, y, width, height, (ImageObserver)null);
                     break;
                  case RIGHT:
                     inputStream = this.getClass().getResourceAsStream("/img/naranja_right.png");
                     image = ImageIO.read(inputStream);
                     g.drawImage(image, x, y, width, height, (ImageObserver)null);
               }
         }

         if (afraid) {
            inputStream = this.getClass().getResourceAsStream("/img/scared.png");
            image = ImageIO.read(inputStream);
            g.drawImage(image, x, y, width, height, (ImageObserver)null);
         }
      } catch (Exception var14) {
      }

   }
}
