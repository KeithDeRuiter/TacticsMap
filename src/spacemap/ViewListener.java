/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package spacemap;

import java.util.UUID;

/**
 *
 * @author Keith
 */
public interface ViewListener {
    public void createTrackAt();
    public void deleteTrack(UUID id);
}
