package mytimer;

import mycommonmethods.FileIO;
import javax.sound.sampled.Clip;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import java.awt.Graphics;
import java.awt.Color;
import java.awt.Font;
import java.awt.FontMetrics;
import java.awt.Dimension;

public class TimerPanel extends JPanel implements Runnable{
    private static final long serialVersionUID = 1L;
    private static final String ALARM_FILE = "/Assets/alarm.wav";
    private int width = 150;
    private int height = 24;
    private String timeString = "00:00:00";
    private long time = 10L;
    private Thread timerThread;

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
        long h = time/3600;
        long m = (time/60)%60;
        long s = time%60;
        timeString = String.format("%02d:%02d:%02d", h, m, s);
        repaint();
    }

    public void start(){
        timerThread = new Thread(this);
        timerThread.start();
    }

    public void stop(){
        if(timerThread != null){
            timerThread.interrupt();
            timerThread = null;
        }
    }

    protected void timesUp(){
        Clip clip = FileIO.playClip(this, "/Assets/alarm.wav");
        String message = "Time's Up!";
        JOptionPane.showMessageDialog(this, message);
        clip.stop();
    }

    public void run(){
        while(time > 0){
            time--;
            setTime(time);
            try{
                Thread.sleep(1000);
            } catch(InterruptedException e){
                e.printStackTrace();
                return;
            }
        }
        timesUp();
    }

    public long getTime(){
        return time;
    }
}