/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package spacemap.view;

import java.util.UUID;
import spacemap.ViewListener;
import spacemap.model.track.Track;

/**
 * Interface to describe a view for the tactics map.
 * @author Keith
 */
public interface View {
    
    /**
     * Sets whether or not "decluttering" of the view is enabled to provide a simpler display view.
     * @param state {@code true} to enable declutter, {@code false} to disable.
     */
    public void setDeclutterEnabled(boolean state);
    
    /**
     * Adds or updates a Track to the view.
     * @param track The track to be added or updated.
     */
    public void addOrUpdateTrackToView(Track track);
    
    /**
     * Removes the object with the given UUID from the view.
     * @param id The UUID to remove.
     */
    public void removeTrackFromView(UUID id);
    
    /**
     * Shows the view.
     */
    public void showView();
    
    /**
     * Adds a listener to this view.
     * @param listener The listener to add.
     */
    public void addViewListener(ViewListener listener);
    
    /**
     * Removes a listener from this view.
     * @param listener The listener to remove.
     */
    public void removeViewListener(ViewListener listener);
    
}
