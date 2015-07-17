/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package spacemap.view.declutter;

import java.awt.geom.Rectangle2D;
import java.util.Set;

/**
 *
 * @author Keith
 */
public interface Declutterable {
    public Set<Rectangle2D.Float> getFixedItems();
    public Set<Rectangle2D.Float> getMoveableItems();
    public void reset();
}
