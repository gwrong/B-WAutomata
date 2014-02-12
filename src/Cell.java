import java.awt.Color;
import java.awt.Graphics;
import java.util.Random;
import java.awt.Rectangle;


/**
* @author Graham Wright
*
* Represents a cell in the game of life
*/
public class Cell {
    
    public static final int WIDTH = 10;
    public static final int HEIGHT = 10;
    private Rectangle bounds;
    private Color color;
    
    /**
    * Constructor for a Cell
    *
    * @param x The x index of the cell in the array
    * @param y The y index of the cell in the array
    */
    public Cell(int x, int y) {
        bounds = new Rectangle(x * WIDTH, y * HEIGHT, WIDTH, HEIGHT);
        color = Color.WHITE;
    }
    
    /**
    * Copy Constructor for a Cell
    *
    * @param that The Cell being copied
    */
    public Cell(Cell that) {
        this.bounds = that.bounds;
        this.color = that.color;
    }
    
    /**
    * Returns whether or not the cell is White
    *
    * @return Whether or not the cell is white
    */
    public boolean isWhite() {
        return color.equals(Color.WHITE);
    }

    public void setColor(Color color) {
        this.color = color;
    }
    
    /**
    * Returns the red value
    *
    * @return The red value
    */
    public int getRed() {
        return color.getRed();
    }
    
    /**
    * Returns the green value
    *
    * @return The green value
    */
    public int getGreen() {
        return color.getGreen();
    }
    
    /**
    * Returns the blue value
    *
    * @return The blue value
    */
    public int getBlue() {
        return color.getBlue();
    }
    
    /**
    * Draws the cell to the screen
    */
    public void draw(Graphics g) {
        g.setColor(color);
        g.fillRect((int) bounds.getX(), (int) bounds.getY(), WIDTH, HEIGHT);
        g.setColor(Color.BLACK);
        g.drawRect((int) bounds.getX(), (int) bounds.getY(), WIDTH, HEIGHT);
        
    }
}