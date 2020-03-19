package matchthree;

import javax.swing.JFrame;
import javax.swing.SwingUtilities;
import javax.swing.UIManager;

import components.TitleLabel;

public class MatchThree extends JFrame{
    private static final long serialVersionUID = 1L;

    private void initGUI(){
        add(new TitleLabel("Match Three"));
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

        setTitle("Match three");
        setResizable(false);
        pack();
        setLocationRelativeTo(null);
        setVisible(true);
        setDefaultCloseOperation(EXIT_ON_CLOSE);
    }
}