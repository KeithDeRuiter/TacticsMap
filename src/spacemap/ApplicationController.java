/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package spacemap;

import java.util.HashMap;
import java.util.Map;
import spacemap.model.ModelListener;
import java.util.UUID;
import spacemap.model.Model;
import spacemap.model.ModelEvent;
import spacemap.model.track.HostilityLevel;
import spacemap.model.track.Position;
import spacemap.view.View;
import spacemap.view.rendering.RenderableGroup;
import spacemap.view.rendering.TrackRenderableConverter;

/**
 *
 * @author Keith
 */
public class ApplicationController implements ModelListener, ViewListener {
    
    private View view;
    
    private Model model;
    
    private TrackRenderableConverter trackRenderableConverter;
    
    private final Map<UUID, RenderableGroup> trackRenderables;

    public ApplicationController(Model model, View view) {
        this.model = model;
        this.view = view;
        trackRenderables = new HashMap<>();
        trackRenderableConverter = new TrackRenderableConverter();
    }
    
    
    public void launch() {
        model.addModelListener(this);
        view.addViewListener(this);
        view.showView();
    }

    @Override
    public void deleteTrack(UUID id) {
        System.out.println("Controller: delete track " + id);
        model.deleteTrack(id);
    }

    @Override
    public void createTrack(Position position, HostilityLevel hostility, String name) {
        System.out.println("Controller: create " + hostility.getName() + " \"" + name + " track at " + position);
        model.createTrack(position, hostility, name);
    }
    
    
    @Override
    public void trackAdded(ModelEvent event) {
        System.out.println("Controller: track added " + event.getTrack().getId());
        //Generate renderable group
        RenderableGroup renderables = trackRenderableConverter.generateRenderableGroup(event.getTrack());
        event.getTrack();
        view.addRenderables(renderables, event.getTrack().getPosition());
    }
    
    @Override
    public void trackUpdated(ModelEvent event) {
        System.out.println("Controller: track updated " + event.getTrack().getId());
        //Generate renderable group
        RenderableGroup renderables = trackRenderableConverter.generateRenderableGroup(event.getTrack());
        event.getTrack();
        view.updateRenderables(renderables, event.getTrack().getPosition());
    }

    @Override
    public void trackRemoved(ModelEvent event) {
        System.out.println("Controller: track removed " + event.getTrack().getId());
        view.removeTrackFromView(event.getTrack().getId());
    }

    
}
