package figures;

import java.awt.Graphics;
import ivisible.IVisible;
import java.io.Serializable;

public abstract class Figure implements IVisible, Serializable {
    public int x, y;
    public int r,g,b; 

    public Figure (int x, int y, int r, int g, int b) {
        this.x = x;
        this.y = y;
        this.r = r;
        this.g = g;
        this.b = b;
    }
    
    public abstract void paint (Graphics g, boolean focused);
    public abstract void resize (int d);
    public abstract void mov (int dx, int dy);
    public abstract boolean clicked(int ex, int ey);
    public abstract void focus(Graphics g);
    public abstract void change_color();
}