package figures;
import java.util.Random;
import java.awt.*;
import java.util.List;
import java.util.Locale;

public class Text extends Figure {

    String text;
    String font;
    String type;
    int size;
    Random rand = new Random();
    
    public Text (int x, int y, int r, int g, int b, String text, int size) {
        super(x, y, r, g, b);

        this.text = text;
        this.type = PickRandomType();
        this.size = size;
        this.font = PickRandomFontFamily();
    }

    

    public void print () {
        System.out.format("Texto na posicao (%d,%d) com (%s)\n",
            this.x, this.y, this.text);
    }

    public void paint (Graphics g) {
        Graphics2D g2d = (Graphics2D) g;
        if (this.type == "BOLD"){
            g2d.setFont( new Font(font, Font.BOLD, size) );
        } else if (this.type == "ITALIC")  {
            g2d.setFont( new Font(font, Font.ITALIC, size) );
        } else{
            g2d.setFont( new Font(font, Font.PLAIN, size) );
        }
        g2d.setColor(new Color(this.r, this.g, this.b));
        g2d.drawString(this.text, this.x, this.y);
    }

    private String PickRandomFontFamily() {
        Locale local_br = new Locale("pt", "BR");
        String[] fonts = GraphicsEnvironment.getLocalGraphicsEnvironment().getAvailableFontFamilyNames(local_br);
        return fonts[rand.nextInt(fonts.length)];
    }

    private String PickRandomType(){
        String[] type = {"BOLD", "PLAIN", "ITALIC"};
        return type[rand.nextInt(type.length)];
    }
}