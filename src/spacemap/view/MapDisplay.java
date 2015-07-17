/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package spacemap.view;

import java.awt.Color;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.util.HashMap;
import java.util.Map;
import java.util.UUID;
import javax.swing.JComponent;
import spacemap.model.track.Position;
import spacemap.view.rendering.Coordinates;
import spacemap.view.rendering.RenderableGroup;
import spacemap.view.rendering.RenderableSymbol;

/**
 *
 * @author Keith
 */
public class MapDisplay {

    private final Map<UUID, IndividualGroupConfiguration> trackRenderableGroupConfigurations;
    
    private final DisplayComponent component;
    
    public MapDisplay() {
        trackRenderableGroupConfigurations = new HashMap<>();
        component = new DisplayComponent();
    }
    
    public void addRenderableGroup(RenderableGroup group, Position position) {
        IndividualGroupConfiguration config = new IndividualGroupConfiguration(group, position);
        trackRenderableGroupConfigurations.put(group.getUuid(), config);
        
        component.repaint();
    }
    
    public void updateRenderableGroup(RenderableGroup group, Position position) {
        IndividualGroupConfiguration config = new IndividualGroupConfiguration(group, position);
        trackRenderableGroupConfigurations.put(group.getUuid(), config);
        
        component.repaint();
    }
    
    public void removeRenderablesForGroup(UUID id) {
        if (!trackRenderableGroupConfigurations.containsKey(id)) {
            System.out.println("Cannot remove renderables for Track " + id + ", ID does not exist in the Map Display");
            return;
        }
        
        trackRenderableGroupConfigurations.remove(id);
        
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

            Graphics2D g2d = (Graphics2D) g.create();
            
            //Initialize font metrics
            

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
            trackRenderableGroupConfigurations.values().stream().forEach((c) -> {
                //Get graphics to transform and render this group
                Coordinates coords = getScreenCoordinatesForPosition(c.getPosition());
                Graphics2D groupGraphics = (Graphics2D)g2d.create();
                groupGraphics.translate(coords.getX(), coords.getY());
                
                //Do rendering
                //Symbols
                c.getGroup().getSymbols().stream().forEach((r) -> {
                    r.render(groupGraphics);
                });
                
                groupGraphics.translate(RenderableSymbol.BOX_SIDE, 0);
                c.getGroup().getTexts().stream().forEach((r) -> {
                    r.render(groupGraphics);
                });

                //Dispose graphics, no longer necessary
                groupGraphics.dispose();
            });
        }    
    }
    
    private class IndividualGroupConfiguration {
        private final RenderableGroup group;
        private final Position position;

        public IndividualGroupConfiguration(RenderableGroup group, Position position) {
            this.group = group;
            this.position = position;
        }

        public RenderableGroup getGroup() {
            return group;
        }

        public Position getPosition() {
            return position;
        }
    }
}
