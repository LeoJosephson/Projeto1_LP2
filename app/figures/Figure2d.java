package figures;
import java.awt.*;

public abstract class Figure2d extends Figure {
    public int w, h;
    public int fr, fg, fb;
    public Figure2d (int x, int y, int w, int h, int r, int g, int b, int fr, int fg, int fb) {
        super(x, y, r, g, b);
        this.w = w;
        this.h = h;
        this.fr = fr;
        this.fg = fg;
        this.fb = fb;
    }

    public void grow(int d){
        if (d<0 && this.w >= 8 && this.h >= 8){
            this.w += d;
            this.h += d;
        }
        else if (d>0){
            this.w += d;
            this.h += d;
        }
    }

    public void resize (int dx, int dy){

        this.x += dx;
        this.y += dy;
    }

    public boolean clicked (int ex, int ey){
        if (ex >= this.x && ex<=this.x + this.w && ey>=this.y && ey<=this.y+this.h){
            return true;
        }
        return false;


    }

    public void focus(Graphics g){
        Graphics2D g2d = (Graphics2D) g;
        g2d.setColor(new Color(255, 0, 0));
        g2d.drawRect(this.x-2, this.y-2, this.w+4, this.h+4);
    }

}