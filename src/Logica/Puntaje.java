package Logica;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Scanner;

import Entidades.Eatable;
import GUI.TableroPuntaje;

public class Puntaje {
    private int score = 0;
    private File scoreboardTextFile;
    private TableroPuntaje tablero;
    private List<Integer> highscores;

    public Puntaje(TableroPuntaje tablero) {
        this.tablero = tablero;
        scoreboardTextFile = new File("scoreboard.txt");
        try {
            scoreboardTextFile.createNewFile();
        } catch (IOException e) {
            e.printStackTrace();
        }
        loadHighscoresFromFile();
    }
    

    public void addScore(Eatable e) {
        score += e.calcularPuntaje();
        tablero.setScore(score);
    }

    public int getScore() {
        return score;
    }

    public void setScore(int score) {
        this.score = score;
        tablero.setScore(score);
    }

    public int getHighscore() {
        updateHighscore();
        if (highscores.isEmpty()) {
            return 0;
        }
        return highscores.get(0);
    }

    public int getHighscore(int i) {
        updateHighscore();
        if (highscores.isEmpty()) {
            return 0;
        }
        return highscores.get(i);
    }

    public void updateHighscore() {
        if (score > 0 && !highscores.contains(score)) {
            highscores.add(score);
        }
        Collections.sort(highscores, Collections.reverseOrder());
        while (highscores.size() > 5) {
            highscores.remove(highscores.size() - 1);
        }

        try (FileWriter writer = new FileWriter(scoreboardTextFile)) {
            for (int score : highscores) {
                writer.write(score + "\n");
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public void loadHighscoresFromFile() {
        highscores = new ArrayList<>();
        try (Scanner scanner = new Scanner(scoreboardTextFile)) {
            while (scanner.hasNextLine()) {
                String line = scanner.nextLine();
                highscores.add(Integer.parseInt(line));
            }
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        }
    }
    
    public List<Integer> highscores(){
    	return highscores;
    }
}
