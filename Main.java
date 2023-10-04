import javax.swing.*;
import java.io.File;

public class Main {
    public static void main (String args[]){
        JFrame frame = new JFrame("Fuegito");
        ScreenFire fire = new ScreenFire(1,200,500);
        frame.add(fire);
            frame.setSize(200,500);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setVisible(true);
    }
}
