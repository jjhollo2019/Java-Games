package mytimer;

import java.awt.BorderLayout;
import java.awt.Font;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.SwingUtilities;
import javax.swing.UIManager;

import components.TitleLabel;

public class MyTimer extends JFrame {
    private static final long serialVersionUID = 1L;
    private Font font = new Font(Font.DIALOG, Font.BOLD, 36);
    private TimerPanel timerPanel = new TimerPanel(10L, font);

    private void initGUI(){
        TitleLabel titleLabel = new TitleLabel("My Timer");
        add(titleLabel, BorderLayout.PAGE_START);
        JPanel centerPanel = new JPanel();
        add(centerPanel, BorderLayout.CENTER);
    }

    public MyTimer(){
        initGUI();
        setTitle("MyTimer");
        setResizable(false);
        pack();
        setLocationRelativeTo(null);
        setVisible(true);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
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
}