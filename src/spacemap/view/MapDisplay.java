/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package spacemap.view;

import java.awt.Color;
import java.awt.FontMetrics;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Map;
import java.util.Set;
import java.util.UUID;
import javax.swing.JComponent;
import spacemap.model.track.Position;
import spacemap.model.track.Track;
import spacemap.view.rendering.Coordinates;
import spacemap.view.rendering.Renderable;
import spacemap.view.rendering.RenderableGroup;
import spacemap.view.rendering.TrackRenderableConverter;

/**
 *
 * @author Keith
 */
public class MapDisplay {
    
    private final Set<Renderable> renderables;

    private final Map<UUID, RenderableGroup> trackRenderableGroups;
    
    private TrackRenderableConverter trackRenderableConverter;
    
    private final DisplayComponent component;
    
    public MapDisplay() {
        renderables = new HashSet<>();
        trackRenderableGroups = new HashMap<>();
        component = new DisplayComponent();
    }
    
    public void addRenderablesForTrack(Track track) {
        Coordinates trackCoordinates = getScreenCoordinatesForPosition(track.getPosition());
        RenderableGroup group = trackRenderableConverter.generateRenderableGroup(track, trackCoordinates);
        
        trackRenderableGroups.put(track.getId(), group);
        group.getAllRenderables().stream().forEach((r) -> {
            addRenderable(r);
        });
    }
    
    public void removeRenderablesForTrack(UUID id) {
        RenderableGroup group = trackRenderableGroups.get(id);
        if (group == null) {
            System.out.println("Cannot remove renderables for Track " + id + ", ID does not exist in the Map Display");
            return;
        }
        
        group.getAllRenderables().stream().forEach((r) -> {
            removeRenderable(r);
        });
    }
    
    public void addRenderable(Renderable r) {
        renderables.add(r);
        component.repaint();
    }
    
    public void removeRenderable(Renderable r) {
        renderables.remove(r);
        component.repaint();
    }
    
    public void setDeclutterEnabled(boolean state) {
        if (state) {
            
        }
    }
    
    public JComponent getComponent() {
        return component;
    }

    public Coordinates getScreenCoordinatesForPosition(Position p) {
        return new Coordinates((int)p.getX(), (int)p.getY());
    }
    
    public Position getPositionForScreenCoordinates(Coordinates c) {
        return new Position(c.getX(), c.getY());
    }
    
    private class DisplayComponent extends JComponent {

        public DisplayComponent() {
            initComponents();
        }
        
        private void initComponents() {
        }
        
        @Override
        protected void paintComponent(Graphics g) {
            super.paintComponent(g);

            Graphics2D g2d = (Graphics2D) g;
            
            //Initialize font metrics
            if(trackRenderableConverter == null) {
                FontMetrics fm = g2d.getFontMetrics();
                trackRenderableConverter = new TrackRenderableConverter(fm);
            }

            //Render Background
            g2d.setColor(Color.BLACK);
            g2d.fillRect(0, 0, getWidth(), getHeight());
            
            //Render grid lines
            g2d.setColor(Color.GRAY);
            for(int i = 0; i < this.getWidth(); i+=100) { //draw verticals
                g2d.drawLine(i, 0, i, this.getHeight());
            }
            for(int i = 0; i < this.getHeight(); i+=100) { //draw horizontals
                g2d.drawLine(0, i, this.getWidth(), i);
            }
            
            //Render Renderables
            renderables.stream().forEach((r) -> {
                r.render(g2d);
            });
        }    
    }
}
