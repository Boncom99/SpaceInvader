import java.awt.Color;
import java.awt.Graphics;

public class Bullet{
	int y;
    int x;
	int speed;
    Color c;
	Bullet(int x ,int y,int speed, Color c) {

        this.x = x;
		this.y=y;
		this.speed = speed;
        this.c=c;

	}
	void moure() {
		x+=speed;
	}
	void pintar(Graphics g) {
		g.setColor(c);
		g.fillRect(x, y, 10,3);
	}

}
