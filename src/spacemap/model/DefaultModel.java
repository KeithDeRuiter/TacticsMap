/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package spacemap.model;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.UUID;
import spacemap.model.track.HostilityLevel;
import spacemap.model.track.Position;
import spacemap.model.track.Track;

/**
 * A basic default model for tracks.
 * @author Keith
 */
public class DefaultModel implements Model {

    /** Listeners to be notified about Model Events. */
    private final List<ModelListener> listeners;
    
    /** Mapping for all tracks from their IDs. */
    private final Map<UUID, Track> tracks;

    /**
     * Constructs a new instance of {@code DefaultModel}.
     */
    public DefaultModel() {
        this.listeners = new ArrayList<>();
        this.tracks = new HashMap<>();
    }
    
    
    
    @Override
    public UUID createTrack(Position position, HostilityLevel hostility, String name) {
        Track newTrack = new Track(position, hostility, name);
        tracks.put(newTrack.getId(), newTrack);
        notifyAllAddOrUpdateTrack(newTrack);
        
        System.out.println("New Track Created: " + newTrack.getId());
        return newTrack.getId();
    }

    @Override
    public void updateTrack(UUID id, Position position, HostilityLevel hostility, String name) {
        Track track = tracks.get(id);
        
        if (track == null) {
            System.out.println("Track " + id + " cannot be updated, could not be located in Model");
            return;
        }
        
        track.setName(name);
        track.setPosition(position);
        track.setHostility(hostility);
        
        System.out.println("Track Updated: " + track.getId());
        notifyAllAddOrUpdateTrack(track);
    }

    @Override
    public void deleteTrack(UUID id) {
        Track oldTrack = tracks.remove(id);
        if (oldTrack == null) {
            System.out.println("Track " + id + " cannot be removed, could not be located in Model");
            return;
        }
        
        System.out.println("Track Deleted: " + id);
        notifyAllDeleteTrack(oldTrack);
    }

    private void notifyAllAddOrUpdateTrack(Track track) {
        ModelEvent event = new ModelEvent(track);
        
        listeners.stream().forEach((l) -> {
            l.trackAddedOrUpdated(event);
        });
    }

    private void notifyAllDeleteTrack(Track track) {
        ModelEvent event = new ModelEvent(track);
        
        listeners.stream().forEach((l) -> {
            l.trackRemoved(event);
        });
    }
    
    @Override
    public void addModelListener(ModelListener listener) {
        listeners.add(listener);
    }

    @Override
    public void removeModelListener(ModelListener listener) {
        listeners.remove(listener);
    }
    
}
