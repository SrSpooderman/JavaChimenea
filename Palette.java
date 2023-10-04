import java.awt.*;
import java.lang.reflect.Array;
import java.util.ArrayList;
import java.util.Collections;

public class Palette {
    /*Parametros*/
    ArrayList<TempColorList> colorsPalette = new ArrayList<TempColorList>();
    ArrayList<TempColorList> colorsTarget = new ArrayList<TempColorList>();

    /*Constructor*/
    public Palette() {
        colorsTarget.add(new TempColorList(255,new Color(255,255,255,1)));
        colorsTarget.add(new TempColorList(1,new Color(99, 11, 1)));
    }

    /*Metodos*/
    public void showPalette(){
        for (int i=0; i< colorsPalette.size(); i++){
            System.out.println("Temp: "+colorsPalette.get(i).getTemperature()+" rgb: "+colorsPalette.get(i).getColor().getRed()+" "+colorsPalette.get(i).getColor().getGreen()+" "+colorsPalette.get(i).getColor().getBlue());
        }
    }
    public void showTargets(){
        for (int i=0; i< colorsTarget.size(); i++){
            System.out.println("Temp: "+colorsTarget.get(i).getTemperature()+" rgb: "+colorsTarget.get(i).getColor().getRed()+" "+colorsTarget.get(i).getColor().getGreen()+" "+colorsTarget.get(i).getColor().getBlue());
        }
    }

    public void addColorTarget(TempColorList Target){
        colorsTarget.add(Target);
        Collections.sort(colorsTarget, (color1,color2) -> Integer.compare(color1.getTemperature(), color2.getTemperature()));
    }

    public void calc(){
        colorsPalette.add(colorsTarget.get(0));

        for(int i = 0; i < colorsTarget.size()-1; i++){
            TempColorList currentTarget = colorsTarget.get(i);
            TempColorList nextTarget = colorsTarget.get(i+1);

            int diff = nextTarget.getTemperature()-currentTarget.getTemperature();
            for (int j = 1; j < diff; j++){
                TempColorList newColor = rgbDiff(currentTarget,nextTarget,j);
                colorsPalette.add(newColor);
            }
            colorsPalette.add(nextTarget);
        }
    }

    private TempColorList rgbDiff(TempColorList first, TempColorList last, int pasada){
        TempColorList result = new TempColorList();
        int diff = last.getTemperature()-first.getTemperature();

        double rdiff = Math.abs(first.getColor().getRed()-last.getColor().getRed()) /(double)diff;
        if (first.getColor().getRed() < last.getColor().getRed()){
            rdiff = first.getColor().getRed()+rdiff*pasada;
        } else {rdiff = first.getColor().getRed()-rdiff*pasada;};


        double gdiff = Math.abs(first.getColor().getGreen()-last.getColor().getGreen()) /(double)diff;
        if (first.getColor().getGreen() < last.getColor().getGreen()){
            gdiff = first.getColor().getGreen()+gdiff*pasada;
        } else {gdiff = first.getColor().getGreen()-gdiff*pasada;};

        double bdiff = Math.abs(first.getColor().getBlue()-last.getColor().getBlue()) /(double)diff;
        if (first.getColor().getBlue() < last.getColor().getBlue()){
            bdiff = first.getColor().getBlue()+bdiff*pasada;
        } else {bdiff = first.getColor().getBlue()-bdiff*pasada;};


        result.setTemperature(first.getTemperature()+pasada );
        result.setColor(new Color((int)rdiff,(int)gdiff,(int)bdiff,1));
        return result;
    }

    public TempColorList findColorPalette(int temperatura){
        for(TempColorList tempColor : colorsPalette){
            if(tempColor.getTemperature() == temperatura){
                return tempColor;
            }
        }
        return null;
    }
}
