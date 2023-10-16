import java.awt.*;
import java.awt.image.BufferedImage;

public class FireAnimation extends BufferedImage {
    int width;
    int height;
    int posX;
    int posY;
    Temperatures temperatures;
    ColorPalette palette;

    public FireAnimation(int width, int height) {
        super(width, height, BufferedImage.TYPE_INT_ARGB);
        this.width = width;
        this.height = height;
        this.temperatures = new Temperatures(width, height, 0.09F, 0.055F);
        this.palette = new ColorPalette();
        createPalette();
    }

    public void next() {
        this.temperatures.next();
        this.initAllBlack();
        ColorTarget color;

        for (int w = 0; w < this.width; w++) {
            for (int h = 0; h < this.height-1; h++) {
                int temperature = this.temperatures.getTemp(w, h);
                if (temperature > 0) {
                    color = palette.getColor(temperature);
                    super.setRGB(w, h, color.color.getRGB());
                }
            }
        }
    }

    private void initAllBlack(){
        for (int w=0; w<this.width; w++){
            for (int h=0; h<this.height; h++){
                this.setRGB(w,h,new Color(0,0,0,0).getRGB());
            }
        }
    }

    private void createPalette(){
        palette.addColorTarget(new ColorTarget(255,new Color(255,255,255,255)));
        palette.addColorTarget(new ColorTarget(0,new Color(0, 0, 0,0)));
        palette.addColorTarget(new ColorTarget(54,new Color(0, 0, 0,100)));
        palette.addColorTarget(new ColorTarget(59,new Color(155, 0, 0,110)));
        palette.addColorTarget(new ColorTarget(72,new Color(200, 100, 0,180)));
        palette.addColorTarget(new ColorTarget(112,new Color(235,235 , 40,250)));
        palette.addColorTarget(new ColorTarget(129,new Color(255, 255, 200,255)));
        palette.addColorTarget(new ColorTarget(149,new Color(255, 255, 255,255)));
        palette.calc();
    }
}