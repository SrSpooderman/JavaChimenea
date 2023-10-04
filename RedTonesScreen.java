import java.awt.Color;
import java.awt.Graphics;
import java.awt.image.BufferedImage;
import javax.swing.JFrame;
import javax.swing.JPanel;

public class RedTonesScreen extends JPanel {

    private BufferedImage image;

    public RedTonesScreen(int width, int height) {
        image = new BufferedImage(width, height, BufferedImage.TYPE_INT_RGB);
        createRedTones();
    }

    private void createRedTones() {
        for (int x = 0; x < image.getWidth(); x++) {
            for (int y = 0; y < image.getHeight(); y++) {
                // Genera tonos de rojo variando el valor de rojo de 0 a 255
                int red = x % 256;
                int pixelColor = new Color(red, 0, 0).getRGB();
                image.setRGB(x, y, pixelColor);
            }
        }
    }

    @Override
    protected void paintComponent(Graphics g) {
        super.paintComponent(g);
        g.drawImage(image, 0, 0, this);
    }

    public static void main(String[] args) {
        int screenWidth = 800;
        int screenHeight = 600;
        JFrame frame = new JFrame("Red Tones Screen");
        RedTonesScreen redTonesScreen = new RedTonesScreen(screenWidth, screenHeight);
        frame.add(redTonesScreen);
        frame.setSize(screenWidth, screenHeight);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setVisible(true);
    }
}
