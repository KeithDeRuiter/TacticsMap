/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package spacemap.model;

/**
 * A listener to a {@link Model} to handle various events.
 * @author Keith
 */
public interface ModelListener {
    
    /**
     * Method triggered when a track has been added or updated in the model.
     * @param event The event with the information added/updated.
     */
    public void trackAddedOrUpdated(ModelEvent event);
    
    /**
     * Method triggered when a track has been added or updated in the model.
     * @param event The event with the information removed.
     */
    public void trackRemoved(ModelEvent event);
}
