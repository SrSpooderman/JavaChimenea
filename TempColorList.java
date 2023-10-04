import java.awt.Color;
public class TempColorList {
    private int temperature;
    private Color color;

    public TempColorList(int temperature, Color color) {
        this.temperature = temperature;
        this.color = color;
    }

    public TempColorList() {

    }

    public int getTemperature() {
        return temperature;
    }

    public void setTemperature(int temperature) {
        this.temperature = temperature;
    }

    public Color getColor() {
        return color;
    }

    public void setColor(Color color) {
        this.color = color;
    }
}
