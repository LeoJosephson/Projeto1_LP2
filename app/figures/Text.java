package figures;
import java.util.Random;

import java.awt.*;
import java.util.List;
import java.util.Locale;


public class Text extends Figure {
    private int w, h;
    private String text;
    private String family;
    private int type;
    private int size;
    private boolean random;
    private Random rand = new Random();
    
    public Text (int x, int y, int r, int g, int b, String text, int size, boolean random) {
        super(x, y, r, g, b);
        this.text = text;
        this.size = size;
        this.random = random;
        if (random == false){
            this.type = 1;
            this.family = "Times New Roman";
        } else{
            this.type = PickRandomType();
            this.family = PickRandomFontFamily();
        }
    }
    
    public void print () {
        System.out.format("Texto na posicao (%d,%d) com (%s)\n",
            this.x, this.y, this.text);
    }

    public void paint (Graphics g, boolean focused) {
        Graphics2D g2d = (Graphics2D) g;
        Font font =  new Font(family, type, size);
        g2d.setFont(font);
        
        FontMetrics fm = g.getFontMetrics(font);
        
        this.w = fm.stringWidth(this.text);
        this.h = fm.getAscent();

        if (focused){
            focus(g);
        }

        g2d.setColor(new Color(this.r, this.g, this.b));
        g2d.drawString(this.text, this.x, this.y);
    }

    private String PickRandomFontFamily() {
        Locale local_br = new Locale("pt", "BR");
        String[] fonts = GraphicsEnvironment.getLocalGraphicsEnvironment().getAvailableFontFamilyNames(local_br);

        return fonts[rand.nextInt(24)];
    }

    private int PickRandomType(){
        // 0 (PLAIN)  1 (BOLD)  2(ITALIC)
        return rand.nextInt(3);
    }

    public void resize(int d){
        if (this.size >= 8 && d<0) this.size += d;
        else if (d > 0) this.size += d;
    }

    public void mov(int dx, int dy){
        this.x += dx;
        this.y += dy;
    }

    public boolean clicked (int ex, int ey){
        if (ex >= this.x && ex<=this.x + this.w && ey<=this.y && ey>=this.y-this.h){
            return true;
        }
        return false;
    }

    void focus(Graphics g){
        int y1 = this.y - this.h;
        Graphics2D g2d = (Graphics2D) g;
        g2d.setColor(new Color(255, 0, 0));
        g2d.drawRect(this.x-2, y1-2, this.w+4, this.h+4);
    }

    public void change_color(){
    int rgb_max = 255;
    this.r = rand.nextInt(rgb_max);
    this.g = rand.nextInt(rgb_max);
    this.b = rand.nextInt(rgb_max);

    }
}