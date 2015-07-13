/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package spacemap;

import spacemap.model.ModelListener;
import java.util.UUID;
import spacemap.model.Model;
import spacemap.model.ModelEvent;
import spacemap.model.track.HostilityLevel;
import spacemap.model.track.Position;
import spacemap.view.View;

/**
 *
 * @author Keith
 */
public class ApplicationController implements ModelListener, ViewListener {
    
    View view;
    
    Model model;

    public ApplicationController(Model model, View view) {
        this.model = model;
        this.view = view;
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
    public void createTrackAt(Position position) {
        System.out.println("Controller: create track at " + position);
        model.createTrack(position, HostilityLevel.ALLY, "TrackName");
    }
    
    
    @Override
    public void trackAddedOrUpdated(ModelEvent event) {
        System.out.println("Controller: track added/updated " + event.getTrack().getId());
        view.addOrUpdateTrackToView(event.getTrack());
    }

    @Override
    public void trackRemoved(ModelEvent event) {
        System.out.println("Controller: track removed " + event.getTrack().getId());
        view.removeTrackFromView(event.getTrack().getId());
    }

    
}
