/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package spacemap.view;

import java.util.Map;
import java.util.UUID;
import spacemap.view.rendering.Coordinates;
import spacemap.view.rendering.Renderable;

/**
 *
 * @author Keith
 */
public interface RenderableCoordinatesProvider {

    void addRenderableConfiguration(IndividualRenderableGroupConfiguration config);

    /**
     * Gets the coordinates of each renderable in a given group.  These coordinates are relative to the group's position.
     * @param uuid The UUID of the group for which coordinates are being requested.
     * @return A map of Renderables in the requested group to the coordinates on screen that they should be rendered at.
     */
    Map<Renderable, Coordinates> getRenderableCoordinatesForGroup(UUID uuid);

    void removeRenderableConfiguration(UUID uuid);
    
}
