package GUI;
/**
 * Lidor Feldman
 * 209297035
 * Itay Ventura
 * 208928333
 */
import javax.swing.*;
import java.awt.*;

/**
 * ArenaPanel for the background of the arena
 */
public class ArenaPanel extends JPanel {
    private Image image;

    /**
     * default constructor for the panel
     */
    public ArenaPanel() {
        image = null;
    }

    /**
     * @param img set image
     */
    public boolean setImage(Image img) {
        this.image=img;
        return true;
    }

    /**
     * @param g the <code>Graphics</code> object to protect
     */
    public void paintComponent(Graphics g) {
        g.drawImage(image,0,0,this.getWidth(),this.getHeight(),this);
        // to use function drawImage(image, x, y, w, h, this)

    }
}
