import java.util.Random;

public class Temperature {
    private int [][]Temperatures;
    private int Height;
    private int Width;
    private double ColdPoints;
    private double SparkPoints;

    public Temperature(int width, int height, double cold, double spark){
        this.Height = height;
        this.Width = width;
        this.ColdPoints = cold;
        this.SparkPoints = spark;
        this.Temperatures = new int[width][height];
    }

    public int[][] getTemperatures() {
        return Temperatures;
    }

    public void setTemperatures(int[][] temperatures) {
        Temperatures = temperatures;
    }

    public int getHeight() {
        return Height;
    }

    public void setHeight(int height) {
        Height = height;
    }

    public int getWidth() {
        return Width;
    }

    public void setWidth(int width) {
        Width = width;
    }

    public double getColdPoints() {
        return ColdPoints;
    }

    public void setColdPoints(int coldPoints) {
        ColdPoints = coldPoints;
    }

    private double getSparkPoints() {
        return SparkPoints;
    }

    private void setSparkPoints(int sparkPoints) {
        SparkPoints = sparkPoints;
    }

    private void calc(){
        for (int h=getHeight()-2; h>4; h--){
            for (int w=2; w<getWidth()-2; w++){
                double newTemp = (
                        ((double) getTemperatures()[w][h] * 1.5D +
                                (double) getTemperatures()[w + 1][h] * 1.2D +
                                (double) getTemperatures()[w - 1][h] * 1.2D +
                                (double) getTemperatures()[w][h + 1] * 0.7D +
                                (double) getTemperatures()[w + 1][h + 1] * 0.7D +
                                (double) getTemperatures()[w - 1][h + 1] * 0.7D) / 5.98569D-0.8);
                getTemperatures()[w][h] = (int) newTemp;

                if (getTemperatures()[w][h] < 0){
                    getTemperatures()[w][h] = 0;
                } else if (getTemperatures()[w][h] > 255) {
                    getTemperatures()[w][h] = 255;
                }
            }
        }
    }

    public void createSparks(){
        Random rd = new Random();

        for (int w=0; w<getWidth(); w++){
            double randomNumber = rd.nextDouble();
            if (randomNumber < getSparkPoints()){
                this.Temperatures[w][getHeight()-1] = 255;
            }
        }
    }

    public void createCold(){
        Random rd = new Random();

        for (int w=0; w<getWidth(); w++){
            double randomNumber = rd.nextDouble();
            if (randomNumber < getColdPoints()){
                this.Temperatures[w][getHeight()-1] = 0;
            }
        }
    }

    public void show(){
        String result = "Plantilla \n";
        for (int h=0; h<getHeight();h++){
            for (int w = 0; w < getWidth(); w++){
                result += "["+getTemperatures()[w][h]+"]";
            }
            result += "\n";
        }
        System.out.println(result);
    }

    public void next(){
        createCold();
        createSparks();
        calc();
    }
}