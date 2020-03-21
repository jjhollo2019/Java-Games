package matchthree;

import java.awt.BorderLayout;
import java.awt.Color;

import javax.swing.BoxLayout;
import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.SwingUtilities;
import javax.swing.UIManager;

import components.ScorePanel;
import components.TitleLabel;

public class MatchThree extends JFrame{
    private static final long serialVersionUID = 1L;
    private ScorePanel scorePanel = new ScorePanel(0, Color.GREEN);
    private BallPanel ballPanel = new BallPanel(this);

    private void initGUI(){
        add(new TitleLabel("Match Three"), BorderLayout.PAGE_START);

        //main panel
        JPanel mainPanel = new JPanel();
        mainPanel.setBackground(Color.GREEN);
        mainPanel.setLayout(new BoxLayout(mainPanel, BoxLayout.Y_AXIS));
        add(mainPanel, BorderLayout.CENTER);

        //score panel
        mainPanel.add(scorePanel);

        //ball panel
        mainPanel.add(ballPanel);

        //button panel


    }

    public static void main(String[] args){
        try{
            UIManager.setLookAndFeel(UIManager.getCrossPlatformLookAndFeelClassName());
        } catch(Exception e){
            //do nothing
        }

        SwingUtilities.invokeLater(new Runnable(){
            public void run(){
                new MatchThree();
            }
        });
    }

    public MatchThree(){
        initGUI();
        setTitle("Match Three");
        setResizable(false);
        pack();
        setLocationRelativeTo(null);
        setVisible(true);
        setDefaultCloseOperation(EXIT_ON_CLOSE);
    }
}