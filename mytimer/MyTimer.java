package mytimer;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Font;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.SwingUtilities;
import javax.swing.UIManager;

import components.TitleLabel;

public class MyTimer extends JFrame {
    private static final long serialVersionUID = 1L;
    private Font font = new Font(Font.DIALOG, Font.BOLD, 36);
    private TimerPanel timerPanel = new TimerPanel(0, font);

    private void initGUI(){
        TitleLabel titleLabel = new TitleLabel("My Timer");
        add(titleLabel, BorderLayout.PAGE_START);
        JPanel centerPanel = new JPanel();
        add(centerPanel, BorderLayout.CENTER);
        timerPanel.setBackground(Color.MAGENTA);
        centerPanel.add(timerPanel);
        centerPanel.setBackground(Color.MAGENTA);
        JPanel buttonPanel = new JPanel();
        buttonPanel.setBackground(Color.BLACK);
        add(buttonPanel, BorderLayout.PAGE_END);
        JButton hoursButton = new JButton();
        hoursButton.setText("Hour");
        hoursButton.addActionListener(new ActionListener(){
            public void actionPerformed(ActionEvent e){
                addAnHour();
            }
        });
        buttonPanel.add(hoursButton);
        JButton minutesButton = new JButton();
        minutesButton.setText("Minute");
        minutesButton.addActionListener(new ActionListener(){
            public void actionPerformed(ActionEvent e){
                addAMinute();
            }
        });
        buttonPanel.add(minutesButton);
        JButton clearButton = new JButton();
        clearButton.setText("Clear");
        clearButton.addActionListener(new ActionListener(){
            public void actionPerformed(ActionEvent e){
                clear();
            }
        });
        buttonPanel.add(clearButton);
        JButton startButton = new JButton();
        startButton.setText("Start");
        startButton.addActionListener(new ActionListener(){
            public void actionPerformed(ActionEvent e){
                start();
            }
        });
        buttonPanel.add(startButton);
        JButton stopButton = new JButton();
        stopButton.setText("Stop");
        stopButton.addActionListener(new ActionListener(){
            public void actionPerformed(ActionEvent e){
                stop();
            }
        });
        buttonPanel.add(stopButton);
    }

    public MyTimer(){
        initGUI();
        setTitle("MyTimer");
        setResizable(false);
        pack();
        setLocationRelativeTo(null);
        setVisible(true);
        setDefaultCloseOperation(EXIT_ON_CLOSE);
    }

    public static void main(String[] args){
        try{
            String classname = UIManager.getCrossPlatformLookAndFeelClassName();
            UIManager.setLookAndFeel(classname);
        } catch(Exception e){
            e.printStackTrace();
        }

        SwingUtilities.invokeLater(new Runnable(){
            public void run(){
                new MyTimer();
            }
        });
    }

    public void addAnHour(){
        long time = timerPanel.getTime();
        time += 3600;
        timerPanel.setTime(time);
    }

    public void addAMinute(){
        long time = timerPanel.getTime();
        time += 60;
        timerPanel.setTime(time);
    }

    public void clear(){
        timerPanel.stop();
        timerPanel.setTime(0);
    }

    public void start(){timerPanel.start();}

    public void stop(){timerPanel.stop();}
}