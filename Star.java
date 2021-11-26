import java.awt.Color;
import java.awt.Graphics;

public class Star extends MovingObject{
    

	@Override
    void move(int init) {
        if (x>0) {
        x += -speed; 
    } else {
        x = init;
        }
    }

    public Star(int x, int y,int width, int height, int speed, Color c) {
        	super(x, y,width,height, speed, c);
		}

		void paintStar(Graphics g){
		g.setColor(c); 
        g.fillArc(x, y, width, height, 0, 360);
        g.drawLine(x+height/2, y+height/2 +5*height/6, x+height/2, y+height/2-5*height/6);//vertical
        g.drawLine( x+height/2+5*height/6, y+height/2, x+height/2-5*height/6,y+height/2); //horizontal
		}
	}
