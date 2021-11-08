package button;

import java.util.Random;
import figures.*;
import ivisible.IVisible;
import java.awt.*;

public abstract class Button implements IVisible{
    protected Figure fig;
    protected int x, y;
    protected int w, h;

    public Button(int x, int y, int w, int h, Figure fig){
        this.x = x;
        this.y = y;
        this.w = w;
        this.h = h;
        this.fig = fig;
    }

    abstract void focus(Graphics g);
    public abstract Figure create_figure(int x, int y);
    public boolean clicked (int ex, int ey){
        if (ex >= this.x && ex<=this.x + this.w && ey>=this.y && ey<=this.y+this.h){
            return true;
        }
        return false;
    }

}