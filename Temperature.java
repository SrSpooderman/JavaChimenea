import java.util.Random;

public class Temperature {
    private int [][]Temperatures;
    private int Height;
    private int Width;
    private int ColdPoints;
    private int SparkPoints;

    public Temperature(int width, int height, int cold, int spark){
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

    public int getColdPoints() {
        return ColdPoints;
    }

    public void setColdPoints(int coldPoints) {
        ColdPoints = coldPoints;
    }

    private int getSparkPoints() {
        return SparkPoints;
    }

    private void setSparkPoints(int sparkPoints) {
        SparkPoints = sparkPoints;
    }

    private void calc(){
        for (int h = getHeight()-1; h>=1;h--){
            for (int w=0; w<getWidth(); w++){
                int avg = 0;
                int div = 3;

                avg += getTemperatures()[w][h-1];
                try {
                    avg += getTemperatures()[w+1][h-1];
                }catch (ArrayIndexOutOfBoundsException e) {
                    div = 2;
                }
                try {
                    avg += getTemperatures()[w-1][h-1];
                }catch (ArrayIndexOutOfBoundsException e) {
                    div = 2;
                }
                avg = avg/div;
                getTemperatures()[w][h] = avg;
            }
        }
    }

    public void createSparks(){
        Random rd = new Random();

        for (int w=0; w<getWidth(); w++){
            int randomNumber = rd.nextInt(100+1);
            if (randomNumber < getSparkPoints()){
                this.Temperatures[w][0] = 255;
            }
        }
    }

    public void createCold(){
        Random rd = new Random();

        for (int w=0; w<getWidth(); w++){
            int randomNumber = rd.nextInt(100+1);
            if (randomNumber < getColdPoints()){
                this.Temperatures[w][0] = 1;
            }
        }
    }

    public void show(){
        String result = "Plantilla \n";
        for (int h = getHeight()-1; h>=0;h--){
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