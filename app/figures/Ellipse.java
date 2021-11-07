package figures;

import java.awt.*;
import java.awt.geom.Ellipse2D;
import java.awt.geom.Ellipse2D.Double;


public class Ellipse extends Figure2d {
    private Ellipse2D e;
    public Ellipse (int x, int y, int w, int h, int r, int g, int b, int fr, int fg, int fb) {
        super(x, y, w, h, r, g, b, fr, fg, fb);
    }

    public void print () {
        System.out.format("Ellipse de tamanho (%d,%d) na posicao (%d,%d).\n",
            this.w, this.h, this.x, this.y);
    }

    public void paint (Graphics g, boolean focused) {
        Graphics2D g2d = (Graphics2D) g;

        if (focused){
            focus(g);
        }

        g2d.setColor(new Color(this.fr, this.fg, this.fb));
        g2d.fill(new Ellipse2D.Double(this.x,this.y, this.w,this.h));
        g2d.setColor(new Color(this.r, this.g, this.b));
        this.e = new Ellipse2D.Double(this.x,this.y, this.w,this.h);
        g2d.draw(e);    
    }

    public boolean clicked(int ex, int ey){
        if (this.e.contains(ex,ey)) return true;
        return false;
    }

    void focus(Graphics g){
        Graphics2D g2d = (Graphics2D) g;
        g2d.setColor(new Color(255, 0, 0));
        Ellipse2D v = new Ellipse2D.Double(this.x-1,this.y-1, this.w+2,this.h+2);
        g2d.draw(v);
    }

}