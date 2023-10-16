import javax.imageio.ImageIO;
import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
public class ScreenFire extends JPanel {
    private int Width;
    private int Height;
    private BufferedImage image;
    private Palette paleta = new Palette();
    private Temperature temp;
    private int PixelSize;

    public ScreenFire(int PixelSize, int width, int height){
        this.Width = width;
        this.Height = height;
        setOpaque(false);
        setSize(this.Width, this.Height);
        this.image = new BufferedImage(this.Width,this.Height,BufferedImage.TYPE_INT_ARGB);
        this.PixelSize = PixelSize;

        this.temp = new Temperature(this.Width,this.Height,0.09,0.055); //cold 0,09 spark 0,055
        createPalete();

        Timer timer = new Timer(100, new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                clearImage();
                initScreen();
                repaint();
            }
        });
        timer.start();
    }

    public void setWidth(int width) {
        Width = width;
    }

    public void setHeight(int height) {
        Height = height;
    }

    public BufferedImage getImage() {
        return image;
    }

    public void setImage(BufferedImage image) {
        this.image = image;
    }

    public Palette getPaleta() {
        return paleta;
    }

    public void setPaleta(Palette paleta) {
        this.paleta = paleta;
    }

    public Temperature getTemp() {
        return temp;
    }

    public void setTemp(Temperature temp) {
        this.temp = temp;
    }

    public int getPixelSize() {
        return PixelSize;
    }

    public void setPixelSize(int pixelSize) {
        PixelSize = pixelSize;
    }

    private void clearImage(){
        for (int w=0; w<this.Width; w++){
            for (int h=0; h<this.Height; h++){
                image.setRGB(w,h,new Color(0,0,0,0).getRGB());
            }
        }
    }

    private void initScreen() {
        this.temp.next();
        TempColorList color;

        for (int w=0; w<this.Width; w++){
            for (int h=0; h<this.Height; h++){
                int temperature = this.temp.getTemperatures()[w][h];
                if (temperature > 0){
                    color = paleta.findColorPalette(temperature);
                    image.setRGB(w,h, color.getColor().getRGB());
                }
            }
        }
    }

    @Override
    public void paint(Graphics g) {
        super.paint(g);
        g.drawImage(image, 0, 0, this);
    }

    private void createPalete(){
        paleta.addColorTarget(new TempColorList(54,new Color(0, 0, 0,100)));
        paleta.addColorTarget(new TempColorList(59,new Color(155, 0, 0,110)));
        paleta.addColorTarget(new TempColorList(72,new Color(200, 100, 0,180)));
        paleta.addColorTarget(new TempColorList(112,new Color(235,235 , 40,250)));
        paleta.addColorTarget(new TempColorList(129,new Color(255, 255, 200,255)));
        paleta.addColorTarget(new TempColorList(149,new Color(255, 255, 255,255)));
        paleta.calc();
    }
}
