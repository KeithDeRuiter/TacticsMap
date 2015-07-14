package spacemap.view.rendering;


import java.awt.Color;
import java.awt.Graphics2D;

/**
 *
 * @author adam
 */
public class RenderableSymbol extends Renderable {

    public static final int BOX_SIDE = 8;
    
    public RenderableSymbol(Color color){
        super(color);
    }

    @Override
    public void render(Graphics2D g2d) {
        g2d.setColor(getColor());
        g2d.drawRect(0 - (BOX_SIDE / 2), 0 - (BOX_SIDE / 2), BOX_SIDE, BOX_SIDE);
    }

}
