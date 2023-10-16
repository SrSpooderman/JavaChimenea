import java.awt.*;

public class ColorTarget {
    int temperature;
    Color color;

    public ColorTarget(int temperature, Color color) {
        this.temperature = temperature;
        this.color = color;
    }
    public ColorTarget(){

    }

    public int getA(){
        return color.getAlpha();
    }

    public int getR(){
        return color.getRed();
    }

    public int getG(){
        return color.getGreen();
    }

    public int getB(){
        return color.getBlue();
    }

    public int getTemperature(){
        return temperature;
    }
}
