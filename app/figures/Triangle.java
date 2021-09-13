package figures;

import java.util.Random;

import java.awt.*;

public class Triangle extends Figure2d {
    Random rand = new Random();
    int[] coordsx = new int[3];
    int[] coordsy = new int[3];
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

    public void paint (Graphics g) {
        Graphics2D g2d = (Graphics2D) g;
        g2d.setColor(new Color(this.fr, this.fg, this.fb));
        g2d.fillPolygon(this.coordsx, this.coordsy, 3);
        g2d.setColor(new Color(this.r, this.g, this.b));
        g2d.drawPolygon(this.coordsx, this.coordsy, 3);    
    }

    public void grow(){
        this.coordsx[0] -= 1;
        this.coordsy[1] -= 1;
        this.coordsx[2] += 1;
    }
}