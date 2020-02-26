package speedwords;

import java.awt.Graphics;
import java.awt.event.MouseEvent;
import java.awt.event.MouseAdapter;
import java.util.ArrayList;
import java.util.Random;
import java.awt.Dimension;

import javax.swing.JPanel;

import mycommonmethods.FileIO;

public class GamePanel extends JPanel{
    private static final long serialVersionUID = 1L;
    private static final String FILE_NAME = "/Assets/enable1_7.txt";
    private static int WIDTH = 500;
    private static int HEIGHT = 300;
    private static final int START_X = WIDTH / 2 - 7 * LetterTile.SIZE / 2;
    private static final int START_Y = HEIGHT / 2 - LetterTile.SIZE / 2;
    private SpeedWords speedWords;
    private ArrayList<TileSet> tileSets = new ArrayList<TileSet>();
    private ArrayList<String> sevenLetterWords = new ArrayList<String>();
    private Random rand = new Random();
    private TileSet movingTiles;
    private int mouseX;
    private int mouseY;

    public GamePanel(SpeedWords speedWords){
        this.speedWords = speedWords;
        sevenLetterWords = FileIO.readTextFile(this, FILE_NAME);
        restart();

        addMouseListener(new MouseAdapter(){
            public void mousePressed(MouseEvent e){
                int x = e.getX();
                int y = e.getY();
                clicked(x, y);
            }

            public void mouseReleased(MouseEvent e){
                released();
            }
        });

        addMouseMotionListener(new MouseAdapter(){
            public void mouseDragged(MouseEvent e){
                int x = e.getX();
                int y = e.getY();
                dragged(x, y);
            }
        });
    }

    private void dragged(int x, int y){
        if(movingTiles != null){
            int changeX = x - mouseX;
            int changeY = y - mouseY;
            movingTiles.changeXY(changeX, changeY);
            mouseX = x;
            mouseY = y;
            repaint();
        }
    }

    private void released(){
        //if dropped on other tiles, connect it to the tiles


        //if not dropped on other tiles, return it to the list of tile sets
        if(movingTiles != null){
            String s = movingTiles.toString();
            int x = movingTiles.getX();
            int y = movingTiles.getY();
            TileSet newTileSet = new TileSet(s, x, y);
            tileSets.add(newTileSet);
            movingTiles = null;
        }
        repaint();
    }

    private void clicked(int x, int y){
        if(movingTiles == null){
            mouseX = x;
            mouseY = y;

            for(int i = 0; i < tileSets.size(); i++){
                TileSet tileSet = tileSets.get(i);
                if(tileSet.contains(mouseX, mouseY)){
                    movingTiles = tileSet;
                    tileSets.remove(i);
                }
            }
            repaint();
        }
    }

    public void paintComponent(Graphics g){
        //draw background
        g.setColor(SpeedWords.TAN);
        g.fillRect(0, 0, WIDTH, HEIGHT);
        
        //draw all current tile sets 
        for(int i = tileSets.size() - 1; i >= 0; i--){
            TileSet tileSet = tileSets.get(i);
            tileSet.draw(g);
        }

        //draw moving tiles
        if(movingTiles != null){
            movingTiles.draw(g);
        }
    }

    public Dimension getPreferredSize(){
        Dimension size = new Dimension(WIDTH, HEIGHT);
        return size;
    }

    public void restart(){
        tileSets.clear();
        int range = sevenLetterWords.size();
        int choose = rand.nextInt(range);
        String s = sevenLetterWords.get(choose);
        TileSet tileSet = new TileSet(s, START_X, START_Y);
        tileSets.add(tileSet);
        repaint();
    }
}