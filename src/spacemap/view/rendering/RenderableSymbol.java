package spacemap.view.rendering;


import java.awt.Color;
import java.awt.Graphics2D;
import java.awt.Rectangle;
import java.util.UUID;

/**
 *
 * @author adam
 */
public class RenderableSymbol extends Renderable {
    private final Coordinates m_point;
    private final UUID m_id;

    public static final int BOX_SIDE = 8;
    
    public RenderableSymbol(Coordinates coords, Color color, UUID trackGuid){
        super(color);
        m_point = coords;
        m_id = trackGuid;
    }

    public int getX(){
        return m_point.getX();
    }

    public int getY() {
        return m_point.getY();
    }

    public UUID getId(){
        return m_id;
    }

    @Override
    public void render(Graphics2D g2d) {
        g2d.setColor(getColor());
        g2d.drawRect(m_point.getX() - (BOX_SIDE / 2), m_point.getY() - (BOX_SIDE / 2), BOX_SIDE, BOX_SIDE);
    }

    @Override
    public Rectangle getBounds() {
        return new Rectangle(m_point.getX() - (BOX_SIDE / 2), m_point.getY() - (BOX_SIDE / 2), BOX_SIDE, BOX_SIDE);
    }

    @Override
    public void translate(int dx, int dy) {
        m_point.translate(dx, dy);
    }
}
