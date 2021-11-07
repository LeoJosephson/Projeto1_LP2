package button;


import figures.*;
import java.awt.*;

public abstract class ButtonFig extends Button{

    public ButtonFig(int x, int y, int w, int h, Figure fig){
        super(x, y, w, h, fig);
    }

    public void paint(Graphics g, boolean focused){
        Graphics2D g2d = (Graphics2D) g;
        if (focused){
            focus(g);
        }
        else{
            g2d.setColor(new Color(128, 128, 128));
            g2d.fillRect(this.x-2, this.y-2, this.w+4, this.h+4);
            g2d.setColor(new Color(0, 0, 0));
            g2d.drawRect(this.x-2, this.y-2, this.w+4, this.h+4);
        }
        this.fig.paint(g, false);
    }

    public void focus(Graphics g){
        Graphics2D g2d = (Graphics2D) g;
        g2d.setColor(new Color(100, 100, 100));
        g2d.fillRect(this.x-2, this.y-2, this.w+4, this.h+4);
        g2d.setColor(new Color(0, 0, 0));
        g2d.drawRect(this.x-2, this.y-2, this.w+4, this.h+4);
    }

}

