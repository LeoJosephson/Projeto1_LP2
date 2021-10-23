import java.awt.*;
import java.awt.event.*;
import javax.swing.*;
import java.util.ArrayList;
import java.util.Random;
import java.io.*;

import figures.*;
import button.*;

class MainApp {
    public static void main (String[] args) {
        ListFrame frame = new ListFrame();
        frame.setVisible(true);

    }
}

class ListFrame extends JFrame {
    ArrayList<Figure> figs = new ArrayList<Figure>();
    ArrayList<button.Button> btns = new ArrayList<button.Button>();
    Random rand = new Random();
    
    button.Button focus_btn = null;
    Figure focus = null;
    Figure removed = null;

    ListFrame () {
        try {
            FileInputStream f = new FileInputStream("proj.bin");
            ObjectInputStream o = new ObjectInputStream(f);
            this.figs = (ArrayList<Figure>) o.readObject();
            o.close();
        } catch (Exception x){
        }

        this.addWindowListener (
            new WindowAdapter() {
                public void windowClosing (WindowEvent e) {
                    try{
                        FileOutputStream f = new FileOutputStream("proj.bin");
                        ObjectOutputStream o = new ObjectOutputStream(f);
                        o.writeObject(figs);
                        o.flush();
                        o.close();
                    } catch (Exception x){
                    }
                    System.exit(0);
                }
            }
        

        );

        // Criando os buttons
        int xb = 20;
        int yb;
        int wb = 40;
        int hb = 40;
        int indice = 1;
        Figure btnf;
        for (yb = 27; yb<=147; yb = yb + 40){

            if (yb==27) btnf = new Rect(xb,yb+10, wb,hb, 0, 0, 0, 255, 255, 255);
            else if (yb==67) btnf = new Ellipse(xb,yb+25, wb,hb, 0, 0, 0, 255, 255, 255);
            else if (yb==107) btnf = new Text(xb,yb+65, 255, 255, 255, "TEXT", 16, false);
            else btnf = new Triangle(xb,yb+95, wb-3,hb-3, 0, 0, 0, 255, 255, 255);
            btns.add(new button.Button(xb, yb+((yb/5)*2),wb, hb, indice, btnf));
            indice += 1;
        }

        this.addKeyListener (
            new KeyAdapter() {
                public void keyPressed (KeyEvent evt) {
                    Dimension size = getContentPane().getSize();
                    int rgb_max = 255;

                    Point p = getLocationOnScreen();
                    Point p_mouse = MouseInfo.getPointerInfo().getLocation();
                    int x = p_mouse.x - p.x;
                    int y = p_mouse.y - p.y; 

                    
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
                        focus_btn = null;
                    }
                    else if (evt.getKeyChar() == 't'){
                        int sizeof = 8 + rand.nextInt(32);
                        figs.add(new Text(x,y, r, g, b, "Hello World!", sizeof, true));
                        focus_btn = null;
                    }else if (evt.getKeyChar() == 'z'){
                        if (removed != null){
                            figs.add(removed);
                            removed = null;
                        }
                        focus_btn = null;
                    }
                    else if (focus != null){
                        if (evt.getKeyChar() == KeyEvent.VK_DELETE){
                            removed = focus;
                            figs.remove(focus);     
                        }
                        else if (evt.getKeyChar() == '+' || evt.getKeyChar() == '-'){
                                if (evt.getKeyChar() == '+') focus.resize(1);
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
                i = last_occurrence(figs, figs.size()-1, p1.x, p1.y);

                boolean new_btn_focus = false;
                if (i != -1){
                    Figure figure = figs.get(i);
                    figs.remove(i);
                    figs.add(figure);
                    focus_btn = null;
                } else {
                    for (button.Button btn: btns){
                        if (btn.clicked(p1.x, p1.y)){
                            focus_btn = btn;
                            focus = null;
                            new_btn_focus = true;
                        }
                    }
                }

                if(focus_btn !=  null){
                    if (i == -1 && new_btn_focus == false){
                        Figure f = focus_btn.create_figure(p1.x, p1.y);
                        figs.add(f);
                        focus_btn = null;
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
        for (button.Button btn: this.btns){
            if (btn == focus_btn) btn.paint(g,true);
            else btn.paint(g, false);
        }
        for (Figure fig: this.figs) {
            focus = figs.get(figs.size()-1);
            if (fig == this.focus && focus_btn == null) fig.paint(g, true);
            else fig.paint(g, false);
        }
    }

    private int last_occurrence(ArrayList<Figure> figs, int length, int x, int y){

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