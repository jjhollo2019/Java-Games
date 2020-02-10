package mytimer;

import javax.swing.JOptionPane;
import javax.swing.JPanel;
import java.awt.Graphics;
import java.awt.Color;
import java.awt.Font;
import java.awt.FontMetrics;
import java.awt.Dimension;

public class TimerPanel extends JPanel{
    private static final long serialVersionUID = 1L;
    private int width = 150;
    private int height = 24;
    private String timeString = "00:00:00";
    private long time = 10L;

    public TimerPanel(long time, Font font){
        this.time = time;
        setFont(font);
        height = font.getSize();
        FontMetrics fm = getFontMetrics(font);
        width = fm.stringWidth(timeString);
    }

    public void paintComponent(Graphics g){
        super.paintComponent(g);
        g.setColor(Color.BLACK);
        g.drawString(timeString, 0, height);
    }
    public Dimension getPreferredSize(){
        Dimension size = new Dimension(width, height);
        return size;
    }

    public void setTime(long time){
        this.time = time;
        long h = time/3600L;
        long m = time/60L;
        long s = time%60L;
        timeString = String.format("%02d:%02d:%02d", h, m, s);
        repaint();
    }

    public void start(){
        while(time > 0){
            time--;
            setTime(time);
            System.out.println(time);
            try{
                Thread.sleep(5000);
            } catch(InterruptedException e){
                e.printStackTrace();
                return;
            }
        }
        timesUp();
    }

    protected void timesUp(){
        String message = "Times Up!";
        JOptionPane.showMessageDialog(this, message);
    }
}