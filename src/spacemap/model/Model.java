/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package spacemap.model;

import java.util.UUID;
import spacemap.model.track.HostilityLevel;
import spacemap.model.track.Position;

/**
 * Interface for the backing model of the map system.
 * @author Keith
 */
public interface Model {
    /**
     * Creates a track.
     * @param position The position of the track to be created.
     * @param hostility The hostility of the track to be created.
     * @param name The name of the track to be created.
     * @return The UUID of the generated track.
     */
    public UUID createTrack(Position position, HostilityLevel hostility, String name);
    /**
     * Creates a track.
     * @param id The UUID of the track being updated.
     * @param position The updated position.
     * @param hostility The updated hostility.
     * @param name The updated name.
     */
    public void updateTrack(UUID id, Position position, HostilityLevel hostility, String name);
    
    /**
     * Deletes a track.
     * @param id The UUID of the track to delete.
     */
    public void deleteTrack(UUID id);
    
    /**
     * Adds a listener to this model.
     * @param listener The listener to add.
     */
    public void addModelListener(ModelListener listener);
    
    /**
     * Removes a listener to this model.
     * @param listener The listener to remove.
     */
    public void removeModelListener(ModelListener listener);
}
