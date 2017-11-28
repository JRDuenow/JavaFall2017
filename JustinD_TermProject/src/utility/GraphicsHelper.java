

package utility;

import java.awt.FontMetrics;
import java.awt.Graphics;
import java.awt.geom.Rectangle2D;


public class GraphicsHelper {
    public static void drawCenteredString(Graphics g, String text, Rectangle2D.Float rect, int xOffset, int yOffset) {

        FontMetrics metrics = g.getFontMetrics(g.getFont());

        // Determine the X coordinate for the text
        int x = (int) rect.x + ((int) rect.width - metrics.stringWidth(text)) / 2;
        // Determine the Y coordinate for the text
        int y = (int) rect.y + (((int) rect.height - metrics.getHeight()) / 2) + metrics.getAscent();

        g.drawString(text, x + xOffset, y + yOffset);
    }
}
