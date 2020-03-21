package matchthree;

import java.awt.Color;
import java.awt.Graphics;
import java.util.Random;

public class Cell{
    private static final Color[] COLORS = {Color.BLUE, Color.CYAN, Color.GREEN, Color.MAGENTA, Color.RED, Color.WHITE, Color.YELLOW};
    public static final int SIZE = 50;
    private int color = -1;
    private boolean inChain = false;
    private Random rand = new Random();

    public Cell(){
        color = rand.nextInt(COLORS.length);
    }

    public void draw(Graphics g, int x, int y){
        //draw the background
        if(inChain){
            g.setColor(Color.DARK_GRAY);
        } else {
            g.setColor(Color.BLACK);
        }
        g.fillRect(x, y, SIZE, SIZE);
        //draw the ball
        if(color > -1){
            g.setColor(COLORS[color]);
            g.fillOval(x + 8, y + 8, SIZE - 16, SIZE - 16);
        }
    }

    public void setEmpty(){
        color = -1;
        inChain = false;
    }

    public void copy(Cell cell){
        color = cell.getColor();
        inChain = false;
    }

    public Integer getColor(){return color;}

    public boolean isEmpty(){return color == -1;}

    public boolean isInChain(){return inChain;}

    public void setInChain(boolean inChain){this.inChain = inChain;}
}