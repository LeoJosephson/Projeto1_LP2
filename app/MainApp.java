import java.awt.*;
import java.awt.event.*;
import javax.swing.*;
import java.util.ArrayList;
import java.util.Random;


import figures.*;

class MainApp {
    public static void main (String[] args) {
        ListFrame frame = new ListFrame();
        frame.setVisible(true);
    }
}

class ListFrame extends JFrame {
    ArrayList<Figure> figs = new ArrayList<Figure>();
    Random rand = new Random();

    ListFrame () {
        this.addWindowListener (
            new WindowAdapter() {
                public void windowClosing (WindowEvent e) {
                    System.exit(0);
                }
            }
        );

        this.addKeyListener (
            new KeyAdapter() {
                public void keyPressed (KeyEvent evt) {
                Dimension size = getContentPane().getSize();
                int rgb_max = 255;
                         
                int x = rand.nextInt(size.width);
                int y = rand.nextInt(size.height);
                int r = rand.nextInt(rgb_max);
                int g = rand.nextInt(rgb_max);
                int b = rand.nextInt(rgb_max);

                if (evt.getKeyChar() == 'r' || evt.getKeyChar() == 'e' || evt.getKeyChar() == 'a'){
                    int w = 1+ rand.nextInt(50);
                    int h = 1+ rand.nextInt(50);
                    int fr = rand.nextInt(rgb_max);
                    int fg = rand.nextInt(rgb_max);
                    int fb = rand.nextInt(rgb_max);
                    
                    if (evt.getKeyChar() == 'r') {
                        figs.add(new Rect(x,y, w,h, r, g, b, fr, fg, fb));
                    } 
                    else if (evt.getKeyChar() == 'e'){
                        figs.add(new Ellipse(x,y, w,h, r, g, b, fr, fg, fb));
                    }
                    else if (evt.getKeyChar() == 'a'){
                        figs.add(new Triangle(x,y, w,h, r, g, b, fr, fg, fb));
                    }
                }
                else if (evt.getKeyChar() == 't'){
                    int sizeof = rand.nextInt(64);
                    figs.add(new Text(x,y, r, g, b, "Hello World!", sizeof));
                }
                else if (evt.getKeyChar() == 'l'){
                    int x2 = rand.nextInt(size.width);
                    int y2 = rand.nextInt(size.height);
                    figs.add(new Line(x,y, x2, y2, r, g, b));
                }
                else if (evt.getKeyChar() == KeyEvent.VK_DELETE){
                    if (figs.size() > 0) figs.remove(figs.size()-1);
                }
                else if (evt.getKeyChar() == 'g'){
                    if (figs.size() > 0){
                        Figure fig = figs.get(figs.size() -1);
                        fig.grow();
                        figs.set(figs.size() -1, fig);
                    }
                }
                else if (evt.getKeyCode() == KeyEvent.VK_DOWN || evt.getKeyCode() == KeyEvent.VK_LEFT || evt.getKeyCode() == KeyEvent.VK_RIGHT || evt.getKeyCode() == KeyEvent.VK_UP ) {
                    if (figs.size() > 0){
                        int dx = 0;
                        int dy = 0;
                        if(evt.getKeyCode() == KeyEvent.VK_DOWN) dy = 2;
                        else if (evt.getKeyCode() == KeyEvent.VK_UP){ dy = -2;}
                        else if (evt.getKeyCode() == KeyEvent.VK_RIGHT) dx = 2;
                        else if (evt.getKeyCode() == KeyEvent.VK_LEFT) dx = -2;
                            
                        
                        Figure fig = figs.get(figs.size() -1);
                        fig.mov(dx,dy);
                        figs.set(figs.size() -1, fig);
                    }
                }
                
                repaint(); 
                }
                
            }
        );

        this.setTitle("Lista de Figures");
        this.setSize(350, 350);
    }

    public void paint (Graphics g) {
        super.paint(g);
        for (Figure fig: this.figs) {
            fig.paint(g);
        }
    }

    /* Método para deletar ultimo objeto
    public ArrayList<Figure> del (ArrayList<Figure> figs){
        figs.remove(figs.size()-1);
        return figs;
    }
    */
}