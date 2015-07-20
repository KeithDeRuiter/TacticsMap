/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package spacemap.view;

import spacemap.model.track.Position;
import spacemap.view.rendering.RenderableGroup;

/**
 * Contains a configuratin for information about rendering a single {@link RenderableGroup}, namely the group and the desired position.
 * @author Keith
 */
public class IndividualRenderableGroupConfiguration {
    private final RenderableGroup group;
    private final Position position;

    public IndividualRenderableGroupConfiguration(RenderableGroup group, Position position) {
        this.group = group;
        this.position = position;
    }

    public RenderableGroup getGroup() {
        return group;
    }

    public Position getPosition() {
        return position;
    }
    
}
