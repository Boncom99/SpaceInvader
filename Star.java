import java.awt.Color;
import java.awt.Graphics;

public class Star {
    
    int x;
    int y;
    int size;


	Star(int x,int y , int size) {
        this.x = x;
        this.y = y;
        this.size = size;
		}

		void paintStar(Graphics g){
		g.setColor(new Color(255, 255, 255)); 
        g.fillArc(x, y, size, size, 0, 360);
        g.drawLine(x+size/2, y+size/2 +5*size/6, x+size/2, y+size/2-5*size/6);//vertical
        g.drawLine( x+size/2+5*size/6, y+size/2, x+size/2-5*size/6,y+size/2); //horizontal
		}
	}
