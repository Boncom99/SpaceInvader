import java.awt.Color;
import java.util.ArrayList;
import java.util.List;
import java.awt.Graphics;

public class Wall {
	int x;
	int y;
	int width;
	int height;
	Color c;
	List <Brick> bricks;
	public Wall(int x, int y,int width, int height, Color c) {
		this.x = x;
		this.y = y;
		this.width = width;
		this.height = height;
		this.c = c;
		bricks =new ArrayList<Brick>();
		for (int i = 0; i < width; i++) {
			for (int j = 0; j < height; j++) {
			bricks.add(new Brick(x+i*10,y+ j*10,10,10, c));
			}
		}

	}

	void paint(Graphics g) {
		for (Brick brick : bricks) {
			brick.paint(g);
		}
	}

	
}
