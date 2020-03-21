package matchthree;

import java.awt.GridLayout;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.Graphics;
import java.awt.event.MouseEvent;
import java.awt.event.MouseAdapter;

import javax.swing.JPanel;

public class BallPanel extends JPanel{
    private static final long serialVersionUID = 1L;
    private static final int ROWS = 10;
    private static final int COLS = 6;
    private static final int WIDTH = COLS * Cell.SIZE;
    private static final int HEIGHT = ROWS * Cell.SIZE;
    private static final int DIRECTION_NONE = 0;
    private static final int DIRECTION_LEFT = 1;
    private static final int DIRECTION_RIGHT = 2;
    private static final int DIRECTION_UP = 3;
    private static final int DIRECTION_DOWN = 4;
    private MatchThree game;
    private Cell[][] cells = new Cell[ROWS][COLS];

    private Integer getSwapDirection(int x, int y){
        int direction = DIRECTION_NONE;

        //which cell was clicked?
        int cellSize = Cell.SIZE;
        int col = x / cellSize;
        int row = y / cellSize;

        //how far was the click from each edge?
        int left = x % cellSize;
        int right = cellSize - left;
        int top = y % cellSize;
        int bottom = cellSize - top;

        //if not in the first column and left edge is closest
        if(col > 0 && left <= right && left <= top && left <= bottom){
            direction = DIRECTION_LEFT;
        }

        //if not in the last column and right edge is closest 
        else if(col < COLS - 1 && right <= left && right <= top && right <= bottom){
            direction = DIRECTION_RIGHT;
        }

        //if not in first row and top edge is closest
        else if(row > 0 && top <= left && top <= right && top <= bottom){
            direction = DIRECTION_UP;
        } 

        //if not in last row and bottom edge is closest 
        else if(row < ROWS - 1 && bottom <= left && bottom <= right && bottom <= top){
            direction = DIRECTION_DOWN;
        }

        return direction;
    }

    private void swap(int row1, int col1, int row2, int col2){
        Cell temp = new Cell();
        temp.copy(cells[row1][col1]);
        cells[row1][col1].copy(cells[row2][col2]);
        cells[row2][col2].copy(temp);
        repaint();
    }

    private void clicked(int x, int y){
        int row = y / Cell.SIZE;
        int col = x / Cell.SIZE;
        int direction = getSwapDirection(x, y);
        switch(direction){
            case DIRECTION_LEFT:
                swap(row, col, row, col - 1);
                break;
            case DIRECTION_RIGHT:
                swap(row, col, row, col + 1);
                break;
            case DIRECTION_UP:
                swap(row, col, row - 1, col);
                break;
            case DIRECTION_DOWN:
                swap(row, col, row + 1, col);
                break;
        }
    }

    public BallPanel(MatchThree game){
        this.game = game;
        setLayout(new GridLayout(ROWS, COLS));
        setInitialBalls();

        addMouseListener(new MouseAdapter(){
            public void mouseReleased(MouseEvent e){
                int x = e.getX();
                int y = e.getY();
                clicked(x, y);
            }
        });
    }

    public void setInitialBalls(){
        for(int row = 0; row < ROWS; row++){
            for(int col = 0; col < COLS; col++){
                cells[row][col] = new Cell();
            }
        }
        repaint();
    }

    public void paintComponent(Graphics g){
        //background
        g.setColor(Color.BLACK);
        g.fillRect(0, 0, WIDTH, HEIGHT);

        //cells
        for(int row = 0; row < ROWS; row++){
            for(int col = 0; col < COLS; col++){
                int x = col * Cell.SIZE;
                int y = row * Cell.SIZE;
                cells[row][col].draw(g, x, y);
            }
        }
    }

    public Dimension getPrefferedSize(){return new Dimension(WIDTH, HEIGHT);}
}