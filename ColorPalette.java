import java.awt.*;
import java.util.ArrayList;
import java.util.Collections;

public class ColorPalette {
    ArrayList<ColorTarget> colorsPalette = new ArrayList<ColorTarget>();
    ArrayList<ColorTarget> colorsTarget = new ArrayList<ColorTarget>();

    public ColorPalette(){

    }

    public void addColorTarget(ColorTarget Target){
        colorsTarget.add(Target);
        Collections.sort(colorsTarget, (color1, color2) -> Integer.compare(color1.getTemperature(), color2.getTemperature()));
    }

    public ColorTarget getColor(int temp){
        for(ColorTarget tempColor : colorsPalette){
            if (tempColor.getTemperature() == temp){
                return tempColor;
            }
        }
        return null;
    }

    private ColorTarget calcIntervalColors(ColorTarget first, ColorTarget last, int pasada){
        ColorTarget result = new ColorTarget();
        int diff = last.getTemperature()-first.getTemperature();

        double rdiff = Math.abs(first.getR()-last.getR()) /(double)diff;
        if (first.getR() < last.getR()){
            rdiff = first.getR()+rdiff*pasada;
        } else {rdiff = first.getR()-rdiff*pasada;};


        double gdiff = Math.abs(first.getG()-last.getG()) /(double)diff;
        if (first.getG() < last.getG()){
            gdiff = first.getG()+gdiff*pasada;
        } else {gdiff = first.getG()-gdiff*pasada;};

        double bdiff = Math.abs(first.getB()-last.getB()) /(double)diff;
        if (first.getB() < last.getB()){
            bdiff = first.getB()+bdiff*pasada;
        } else {bdiff = first.getB()-bdiff*pasada;};

        double adiff = Math.abs(first.getA()-last.getA()) /(double)diff;
        if (first.getA() < last.getA()){
            adiff = first.getA()+adiff*pasada;
        } else {adiff = first.getA()-adiff*pasada;};

        result.temperature=first.getTemperature()+pasada;
        result.color=new Color((int)rdiff,(int)gdiff,(int)bdiff,(int)adiff);
        return result;
    }

    public void calc(){
        colorsPalette.add(colorsTarget.get(0));

        for(int i = 0; i < colorsTarget.size()-1; i++){
            ColorTarget currentTarget = colorsTarget.get(i);
            ColorTarget nextTarget = colorsTarget.get(i+1);

            int diff = nextTarget.getTemperature()-currentTarget.getTemperature();
            for (int j = 1; j < diff; j++){
                ColorTarget newColor = calcIntervalColors(currentTarget,nextTarget,j);
                colorsPalette.add(newColor);
            }
            colorsPalette.add(nextTarget);
        }
    }
}
