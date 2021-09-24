package figures;

import java.awt.*;

public class Rect extends Figure2d {

    public Rect (int x, int y, int w, int h, int r, int g, int b, int fr, int fg, int fb) {
        super(x, y, w, h, r, g, b, fr, fg, fb);
    }

    public void print () {
        System.out.format("Retangulo de tamanho (%d,%d) na posicao (%d,%d).\n",
            this.w, this.h, this.x, this.y);
    }

    public void paint (Graphics g) {
        Graphics2D g2d = (Graphics2D) g;
        g2d.setColor(new Color(this.fr, this.fg, this.fb));
        g2d.fillRect(this.x, this.y, this.w, this.h);
        g2d.setColor(new Color(this.r, this.g, this.b));
        g2d.drawRect(this.x,this.y, this.w,this.h);
    }

    public void mov (int dx, int dy){

        this.x += dx;
        this.y += dy;
    }
}