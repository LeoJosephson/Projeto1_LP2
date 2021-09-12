package figures;

import java.awt.*;

public class Line extends Figure {
    int x2, y2;

    public Line (int x, int y, int x2, int y2, int r, int g, int b) {
        super(x, y, r, g, b);
        this.x2 = x2;
        this.y2 = y2;
        
    }

    public void print () {
        System.out.format("Line de  (%d,%d) ate (%d,%d).\n",
            this.x, this.y, this.x2, this.y2);
    }

    public void paint (Graphics g) {
        Graphics2D g2d = (Graphics2D) g;
        g2d.setColor(new Color(this.r, this.g, this.b));
        g2d.drawLine(this.x, this.y, this.x2, this.y2);
        
    }

}