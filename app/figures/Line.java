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
    
    public void grow(){
        if (x2 > x) {
            this.x2 += 2;
            this.y2 += 2;
        }
        else {
            this.x += 2;
            this.y += 2;
        }

            
    }

    public void mov(int dx, int dy){
        this.x += dx;
        this.x2 += dx;
        this.y += dy;
        this.y2 += dy;
    }

    public boolean clicked(int ex, int ey){
        return click(this.x, this.y, this.x2, this.y2, ex, ey);

    }

    public boolean click(int x1, int x2, int y1, int y2, int ex, int ey){
        return false;
    }


    public void focus(Graphics g){
        Graphics2D g2d = (Graphics2D) g;
        g2d.setColor(new Color(255, 0, 0));
        g2d.drawLine(this.x, this.y, this.x2, this.y2);
    }
}