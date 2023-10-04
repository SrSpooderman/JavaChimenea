import javax.imageio.ImageIO;
import javax.swing.*;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;

public class ScreenImage extends JFrame {
    File backgroundImage = new File("");
    public ScreenImage(File Archivo, String titulo) {
        setTitle(titulo);
        setSizeImage(Archivo);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        this.backgroundImage = Archivo;
        initScreen();
    }

    private void initScreen(){
        try{
            BufferedImage backImg = ImageIO.read(backgroundImage);
            JLabel sticker = new JLabel(new ImageIcon(backImg));
            getContentPane().add(sticker);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    private void setSizeImage(File image){
        try {
            BufferedImage imageBuff = ImageIO.read(image);
            if (imageBuff != null){
                int ancho = imageBuff.getWidth();
                int alto = imageBuff.getHeight();
                setSize(ancho,alto);
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
