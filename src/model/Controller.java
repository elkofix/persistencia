package src.model;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.stream.Stream;

public class Controller {

    public Controller(){
        hoardings = new Hoarding[1];
    }
    public Hoarding[] getHoardings() {
        return hoardings;
    }

    public String showHoarding(){
        String msj = "W        H      inUse     Brand\n";
        int count = 0;
        for (int i = 0; i < hoardings.length ; i++) {
            if(hoardings[i]!=null){
                msj+=hoardings[i].toString()+"\n";
                count++;
            }

        }
        return msj+"TOTAL: "+count+" vallas";
    }

    public String showHazardousHoard(){
        String msj = "===========================\n"+
                "DANGEROUS BILLBOARD REPORT\n"+
                "===========================\n"+
                "The dangerous billboard are:\n";
        int count = 1;
        if(hoardings!=null) {
            for (int i = 0; i < hoardings.length; i++) {
                if(hoardings[i]!=null){
                    int area = hoardings[i].getHeight() * hoardings[i].getWidth();
                    if (area >= 200000) {
                        msj += count + 1 + ". Billboard " + hoardings[i].getBrand() + " with area " + area + "\n";
                        count++;
                    }
                }

            }
        }
        return msj;
    }
    public void addHoarding(String width, String height, String inUse,String brand) {
        Hoarding newH = new Hoarding(Integer.parseInt(width), Integer.parseInt(height), Boolean.parseBoolean(inUse), brand);
        Hoarding[] temp = {newH};
        this.hoardings = Stream.of(hoardings, temp).flatMap(Stream::of).toArray(Hoarding[]::new);
    }

    public void addHoardings(Hoarding[] temp)  {
        this.hoardings = Stream.of(hoardings, temp).flatMap(Stream::of).toArray(Hoarding[]::new);
    }
    public void setHoardings(Hoarding[] hoardings) {
        this.hoardings = hoardings;
    }



    private Hoarding[] hoardings;
}
