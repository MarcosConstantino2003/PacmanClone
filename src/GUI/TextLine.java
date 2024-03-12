package GUI;
import java.awt.Graphics;
import java.awt.image.BufferedImage;

public class TextLine {
    private BufferedImage[] charTextArray;
    private int x, y;
    private boolean alignedLeft;

    public TextLine (BufferedImage[] charTextArray, int x, int y, boolean alignedLeft){
        this.charTextArray = charTextArray;
        this.x = x;
        this.y = y;
        this.alignedLeft = alignedLeft;
    }

    public void render(Graphics g) {
        int dx = x;
        if (!alignedLeft) {
            for (BufferedImage character : charTextArray){
                dx -= character.getWidth();
            }
            dx--;
        }
        for (BufferedImage character : charTextArray){
            g.drawImage(character, dx, y, null);
            dx += character.getWidth();
        }
    }
}
