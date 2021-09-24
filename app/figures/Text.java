package figures;
import java.util.Random;
import java.awt.*;
import java.util.List;
import java.util.Locale;


public class Text extends Figure {
    int w, h;
    String text;
    String family;
    int type;
    int size;
    Font font;
    Random rand = new Random();
    
    public Text (int x, int y, int r, int g, int b, String text, int size) {
        super(x, y, r, g, b);

        this.text = text;
        this.size = size;
        this.type = PickRandomType();
        this.family = PickRandomFontFamily();


    }
    
    public void print () {
        System.out.format("Texto na posicao (%d,%d) com (%s)\n",
            this.x, this.y, this.text);
    }

    public void paint (Graphics g) {
        Graphics2D g2d = (Graphics2D) g;
        this.font =  new Font(family, type, size);
        g2d.setFont(this.font);

        FontMetrics fm = g.getFontMetrics(this.font);
        
        this.w = fm.stringWidth(this.text);
        this.h = fm.getAscent();

        g2d.setColor(new Color(this.r, this.g, this.b));
        g2d.drawString(this.text, this.x, this.y);
    }

    private String PickRandomFontFamily() {
        Locale local_br = new Locale("pt", "BR");
        String[] fonts = GraphicsEnvironment.getLocalGraphicsEnvironment().getAvailableFontFamilyNames(local_br);
        return fonts[rand.nextInt(fonts.length)];
    }

    private int PickRandomType(){
        // 0 (PLAIN)  1 (BOLD)  2(ITALIC)
        return rand.nextInt(3);
    }

    public void grow(){
        this.size += 2;
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

}