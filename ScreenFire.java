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
        setSize(this.Width, this.Height);
        this.image = new BufferedImage(this.Width,this.Height,BufferedImage.TYPE_INT_RGB);
        this.PixelSize = PixelSize;

        this.temp = new Temperature(this.Width,this.Height,40,60);
        this.temp.next();
        createPalete();

        Timer timer = new Timer(100, new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
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
                image.setRGB(w,h,0x00000000);
            }
        }
    }

    private void initScreen() {
        clearImage();
        this.temp.next();
        TempColorList color;

        for (int w=0; w<this.Width; w++){
            for (int h=0; h<this.Height; h++){
                int temperature = this.temp.getTemperatures()[w][h];
                if (temperature > 0){
                    color = paleta.findColorPalette(temperature);
                    image.setRGB(w,(this.Height-1)-h, color.getColor().getRGB());
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
        paleta.addColorTarget(new TempColorList(252,new Color(247, 210, 106)));
        paleta.addColorTarget(new TempColorList(210,new Color(250, 166, 57)));
        paleta.addColorTarget(new TempColorList(168, new Color(250, 128, 17)));
        paleta.addColorTarget(new TempColorList(126, new Color(242, 81, 1)));
        paleta.addColorTarget(new TempColorList(84, new Color(197, 49, 1)));
        paleta.addColorTarget(new TempColorList(42,new Color(131, 20, 3)));
        paleta.calc();
    }
}
