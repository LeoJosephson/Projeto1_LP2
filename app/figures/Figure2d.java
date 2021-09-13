package figures;


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

    public void grow(){
        this.w += 2;
        this.h += 2;
    }
}