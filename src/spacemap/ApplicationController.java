/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package spacemap;

import java.util.UUID;
import spacemap.model.Model;
import spacemap.view.View;

/**
 *
 * @author Keith
 */
public class ApplicationController implements ModelListener, ViewListener {
    
    View view;
    
    Model model;
    
    @Override
    public void thingUpdated() {
        view.addAndUpdateTrackToView(null);
    }

    @Override
    public void createTrackAt() {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public void deleteTrack(UUID id) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }
    
}
