package spacemap.view.rendering;

import java.awt.Color;
import java.awt.Graphics2D;

/**
 *
 * @author AndersonAN
 */
public class RenderableBox extends Renderable {
    
//    private Coordinates m_topLeft;
    private int m_width;
    private int m_height;
    
    public RenderableBox(int width, int height) {
        this(width, height, Color.BLACK);
    }
    
    public RenderableBox(int width, int height, Color color){
        super(color);
        m_width = width;
        m_height = height;
    }
    
    @Override
    public void render(Graphics2D g2d) {
        g2d.setColor(getColor());
        g2d.drawRect(0, 0, m_width, m_height);
    }
}
