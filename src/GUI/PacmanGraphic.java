// Source code is decompiled from a .class file using FernFlower decompiler.
package GUI;

import Logica.PacmanLogic;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.Image;
import java.awt.Rectangle;
import java.awt.geom.AffineTransform;
import java.awt.image.ImageObserver;
import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.io.InputStream;
import javax.swing.ImageIcon;

public class PacmanGraphic extends Rectangle {
   private Image pacmanImage;
   private ImageIcon icon;

   public PacmanGraphic(PacmanLogic logica) {
      InputStream inputStream = this.getClass().getResourceAsStream("/img/pacman.gif");
      if (inputStream != null) {
         try {
            ByteArrayOutputStream outputStream = new ByteArrayOutputStream();
            byte[] buffer = new byte[4096];

            int bytesRead;
            while((bytesRead = inputStream.read(buffer)) != -1) {
               outputStream.write(buffer, 0, bytesRead);
            }

            byte[] imageBytes = outputStream.toByteArray();
            ImageIcon icon = new ImageIcon(imageBytes);
            this.pacmanImage = icon.getImage();
         } catch (IOException var16) {
            var16.printStackTrace();
         } finally {
            try {
               inputStream.close();
            } catch (IOException var15) {
               var15.printStackTrace();
            }

         }
      }

   }

   public void render(Graphics g, int x, int y, int width, int height, boolean up, boolean down, boolean left, boolean right) {
      Graphics2D g2d = (Graphics2D)g;
      double rotation = 0.0;
      if (up) {
         rotation = Math.toRadians(270.0);
      } else if (down) {
         rotation = Math.toRadians(90.0);
      } else if (left) {
         rotation = Math.toRadians(180.0);
      } else if (right) {
         rotation = 0.0;
      }

      AffineTransform original = g2d.getTransform();
      AffineTransform rotationTransform = AffineTransform.getRotateInstance(rotation, (double)(x + width / 2), (double)(y + height / 2));
      g2d.setTransform(rotationTransform);
      g2d.drawImage(this.pacmanImage, x, y, width, height, (ImageObserver)null);
      g2d.setTransform(original);
   }
}
