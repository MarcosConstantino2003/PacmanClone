package Logica;

import java.awt.Canvas;
import java.awt.Dimension;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.Image;
import java.awt.image.BufferedImage;
import java.io.IOException;
import java.io.InputStream;
import java.util.Timer;
import java.util.TimerTask;

import javax.imageio.ImageIO;
import javax.swing.ImageIcon;
import javax.swing.JFrame;

import Entidades.Ghost;
import Entidades.Pacman;
import GUI.GameOverScreen;
import GUI.Leaderboard;
import GUI.LevelSelector;
import GUI.MainMenu;
import GUI.Map;
import GUI.Reloj;
import GUI.Rules;
import GUI.TableroPuntaje;
import GUI.TextoVidas;
import GUI.WinScreen;

public class Game extends Canvas implements Runnable {
	private static final long serialVersionUID = 1L;
	private boolean isRunning = false;
	public static int width = 720;
	public static int height = 480;
	public static String title = "Pac-Man - Constantino";
	public Thread hilo, pacmanHilo;
	public static Pacman pacman;
	public PacmanLogic pacmanlogica;
	public Level nivel;
	public Map mapa;
	public TableroPuntaje tableroPuntaje;
	private TextoVidas textoVidas;
	public Puntaje puntaje;
	public Ghost[] ghosts;
	private Ghost rosa, rojo, celeste, naranja;
	public static long startTime;
	public Reloj reloj;
	private SoundPlayer player;
	public int nivelActual;
	private MainMenu mainMenu;
	private Rules rulesScreen;
	private LevelSelector levelSelector;
	private GameOverScreen gameOverScreen;
	private WinScreen winScreen;
	private GameContext context;
	private static JFrame ventana;
	private Leaderboard leaderboard;

	// Constructor.
	public Game() {
		nivelActual = 1;
		nivel = new Level(nivelActual);
		mapa = nivel.getMap();
		// Create Pacman graphics.
		pacman = new Pacman(this);
		pacmanlogica = pacman.getLogica();
		Dimension dimension = new Dimension(Game.WIDTH, Game.HEIGHT);
		setPreferredSize(dimension);
		setMinimumSize(dimension);
		setMaximumSize(dimension);
		// Add Key Listener to use the keyboard.
		try {
			mainMenu = new MainMenu(this);
			rulesScreen = new Rules(this);
			levelSelector = new LevelSelector(this);
			tableroPuntaje = new TableroPuntaje(this);
			puntaje = new Puntaje(tableroPuntaje);
			winScreen = new WinScreen(this);
			leaderboard = new Leaderboard(this);

		} catch (IOException e) {
			e.printStackTrace();
		}
		addKeyListener(pacmanlogica);
		addKeyListener(mainMenu);
		addKeyListener(rulesScreen);
		addKeyListener(levelSelector);
		addKeyListener(leaderboard);

		textoVidas = new TextoVidas(this);
		startTime = System.currentTimeMillis(); // Initialize gameStartTime when the game starts
		gameOverScreen = new GameOverScreen();
		reloj = new Reloj(this);
		rosa = new Ghost(mapa, this, 1);
		rojo = new Ghost(mapa, this, 2);
		celeste = new Ghost(mapa, this, 3);
		naranja = new Ghost(mapa, this, 4);
		// Assign the ghosts to the array
		ghosts = new Ghost[] { rosa, rojo, celeste, naranja };
		player = new SoundPlayer();
		context = context.getInstance();
		// Load the image as a stream
		InputStream inputStream = getClass().getResourceAsStream("/img/icon.png");
		if (inputStream != null) {
			try {
				// Read the image from the stream
				BufferedImage bufferedImage = ImageIO.read(inputStream);
				// Create ImageIcon from the image
				ImageIcon icon = new ImageIcon(bufferedImage);
				// Set the icon image for the JFrame
				ventana.setIconImage(icon.getImage());
			} catch (IOException e) {
				e.printStackTrace();
			}
		} else {
			System.err.println("Image not found: /img/icon.png");
		}
	}

	// Method to start threads.
	public synchronized void start() {
		// If the game is not running, create and start a new thread.
		if (!isRunning) {
			isRunning = true;
			hilo = new Thread(this);
			hilo.start();
			pacmanHilo = new Thread(pacman);
			pacmanHilo.start();
		}
		player.playIntroSound();
	}

	// Method to stop the thread.
	public synchronized void stop() {
		// If the game is running, stop it. Otherwise, do nothing.
		if (isRunning) {
			isRunning = false;
			try {
				hilo.join();
				pacmanHilo.join();
			} catch (InterruptedException e) {
				e.printStackTrace();
			}
		}
	}

	// Method to update
	private void tick() {
		pacman.tick();
		for (Ghost g : ghosts)
			g.tick();
		if (nivel.beaten())
			win();
	}

	// Method to draw
	void render() {
		if (context.state() == 6) {
			// Create a BufferedImage to draw the map and the characters
			BufferedImage buffer = new BufferedImage(width, height, BufferedImage.TYPE_INT_RGB);
			Graphics2D g2d = buffer.createGraphics();
			// Draw the map and the characters to the BufferedImage
			mapa.drawLevel(g2d);
			tableroPuntaje.draw(g2d);
			textoVidas.draw(g2d);
			reloj.draw(g2d);
			for (Ghost g : ghosts)
				g.render(g2d);
			pacman.render(g2d);
			Graphics g = this.getGraphics();
			g.drawImage(buffer, 0, 0, null);
			// Free up resources of the graphics context
			g.dispose();
			g2d.dispose();
		}
	}

	// Method to keep the game running.
	public void run() {
		requestFocus();
		long lastTime = System.nanoTime();
		double targetTick = 60;
		double delta = 0.5;
		double nano = 1000000000 / targetTick;
		while (isRunning) {
			switch (context.state()) {
			case 0:
				mainMenu.setActive(true);
				rulesScreen.setActive(false);
				levelSelector.setActive(false);
				pacmanlogica.setActive(false);
				mainMenu.render(getGraphics());
				break;
			case 1:
				mainMenu.setActive(false);
				rulesScreen.setActive(true);
				levelSelector.setActive(false);
				pacmanlogica.setActive(false);
				rulesScreen.render(getGraphics());
				break;
			case 2:
				mainMenu.setActive(false);
				rulesScreen.setActive(false);
				levelSelector.setActive(true);
				pacmanlogica.setActive(false);
				levelSelector.render(getGraphics());
				break;
			case 3:
				mainMenu.setActive(false);
				rulesScreen.setActive(false);
				levelSelector.setActive(false);
				pacmanlogica.setActive(false);
				gameOverScreen.render(getGraphics());
				break;
			case 4:
				mainMenu.setActive(false);
				rulesScreen.setActive(false);
				levelSelector.setActive(true);
				pacmanlogica.setActive(false);
				winScreen.render(getGraphics());
				break;
			case 5:
				mainMenu.setActive(false);
				rulesScreen.setActive(false);
				levelSelector.setActive(false);
				pacmanlogica.setActive(false);
				leaderboard.setActive(true);
				leaderboard.render(getGraphics());
				break;
			case 6:
				pacmanlogica.setActive(true);
				long now = System.nanoTime();
				delta += (now - lastTime) / nano;
				lastTime = now;

				while (delta >= 1) {
					tick();
					render();
					delta--;
				}
				break;
			}
		}
		stop();
	}

	public static void main(String[] args) {
		// Create the window.
		ventana = new JFrame(title);
		Game juego = new Game();
		ventana.add(juego);
		ventana.setResizable(false);
		ventana.setSize(width, height);
		ventana.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		ventana.setVisible(true);

		// Start the main thread.
		juego.start();

	}

	public synchronized void reloadLevel() {
		reloadLevel(nivelActual);
	}

	public synchronized void reloadLevel(int level) {
		nivelActual = level;
		nivel = new Level(nivelActual);
		mapa = nivel.getMap();
		mapa.drawLevel(getGraphics());
		// Reset Pacman's position
		pacman = new Pacman(this);
		pacmanlogica = pacman.getLogica();
		addKeyListener(pacmanlogica);
		startTime = System.currentTimeMillis();
		resetEntities();
		textoVidas = new TextoVidas(this);
		puntaje.setScore(0);
		reloj.resetTimer();
		context.toPlaying();
		mainMenu.setActive(false);
		levelSelector.setActive(false);
		player.stopIntroSound();
		player.playMusic();
		repaint();
	}

	public synchronized void resetEntities() {
		// Despawn the old ghosts
		for (Ghost ghost : ghosts) {
			ghost.setSpawned(false); // Set the spawned flag to false
			ghost.setLocation(-100, -100); // Move them off-screen
		}
		startTime = System.currentTimeMillis();
		rosa = new Ghost(mapa, this, 1);
		rojo = new Ghost(mapa, this, 2);
		celeste = new Ghost(mapa, this, 3);
		naranja = new Ghost(mapa, this, 4);
		ghosts = new Ghost[] { rosa, rojo, celeste, naranja };
		pacmanlogica.movetoSpawnpoint();
		pacmanlogica.setDead(false);
	}

	public TableroPuntaje getTableroPuntaje() {
		return tableroPuntaje;
	}

	public TextoVidas getTextoVidas() {
		return textoVidas;
	}

	public GameOverScreen getGameOverScreen() {
		return gameOverScreen;
	}

	public void gameOver() {
		context.toGameOver();
		Timer soundTimer = new Timer();
		soundTimer.schedule(new TimerTask() {
			public void run() {
				player.playGameOverSound();
				player.stopMusic();
				soundTimer.cancel(); // Cancel the timer after playing the sound
			}
		}, 3000); // Delay in milliseconds (3 seconds)
		Timer timer = new Timer();
		timer.schedule(new TimerTask() {
			public void run() {
				toMainMenu();
				timer.cancel(); // Cancel the timer after transitioning to the main menu
			}
		}, 8000); // Delay in milliseconds (8 seconds)
	}

	public void win() {
		context.toWinScreen();
		player.stopMusic();
		player.playWinSound();
		puntaje.updateHighscore();
		Timer timer = new Timer();
		timer.schedule(new TimerTask() {
			public void run() {
				toMainMenu();
				timer.cancel(); // Cancel the timer after transitioning to the main menu
			}
		}, 5000); // Delay in milliseconds (3 seconds)
	}

	public void toMainMenu() {
		player.stopMusic();
		puntaje.updateHighscore();
		context.toMainMenu();
		player.playIntroSound();
	}

	public void toRules() {
		context.toRules();
	}

	public void toLevels() {
		context.toLevels();
	}

	public void toLeaderboard() {
		context.toLeaderboard();
	}

	public void pauseGame(int milliseconds) {
		try {
			hilo.sleep(milliseconds);
		} catch (InterruptedException e) {
			e.printStackTrace();
		}
	}

	public SoundPlayer player() {
		return player;
	}

}
