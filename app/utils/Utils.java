package utils;

import java.awt.*;
import java.awt.event.*;
import java.util.ArrayList;
import java.util.Random;

import figures.*;


public class Utils{
    Random rand = new Random();

    public void NewFigure(KeyEvent evt, ArrayList<Figure> figs, Dimension size){
        int rgb_max = 255;                   
        int x = rand.nextInt(size.width);
        int y = rand.nextInt(size.height);
        int r = rand.nextInt(rgb_max);
        int g = rand.nextInt(rgb_max);
        int b = rand.nextInt(rgb_max);
        
        if (evt.getKeyChar() == 'r' || evt.getKeyChar() == 'e' || evt.getKeyChar() == 'a'){
            int w = 1+ rand.nextInt(50);
            int h = 1+ rand.nextInt(50);
            int fr = rand.nextInt(rgb_max);
            int fg = rand.nextInt(rgb_max);
            int fb = rand.nextInt(rgb_max);
            
            if (evt.getKeyChar() == 'r') {
                figs.add(new Rect(x,y, w,h, r, g, b, fr, fg, fb));
            } 
            else if (evt.getKeyChar() == 'e'){
                figs.add(new Ellipse(x,y, w,h, r, g, b, fr, fg, fb));
            }
            else if (evt.getKeyChar() == 'a'){
                figs.add(new Triangle(x,y, w,h, r, g, b, fr, fg, fb));
            }
        }
        else if (evt.getKeyChar() == 't'){
            int sizeof = rand.nextInt(64);
            figs.add(new Text(x,y, r, g, b, "Hello World!", sizeof));
        }
        else if (evt.getKeyChar() == 'l'){
            int x2 = rand.nextInt(size.width);
            int y2 = rand.nextInt(size.height);
            figs.add(new Line(x,y, x2, y2, r, g, b));
        }
    }

    public void Utilities(KeyEvent evt, ArrayList<Figure> figs){
        
    }
    // and so on...

}
/*if (evt.getKeyChar() == 'r' || evt.getKeyChar() == 'e' || evt.getKeyChar() == 'a' || evt.getKeyChar() == 't' || evt.getKeyChar() == 'l'){
                    Utils fig = new Utils();
                    fig.NewFigure(evt, figs, size);
                }*/