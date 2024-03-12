package Logica;

public class GameContext {
	
    private static GameContext instance;

	private boolean isMainMenu, isRules, isGameOver, isLevelSelector;
	private boolean isWinScreen, isPlaying, isInLeaderboard;

	private GameContext() {
        // Private constructor to prevent instantiation from outside
    }
	
    public static GameContext getInstance() {
        if (instance == null) {
            instance = new GameContext();
        }
        return instance;
    }
        
	
	private void resetContext() {
		isMainMenu=false;
		isRules=false;
		isGameOver=false;
		isLevelSelector=false;
		isWinScreen = false;
		isPlaying = false;
		isInLeaderboard = false;
	}
	
	public void toLevels() {
		resetContext();
		isLevelSelector=true;
	}
	
	public void toRules() {
		resetContext();
		isRules=true;
	}
	
	public void toMainMenu() {
		resetContext();
		isMainMenu=true;
	}
	
	public void toGameOver() {
		resetContext();
		isGameOver=true;
	}
	
	public void toWinScreen() {
		resetContext();
		isWinScreen=true;
	}
	public void toPlaying() {
		resetContext();
		isPlaying=true;
	}
	
	public void toLeaderboard() {
		resetContext();
		isInLeaderboard=true;
	}
	
	public int state() {
		if (isMainMenu)
			return 0;
		else if(isRules)
			return 1;
		else if (isLevelSelector)
			return 2;
		else if (isGameOver)
			return 3;
		else if (isWinScreen)
			return 4;
		else if (isInLeaderboard)
			return 5;
		else if (isPlaying)
			return 6;
		else
			return 0;

	}
}
