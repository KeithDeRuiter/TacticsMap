/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package spacemap.view;

import java.util.UUID;
import spacemap.model.Track;

/**
 *
 * @author Keith
 */
public interface View {
    public void setDeclutterEnabled(boolean state);
    public void addAndUpdateTrackToView(Track track);
    public void removeTrackFromView(UUID id);
}
