package Entidades;

import java.awt.Graphics;
import java.awt.Rectangle;

import GUI.PacmanGraphic;
import Logica.Game;
import Logica.PacmanLogic;

public class Pacman extends Rectangle implements Runnable {
    private PacmanLogic logica;
    private PacmanGraphic grafica;
    private Thread thread;
    private Game g;
    public Pacman(Game game) {
    	g = game;
        logica = new PacmanLogic(game);
        grafica = new PacmanGraphic(logica);
        thread = new Thread(this);
    }

    public void run() {
        while (logica.isActive()) {
            tick();
            render(g.getGraphics());
            try {
                Thread.sleep(100); // Adjust sleep time as needed
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
    }

    public void start() {
        thread.start();
    }

    public void stop() {
        logica.setActive(false);
        try {
            thread.join();
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }

    public void tick() {
        logica.tick();
    }

    public void render(Graphics g) {
        grafica.render(g, logica.x, logica.y, logica.width, logica.height, logica.up, logica.down, logica.left, logica.right);
    }

    public PacmanLogic getLogica() {
        return logica;
    }
}
