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
		}
	}
