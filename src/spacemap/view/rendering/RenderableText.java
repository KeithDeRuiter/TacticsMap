package spacemap.view.rendering;

import java.awt.Color;
import java.awt.Graphics2D;

/**
 * Everything needed to render text.
 * @author Keith
 */
public class RenderableText extends Renderable {

    private final String m_text;
    
    public RenderableText(String text, Color color){
        super(color);
        m_text = text;
    }

    public String getText(){
        return m_text;
    }

    @Override
    public void render(Graphics2D g2d) {
        g2d.setColor(getColor());
        g2d.drawString(m_text, 0, 0);
    }

}
