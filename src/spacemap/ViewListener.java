/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package spacemap;

import java.util.UUID;
import spacemap.model.track.Position;

/**
 * A listener for events triggered from the view.
 * @author Keith
 */
public interface ViewListener {
    /** 
     * Indicates that a track should be created at the specified location.
     * @param position The position of the new track.
     */
    public void createTrackAt(Position position);
    
    /**
     * Indicates that the track with the given ID should be deleted.
     * @param id The UUID of the track to be deleted.
     */
    public void deleteTrack(UUID id);
}
