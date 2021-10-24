package button;

import java.util.Random;
import figures.*;
import ivisible.IVisible;
import java.awt.*;

public class Button implements IVisible{
    public int index;
    private Figure fig;
    public int x, y;
    public int w, h;

    public Button(int x, int y, int w, int h, int index, Figure fig){
        this.x = x;
        this.y = y;
        this.w = w;
        this.h = h;
        this.index = index;
        this.fig = fig;

    }

    public boolean clicked (int ex, int ey){
        if (ex >= this.x && ex<=this.x + this.w && ey>=this.y && ey<=this.y+this.h){
            return true;
        }
        return false;
    }

    public void paint(Graphics g, boolean focused){
        Graphics2D g2d = (Graphics2D) g;
        if (focused){
            focus(g);
        }
        else{
            g2d.setColor(new Color(128, 128, 128));
            g2d.fillRect(this.x-2, this.y-2, this.w+4, this.h+4);
            g2d.setColor(new Color(0, 0, 0));
            g2d.drawRect(this.x-2, this.y-2, this.w+4, this.h+4);
        }
        this.fig.paint(g, false);
    }

    public void focus(Graphics g){
        Graphics2D g2d = (Graphics2D) g;
        g2d.setColor(new Color(100, 100, 100));
        g2d.fillRect(this.x-2, this.y-2, this.w+4, this.h+4);
        g2d.setColor(new Color(0, 0, 0));
        g2d.drawRect(this.x-2, this.y-2, this.w+4, this.h+4);
    }

    public Figure create_figure(int x, int y){
        Figure f;
        Random rand = new Random();
        int rgb_max = 255;
        int r, g, b, fr, fg, fb;
        r = rand.nextInt(rgb_max);
        g = rand.nextInt(rgb_max);
        b = rand.nextInt(rgb_max);
        fr = rand.nextInt(rgb_max);
        fg = rand.nextInt(rgb_max);
        fb = rand.nextInt(rgb_max);
        if (this.index == 1) return (new Rect(x, y, 20, 20, r,g,b, fr, fg, fb));
        else if (this.index == 2) return(new Ellipse(x, y, 20, 20, r,g,b, fr, fg, fb));
        else if (this.index == 3) return(new Text(x, y, r,g,b, "Hello World!", 16, true));
        else return(new Triangle(x, y, 20, 20, r,g,b, fr, fg, fb));

    }
}

