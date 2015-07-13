/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package spacemap;

import spacemap.model.DefaultModel;
import spacemap.view.DefaultView;

/**
 *
 * @author Keith
 */
public class SpaceMap {

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {
        DefaultModel model = new DefaultModel();
        DefaultView view = new DefaultView();
        
        ApplicationController controller = new ApplicationController(model, view);
        controller.launch();
    }
    
}
