import java.util.Random;

public class Temperatures {
    int width;
    int height;
    int [][]Temperatures;
    float coldPointsPercentatge;
    float sparkPercentatge;

    public Temperatures(int width, int height, float coldPointsPercentatge, float sparkPercentatge) {
        this.width = width;
        this.height = height;
        this.coldPointsPercentatge = coldPointsPercentatge;
        this.sparkPercentatge = sparkPercentatge;
        this.Temperatures = new int[width][height];
    }

    public int getTemp(int x,int y){
        return Temperatures[x][y];
    }

    public void next(){
        createColdPoints();
        createSparks();
        calcTemperatures();
    }

    public void calcTemperatures(){
        for (int h=height-2;h>4;h--){
            for (int w=2;w<width-2;w++){
                double newTemp;
                newTemp =(
                        Temperatures[w][h]*1.5D+
                        Temperatures[w+1][h]*1.2D+
                        Temperatures[w-1][h]*1.2D+
                        Temperatures[w][h+1]*0.7D+
                        Temperatures[w+1][h+1]*0.7D+
                        Temperatures[w-1][h+1]*0.7D)/5.98569D-.2D;
                Temperatures[w][h] = (int) newTemp;

                if (Temperatures[w][h] <0){
                    Temperatures[w][h]=0;
                } else if (Temperatures[w][h]>255) {
                    Temperatures[w][h]=255;
                }
            }
        }
    }

    public void createColdPoints(){
        Random rd = new Random();

        for (int w=0;w<width;w++){
            float randomNumber = rd.nextFloat();
            if (randomNumber < coldPointsPercentatge){
                Temperatures[w][height-1] = 0;
            }
        }
    }

    public void createSparks(){
        Random rd = new Random();

        for (int w=0;w<width;w++){
            float randomNumber = rd.nextFloat();
            if (randomNumber < coldPointsPercentatge){
                Temperatures[w][height-1] = 255;
            }
        }
    }
}
