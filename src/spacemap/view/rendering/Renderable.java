package spacemap.view.rendering;

import java.awt.Color;
import java.awt.Graphics2D;

/**
 * The base class for Renderable objects.
 * @author Keith
 */
public abstract class Renderable {

    /** The color of this renderable. */
    private final Color color;

    /**
     * Constructs a new {@code Renderable}.
     * @param color The color for this renderable.
     */
    public Renderable(Color color){
        this.color = color;
    }

    /**
     * Gets the color of this renderable.
     * @return The color of this renderable.
     */
    public Color getColor(){
        return color;
    }
    
    /**
     * Renders the renderable using the given {@code Graphics}.
     * @param g2d 
     */
    public abstract void render(Graphics2D g2d);
    
}
