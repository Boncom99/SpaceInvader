import java.awt.Color;
import java.awt.Graphics;

public class Explosion extends MovingObject{
    int duration;
    long time0;
	@Override
	void move(int k) {
	}
	public Explosion(int x, int y,int width, int height,int speed, Color c, long time0){
		super(x, y,width,height, speed, c);
        this.duration = 100;
        this.time0=time0;

	}

    boolean over() {
        long timeNow = System.currentTimeMillis();
		long time = timeNow - time0;
		if (time < 0 || time > duration) {
            return true;
        }
        return false;
    }
	void paint(Graphics g){
            g.setColor(c); 
            int w= width/2;
            int h= height/2;
            g.fillArc(x-w, y-h, width, height, 0, 360);
            w = width / 6;
            h = height/ 6;
            g.setColor(Color.YELLOW); 
            g.fillArc(x-w, y-h, 2*w, 2*h, 0, 360);
    }
}
