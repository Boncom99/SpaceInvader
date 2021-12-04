import java.awt.Font;
import java.awt.FontFormatException;
import java.awt.Frame;
import java.awt.Graphics;
import java.awt.Image;
import java.awt.MouseInfo;
import java.awt.Point;
import java.awt.PointerInfo;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.awt.event.WindowEvent;
import java.awt.event.WindowListener;
import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

public class Window extends Frame implements KeyListener, WindowListener, MouseListener{
	Game game;

	int WIDTH=800,HEIGHT=600;
    List<Integer> keys=new ArrayList<Integer>();	
	//T�cnica de double buffer
	Image im;
	Graphics g;
	Font MainFont;
	Font MediumFont;
	Font smallFont;
	Font TinyFont;
	Font BigFont;
	File file = new File("background.bmp");
	/*String audioFile = "bip.mp3";
	Media hit = new Media(new File(audioFile).toURI().toString());
	MediaPlayer mediaPlayer = new MediaPlayer(hit);
	mediaPlayer.play();*/

	public static void main(String[] args) {
		new Window();
	}
	
	Window() {
		super("Space Invader");
		try{

		MainFont = Font.createFont(Font.TRUETYPE_FONT, new File ("ARCADE.TTF"));
		 smallFont=MainFont.deriveFont(30f);
		 TinyFont=MainFont.deriveFont(20f);
		 MediumFont=MainFont.deriveFont(65f);
		 BigFont=MainFont.deriveFont(100f);
		}
		catch (IOException|FontFormatException e) {
     // Handle exception
}

		setSize(WIDTH,HEIGHT);
		setVisible(true);
		this.addKeyListener(this);
		addWindowListener(this);
		addMouseListener(this);
		im=this.createImage(WIDTH, HEIGHT);
		g=im.getGraphics();
		game=new Game(this);
		game.run();
	}

	public void update(Graphics g) {
		if(keys.contains(KeyEvent.VK_DOWN)){
			game.moveShip(1);
		}
		if (keys.contains(KeyEvent.VK_UP)) {
			game.moveShip(-1);
		}
		/*if(keys.contains(KeyEvent.VK_SPACE)){
			game.shoot();
		}*/
		paint(g);
	}
	public void paint(Graphics g) {
		g.drawImage(im,0,0,null);
	}
 void checkmouse(){
    PointerInfo a = MouseInfo.getPointerInfo();
    Point b = a.getLocation();
    int x = (int) b.getX();
    int y = (int) b.getY();
	game.start(x, y);
    }
	


	@Override
	public void mouseClicked(MouseEvent e) {
		checkmouse();
		
	}

	@Override
	public void mouseEntered(MouseEvent e) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void mouseExited(MouseEvent e) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void mousePressed(MouseEvent e) {


		
	}

	@Override
	public void mouseReleased(MouseEvent e) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void keyReleased(KeyEvent e) {
		//Per a saber quina tecla et pitjen, la variable 'e' rebuda cont� la tecla.
 	if(keys.contains(e.getKeyCode())){
        keys.remove(keys.indexOf(e.getKeyCode()));
    }	
	if(e.getKeyCode()==KeyEvent.VK_SPACE)
		game.shoot();
	}

	@Override
	public void keyPressed(KeyEvent e) {
		 if(!keys.contains(e.getKeyCode())){
        keys.add(e.getKeyCode());
    }
	}
	@Override
	public void keyTyped(KeyEvent e) {
	
	}
		@Override
	public void windowActivated(WindowEvent e) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void windowClosed(WindowEvent e) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void windowClosing(WindowEvent e) {
	  System.exit(0);// tencar tot
       // dispose(); // tencar una finestra	
	}

	@Override
	public void windowDeactivated(WindowEvent e) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void windowDeiconified(WindowEvent e) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void windowIconified(WindowEvent e) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void windowOpened(WindowEvent e) {
		// TODO Auto-generated method stub
		
	}

}
