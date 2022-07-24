import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;

public class Keyboard implements KeyListener {

    @Override
    public void keyTyped(KeyEvent e) {

    }

    @Override
    public void keyPressed(KeyEvent e) {
        switch (e.getKeyChar()) {
            case ']':
                Window.GLOBAL_OFFSET += 5;
                break;
            case '[':
                Window.GLOBAL_OFFSET -= 5;
                break;
            case 'o':
                Window.REGEN = true;
                break;
        }
    }

    @Override
    public void keyReleased(KeyEvent e) {

    }
}
