import java.awt.Color;

public class Bullet extends MovingObject{
	int direction;  //+1 or -1
	@Override
    void move() {
		x+=direction*speed;
    }
	public Bullet(int x ,int y,int width, int height,int speed, Color c, int direction) {
		super(x, y,width,height, speed, c);
		this.direction = direction;
	}

}
