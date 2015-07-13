/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package spacemap.model;

import spacemap.model.track.Track;

/**
 *
 * @author Keith
 */
public class ModelEvent {
    
    /** The track that the event is about. */
    private final Track track;

    public ModelEvent(Track track) {
        this.track = track;
    }

    public Track getTrack() {
        return track;
    }
    
    
}
