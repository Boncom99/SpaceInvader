import java.awt.Color;

public class Bullet extends MovingObject{
	@Override
    void move(int direction) {
		x+=direction*speed;
    }
	public Bullet(int x ,int y,int width, int height,int speed, Color c) {
		super(x, y,width,height, speed, c);
	}

}
