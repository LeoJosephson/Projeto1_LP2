package figures;

import java.util.Random;
import java.lang.Math.*;
import java.awt.*;

public class Triangle extends Figure2d {
    Random rand = new Random();
    private int[] coordsx = new int[3];
    private int[] coordsy = new int[3];
    private Polygon triangle;
    public Triangle (int x, int y, int w, int h, int r, int g, int b, int fr, int fg, int fb) {
        super(x, y, w, h, r, g, b, fr, fg, fb);
        this.coordsx[0] = this.x;
        this.coordsy[0] = this.y;
        this.coordsx[1] = this.x + rand.nextInt(this.w);
        this.coordsy[1] = this.y - this.h;
        this.coordsx[2] = this.x+this.w;
        this.coordsy[2] = this.y;
    }
    

    public void print () {
        System.out.format("Triangulo de tamanho (%d,%d)(largura, altura) na posicao (%d,%d).\n",
            this.w, this.h, this.x, this.y);
    }

    public void paint (Graphics g, boolean focused) {
        Graphics2D g2d = (Graphics2D) g;

        if (focused){
            focus(g);
        }

        g2d.setColor(new Color(this.fr, this.fg, this.fb));
        g2d.fillPolygon(this.coordsx, this.coordsy, 3);
        g2d.setColor(new Color(this.r, this.g, this.b));
        this.triangle = new Polygon(this.coordsx, this.coordsy, 3);
        g2d.drawPolygon(triangle);
    }

    public void resize(int d){
        if (d<0 && this.w > 8 && this.h > 8){
            this.w += 2 * d;
            this.h += d;
            this.coordsx[0] += -d;
            this.coordsy[1] += -d;
            this.coordsx[2] += d;
            int difx0 = this.coordsx[1] - this.coordsx[0];
            int difx2 = this.coordsx[2] - this.coordsx[1];
            if (this.coordsx[1] == this.coordsx[0] || this.coordsx[1] == this.coordsx[2]){
                if (difx0 > difx2) this.coordsx[1] += d;
                else this.coordsx[1] -= d;
            }
        }
        else if (d>0){
            this.w += 2 * d;
            this.h += d;
            this.coordsx[0] -= d;
            this.coordsy[1] -= d;
            this.coordsx[2] += d;
        }
    }

    public void mov(int dx, int dy){
        for (int i=0; i<=2; i++){
            this.coordsx[i] += dx;
            this.coordsy[i] += dy;
        }
    }

    public boolean clicked(int ex, int ey){
        if (this.triangle.contains(ex, ey)) return true;
        else return false;
    }

    protected void focus(Graphics g){
        Graphics2D g2d = (Graphics2D) g;
        g2d.setColor(new Color(255, 0, 0));
        int[] coordsxf = new int[3];
        int[] coordsyf = new int[3];
        coordsxf[0] = this.coordsx[0] -2;
        coordsxf[1] = this.coordsx[1];
        coordsxf[2] = this.coordsx[2] +2;
        coordsyf[0] = this.coordsy[0] +1;
        coordsyf[1] = this.coordsy[1] -1;
        coordsyf[2] = this.coordsy[2] +1;
        Polygon x = new Polygon(coordsxf, coordsyf, 3);
        g2d.drawPolygon(x);
    }
}