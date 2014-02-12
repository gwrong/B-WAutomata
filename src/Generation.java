import java.awt.Color;
import java.awt.Graphics;
import java.util.Random;

/**
* @author Graham Wright
*
* Represents a generation of cells
*/
public class Generation {

    public static final int WIDTH = 100;
    public static final int HEIGHT = 100;
    private Cell[][] cells;
    private Cell[][] newCells;
    private Random rand;
    
    /**
    * Constructor for a generation of cells
    */
    public Generation() {
        cells = new Cell[WIDTH][HEIGHT];
        rand = new Random();
        for (int i = 0; i < WIDTH; i++) {
            for (int j = 0; j < HEIGHT; j++) {
                cells[i][j] = new Cell(i, j);
                if (rand.nextInt(20) == 0) {
                    cells[i][j].setColor(Color.BLACK);
                }
            }
        }
        
        newCells = this.deepCopy(cells);
    }
 
    /**
    * Clears the generation/screen
    */ 
    public void clear() {
        cells = new Cell[WIDTH][HEIGHT];
        for (int i = 0; i < WIDTH; i++) {
            for (int j = 0; j < HEIGHT; j++) {
                cells[i][j] = new Cell(i, j);
            }
        }
        newCells = this.deepCopy(cells);
    }
    
    /**
    * Changes the state of the cell
    *
    * @param i, j Index of Cell
    * @param alive New State of cell
    */
    public void modifyCell(int i, int j, Color color) {
        cells[i][j].setColor(color);
    }
    
    /**
    * Toggles the state of the cell
    *
    * @param i, j Index of Cell
    */
    public void toggleCell(int i, int j) {
        Cell current = cells[i][j];
        current.setColor(Color.BLACK);
    }
    
    /**
    * Deep copy of toCopy
    *
    * @return The copied array of cells
    */
    public static Cell[][] deepCopy(Cell[][] toCopy) {
        Cell[][] result = new Cell[WIDTH][HEIGHT];
        for (int i = 0; i < WIDTH; i++) {
            for (int j = 0; j < HEIGHT; j++) {
                result[i][j] = new Cell(toCopy[i][j]);
            }
        }
        return result;
    }
 
    /**
    * Updates the generation using the specified rules
    */ 
    public void update() {
        int total = 0;
        for (int i = 0; i < WIDTH; i++) {
            for (int j = 0; j < HEIGHT; j++) {
                Cell cur = cells[i][j];
                int redTotal = 8 * cur.getRed();
                int greenTotal = 8 * cur.getGreen();
                int blueTotal = 8 * cur.getBlue();
                int[] previousColorValues = {redTotal, greenTotal, blueTotal};
                
                int[] finalColorValues = neighborValues(i, j, previousColorValues);
                newCells[i][j].setColor(new Color(finalColorValues[0], finalColorValues[1], finalColorValues[2]));
            }
        }
        cells = this.deepCopy(newCells);
    }
    
    private int[] neighborValues(int i, int j, int[] previousColorValues) {
        neighborValue(i-1, j+1, previousColorValues);
        neighborValue(i, j+1, previousColorValues);
        neighborValue(i+1, j+1, previousColorValues);
        neighborValue(i+1, j, previousColorValues);
        neighborValue(i+1, j-1, previousColorValues);
        neighborValue(i, j-1, previousColorValues);
        neighborValue(i-1, j-1, previousColorValues);
        neighborValue(i-1, j, previousColorValues);
        return averagesOf(previousColorValues);
    }

    
    private void neighborValue(int i, int j, int[] previousColorValues) {
        if (i < 0 || i >= WIDTH || j < 0 || j >= HEIGHT) {
            return;
        } else {
            previousColorValues[0] += cells[i][j].getRed();
            previousColorValues[1] += cells[i][j].getGreen();
            previousColorValues[2] += cells[i][j].getBlue();
        }
    }
    
    private static int[] averagesOf(int[] colorArray) {
        int[] averaged = {colorArray[0] / 16, colorArray[1] / 16, colorArray[2] / 16};
        return averaged;
    }
    
    
    /**
    * Draws generation to GUI
    */
    public void draw(Graphics g) {
        for (int i = 0; i < WIDTH; i++) {
            for (int j = 0; j < HEIGHT; j++) {
                cells[i][j].draw(g);
            }
        }
    }
}