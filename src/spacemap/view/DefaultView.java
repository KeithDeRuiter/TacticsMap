/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package spacemap.view;

import java.awt.Dimension;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.util.ArrayList;
import java.util.List;
import java.util.UUID;
import javax.swing.JComponent;
import javax.swing.JFrame;
import spacemap.ViewListener;
import spacemap.model.track.Position;
import spacemap.model.track.Track;
import spacemap.view.rendering.Coordinates;
import spacemap.view.rendering.RenderableBox;

/**
 * The default map view.
 * @author Keith
 */
public class DefaultView implements View {

    JFrame frame;
    
    private MapDisplay map;
    
    private final List<ViewListener> listeners;

    public DefaultView() {
        listeners = new ArrayList<>();
        initComponents();
    }
    
    private void initComponents() {
        frame = new JFrame("Tactics Map");
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setPreferredSize(new Dimension(800, 600));
        frame.setResizable(true);
        
        map = new MapDisplay();
        JComponent mapComponent = map.getComponent();
        mapComponent.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseReleased(MouseEvent e) {
                super.mouseReleased(e);
                System.out.println("Mouse Released at " + e.getPoint());
                notifyAllCreateTrackAt(map.getPositionForScreenCoordinates(new Coordinates(e.getX(), e.getY())));
            }
        });
        
        
        map.addRenderable(new RenderableBox(new Coordinates(10, 10), 10, 10));
        
        frame.add(mapComponent);
    }
    
    @Override
    public void showView() {
        frame.pack();
        frame.setVisible(true);
    }
    
    @Override
    public void setDeclutterEnabled(boolean state) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public void addOrUpdateTrackToView(Track track) {
        map.addRenderablesForTrack(track);
    }

    @Override
    public void removeTrackFromView(UUID id) {
        map.removeRenderablesForTrack(id);
    }

    public void notifyAllCreateTrackAt(Position p) {
        listeners.stream().forEach((l) -> {
            l.createTrackAt(p);
        });
    }
    
    @Override
    public void addViewListener(ViewListener listener) {
        listeners.add(listener);
    }

    @Override
    public void removeViewListener(ViewListener listener) {
        listeners.remove(listener);
    }
    
}
