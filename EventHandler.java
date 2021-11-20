import java.awt.event.KeyListener;
import java.awt.event.KeyEvent;
class EventHandler implements KeyListener{
    @Override
    public void keyPressed(KeyEvent e) {
        // TODO Auto-generated method stub

    }

  public void keyReleased(KeyEvent e) {
		//Per a saber quina tecla et pitjen, la variable 'e' rebuda cont� la tecla.
		if (e.getKeyCode()==KeyEvent.VK_SPACE) {
			System.out.println("Espai premut.");

		}
		if (e.getKeyCode()==KeyEvent.VK_DOWN) {
			System.out.println("down");
		}
		if (e.getKeyCode()==KeyEvent.VK_UP) {
			System.out.println("up");
		}
		if (e.getKeyCode()==KeyEvent.VK_LEFT) {
			System.out.println("left");
		}
		if (e.getKeyCode()==KeyEvent.VK_RIGHT) {
			System.out.println("right");
		}
	}

    @Override
    public void keyTyped(KeyEvent e) {
        // TODO Auto-generated method stub

    }
} 