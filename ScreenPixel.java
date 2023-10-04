import javax.swing.*;
import java.awt.*;
import java.awt.image.BufferedImage;

public class ScreenPixel extends JFrame {
    private BufferedImage image = new BufferedImage(200,200,BufferedImage.TYPE_INT_RGB);
    public ScreenPixel() {
        setTitle("Pixel");
        setSize(200,200);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setVisible(true);

        initScreen();
    }

    private void initScreen(){
        image.setRGB(100,100, new Color(225,23,12).getRGB());
        repaint();
    }

    @Override
    public void paint(Graphics g) {
        super.paint(g);
        g.drawImage(image, 0, 0, this);
    }
}