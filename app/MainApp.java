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
    
    Figure focus = null;
    Figure removed = null;


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
                    int x, y;
                    Point p_mouse = MouseInfo.getPointerInfo().getLocation();
                    
                    if (p_mouse.x <= size.width && p_mouse.y <= size.height){
                        x = p_mouse.x;
                        y = p_mouse.y;
                    } else{
                        x = rand.nextInt(size.width);
                        y = rand.nextInt(size.height);
                    }
                    int r = rand.nextInt(rgb_max);
                    int g = rand.nextInt(rgb_max);
                    int b = rand.nextInt(rgb_max);

                    if (evt.getKeyChar() == 'r' || evt.getKeyChar() == 'e' || evt.getKeyChar() == 'a'){
                        int w = 8+ rand.nextInt(50);
                        int h = 8+ rand.nextInt(50);
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
                        int sizeof = 8 + rand.nextInt(32);
                        figs.add(new Text(x,y, r, g, b, "Hello World!", sizeof));
                    }else if (evt.getKeyChar() == 'z'){
                        if (removed != null){
                            figs.add(removed);
                            removed = null;
                        }
                    }
                    else if (focus != null){
                        if (evt.getKeyChar() == KeyEvent.VK_DELETE){
                            removed = focus;
                            figs.remove(focus);     
                        }
                        else if (evt.getKeyChar() == '+' || evt.getKeyChar() == '-'){
                                if (evt.getKeyChar() == '+') focus.resize(2);
                                else focus.resize(-1);
                        }
                        else if (evt.getKeyChar() == 'c'){
                            focus.change_color();
                        }
                        else if (evt.getKeyCode() == KeyEvent.VK_DOWN || evt.getKeyCode() == KeyEvent.VK_LEFT || evt.getKeyCode() == KeyEvent.VK_RIGHT || evt.getKeyCode() == KeyEvent.VK_UP ) {
                            int dx = 0;
                            int dy = 0;
                            if(evt.getKeyCode() == KeyEvent.VK_DOWN) dy = 2;
                            else if (evt.getKeyCode() == KeyEvent.VK_UP){ dy = -2;}
                            else if (evt.getKeyCode() == KeyEvent.VK_RIGHT) dx = 2;
                            else if (evt.getKeyCode() == KeyEvent.VK_LEFT) dx = -2;
                                                
                            focus.mov(dx,dy);
                        }
                    }
                    repaint(); 
                }
                
            }
        );
        MouseAdapter m = new MouseAdapter(){
            Point p1;
            int i = -1;

            public void mouseDragged(MouseEvent e){
                if (i != -1){
                    Point pt = e.getPoint();
                    
                    int dx = ((int)(pt.x - p1.x));
                    int dy = ((int)(pt.y - p1.y));

                    p1.x = p1.x + dx;
                    p1.y = p1.y + dy;
                    focus.mov(dx, dy);

                }
            repaint();
            }
            public void mousePressed(MouseEvent e){
                p1 = e.getPoint();

                if (figs.size() > 0){
                    i = last_occurrence(figs, figs.size()-1, p1.x, p1.y);

                    if (i != -1){
                        Figure figure = figs.get(i);
                        figs.remove(i);
                        figs.add(figure);
                    }   
                }
            repaint();
            }
                
        };
        this.addMouseListener(m);
        this.addMouseMotionListener(m);

        this.setTitle("Trabalho de LP2");
        this.setSize(450, 450);
    }

    public void paint (Graphics g) {
        super.paint(g);
        for (Figure fig: this.figs) {
            fig.paint(g);
            focus = figs.get(figs.size()-1);
            if (fig == focus){
                fig.focus(g);
            }
        }
    }

    public int last_occurrence(ArrayList<Figure> figs, int length, int x, int y){

        if (length == -1){ 
            return -1;
        }
        Figure fig = figs.get(length);
        
        if (fig.clicked(x,y)){
            return length;
        } else{
            return last_occurrence(figs, length-1, x, y);
        }
    }
}