import javax.imageio.ImageIO;
import java.awt.*;
import java.awt.image.BufferStrategy;
import java.awt.image.BufferedImage;
import java.io.File;

public class Viewer extends Canvas {
    private BufferedImage backgroundImg;
    private FireAnimation foregroundImg;
    private BufferStrategy bs;

    public Viewer(int pixelW, int pixelH, FireAnimation foregroundImg){
        Dimension d = new Dimension(pixelW, pixelH);
        this.setPreferredSize(d);
        this.loadBackground();
        this.foregroundImg = foregroundImg;
        this.bs = null;
    }

    public void paintBackground(){
        if (this.bs == null){
            this.createBufferStrategy(2);
            bs = this.getBufferStrategy();
        }

        bs.getDrawGraphics().drawImage(this.backgroundImg, 0,0,this.getWidth(),this.getHeight(),null);
        bs.show();
        bs.getDrawGraphics().dispose();
    }

    public void paintForeground(){
        if (this.bs == null) {
            this.createBufferStrategy(2);
            bs = this.getBufferStrategy();
        }

        bs.getDrawGraphics().drawImage(this.foregroundImg, (int) (this.getWidth() / 2.32), (int) (this.getHeight() / 1.65), (int) (this.getHeight() / 2.3273), (int) (this.getWidth() / 5.3895), null);
        this.foregroundImg.next();

        bs.show();
        bs.getDrawGraphics().dispose();
    }

    private void loadBackground(){
        try{
            this.backgroundImg = ImageIO.read(new File("C:/Users/pfran/Downloads/bg.jpg"));
        }catch (Exception e){
            System.err.println(e);
        }
    }
}
