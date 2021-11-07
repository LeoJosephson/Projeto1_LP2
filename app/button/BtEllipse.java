package button;

import figures.*;
import java.util.Random;

public class BtEllipse extends ButtonFig{

    public BtEllipse (int x, int y, int w, int h, Figure fig){
        super(x, y, w, h, fig);
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

        f = new Ellipse(x, y, 20, 20, r,g,b, fr, fg, fb);
        return f;
    }
}