// Source code is decompiled from a .class file using FernFlower decompiler.
package Logica;

import GUI.Map;

public class Level {
   private Map map;

   public Level(int choice) {
      switch (choice) {
         case 1:
            this.map = new Map("/Niveles/level1.txt");
            break;
         case 2:
            this.map = new Map("/Niveles/level2.txt");
            break;
         case 3:
            this.map = new Map("/Niveles/level3.txt");
      }

   }

   public Map getMap() {
      return this.map;
   }

   public boolean beaten() {
      return this.map.pellets() == 0;
   }
}
