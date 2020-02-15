package speedwords;

import java.awt.Graphics;
import java.awt.Dimension;

import javax.swing.JPanel;

public class GamePanel extends JPanel{
    private static final long serialVersionUID = 1L;
    private static int WIDTH = 500;
    private static int HEIGHT = 300;
    private SpeedWords speedWords;

    public GamePanel(SpeedWords speedWords){
        this.speedWords = speedWords;
    }

    public void paintComponent(Graphics g){
        //draw background
        g.setColor(SpeedWords.TAN);
        g.fillRect(WIDTH, HEIGHT, 0, 0);
    }

    public Dimension getPreferredSize(){
        Dimension size = new Dimension(WIDTH, HEIGHT);
        return size;
    }
}