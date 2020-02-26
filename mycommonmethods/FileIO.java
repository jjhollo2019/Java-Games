package mycommonmethods;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.URL;
import java.util.ArrayList;
import java.awt.image.BufferedImage;

import javax.imageio.ImageIO;
import javax.sound.sampled.AudioInputStream;
import javax.sound.sampled.AudioSystem;
import javax.sound.sampled.Clip;
import javax.sound.sampled.LineUnavailableException;
import javax.sound.sampled.UnsupportedAudioFileException;
import javax.swing.JOptionPane;

public class FileIO {
    public static Clip playClip(Object requestor, String fileName){
        Clip clip = null;
        try{
            URL url = requestor.getClass().getResource(fileName);
            AudioInputStream audioInputStream = AudioSystem.getAudioInputStream(url);
            clip = AudioSystem.getClip();
            clip.open(audioInputStream);
            clip.start();
        } catch(IOException e){
            String message = "File " + fileName + " could not be opened";
            JOptionPane.showMessageDialog(null, message);
        } catch (UnsupportedAudioFileException e){
            String message = "File " + fileName + " is not a valid audio type";
            JOptionPane.showMessageDialog(null, message);
        } catch(LineUnavailableException e){
            String message = "Resources not available to open file " + fileName + " at this time";
            JOptionPane.showMessageDialog(null, message);
        }
        return clip;
    }

    public static BufferedImage readImageFile(Object requestor, String fileName){
        BufferedImage image = null;
        try{
            InputStream input = requestor.getClass().getResourceAsStream(fileName);
            image = ImageIO.read(input);
        } catch(IOException e){
            String message = "The image file " + fileName + " can not be opened";
            JOptionPane.showMessageDialog(null, message);
        }
        return image;
    }

    public static ArrayList<String> readTextFile(Object requestor, String fileName){
        ArrayList<String> lines = new ArrayList<String>();
        try{
            InputStream input = requestor.getClass().getResourceAsStream(fileName);
            BufferedReader in = new BufferedReader(new InputStreamReader(input));
            String line = in.readLine();
            while(line != null){
                lines.add(line);
                line = in.readLine();
            }
            in.close();
        } catch(NullPointerException e){
            String message = "File " + fileName + " not found";
            JOptionPane.showMessageDialog(null, message);
        } catch(IOException e){
            String message = "File " + fileName + " can not be opened";
            JOptionPane.showMessageDialog(null, message);
        }
        return lines;
    }
}