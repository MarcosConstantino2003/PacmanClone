package GUI;

import java.awt.Color;
import java.awt.Font;
import java.awt.FontFormatException;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.awt.image.BufferedImage;
import java.io.IOException;
import java.util.List;

import Logica.Game;

public class Leaderboard implements KeyListener {
    private Font customFont;
    private Game game;
    List<Integer> highscores;
    private boolean isActive = false; // Variable to indicate whether the leaderboard is active
    public Leaderboard(Game game) throws IOException {
        this.game = game;
        highscores = game.puntaje.highscores();
        try {
            // Load custom font
            customFont = Font.createFont(Font.TRUETYPE_FONT, getClass().getResourceAsStream("/img/emulogic.ttf")).deriveFont(32f);
        } catch (FontFormatException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public void render(Graphics g) {
        // Create a buffered image for rendering
        BufferedImage buffer = new BufferedImage(Game.width, Game.height, BufferedImage.TYPE_INT_RGB);
        Graphics2D g2d = buffer.createGraphics();

        // Draw background
        g.setColor(Color.BLACK);
        g.fillRect(0, 0, game.width, game.height);

        // Draw leaderboard title
        g.setColor(Color.YELLOW);
        g.setFont(customFont);
        g.drawString("LEADERBOARD", 180, 100);

        // Draw leaderboard content and instructions
        Font text = customFont.deriveFont(14f);
        g.setColor(Color.WHITE);
        g.setFont(text);
        g.drawString("TOP 1: " + (highscores.size() >= 1 ? highscores.get(0) : 0), 280, 140);
        g.drawString("TOP 2: " + (highscores.size() >= 2 ? highscores.get(1) : 0), 280, 160);
        g.drawString("TOP 3: " + (highscores.size() >= 3 ? highscores.get(2) : 0), 280, 180);
        g.drawString("TOP 4: " + (highscores.size() >= 4 ? highscores.get(3) : 0), 280, 200);
        g.drawString("TOP 5: " + (highscores.size() >= 5 ? highscores.get(4) : 0), 280, 220);
        // Draw copyright notice
        Font copyright = customFont.deriveFont(12f);
        g.setColor(Color.WHITE);
        g.setFont(copyright);
        g.drawString("Marcos Constantino © TDP 2024", 180, game.height - 70);

        // Dispose graphics object
        g.dispose();
    }

    public void keyPressed(KeyEvent e) {
        // Handle key presses
        if (isActive) {
            int key = e.getKeyCode();
            if (key == KeyEvent.VK_ESCAPE) {
                game.toMainMenu();
            }
        }
    }

    // Other methods for KeyListener interface
    public void keyReleased(KeyEvent e) {}
    public void keyTyped(KeyEvent e) {}

    // Setters and getters for isActive property
    public void setActive(boolean active) {
        isActive = active;
    }

    public boolean isActive() {
        return isActive;
    }
}
