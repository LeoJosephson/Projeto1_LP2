package button;
import figures.*;
import java.util.Random;

public class BtText extends ButtonFig{

    public BtText (int x, int y, int w, int h, Figure fig){
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
        f = new Text(x, y, r,g,b, "Hello World!", 16, true);

        return f;
    }
    
}