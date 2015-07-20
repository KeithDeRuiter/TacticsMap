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
import java.util.List;
import java.util.Map;
import java.util.UUID;
import javax.swing.JComponent;
import spacemap.model.track.Position;
import spacemap.view.rendering.Coordinates;
import spacemap.view.rendering.Renderable;
import spacemap.view.rendering.RenderableGroup;

/**
 *
 * @author Keith
 */
public class MapDisplay {

    private final Map<UUID, IndividualRenderableGroupConfiguration> trackRenderableGroupConfigurations;
    
    private final DisplayComponent component;
    
    private RenderableCoordinatesProvider coordinateProvider;
    
    public MapDisplay() {
        trackRenderableGroupConfigurations = new HashMap<>();
        component = new DisplayComponent();
        coordinateProvider = new DefaultRenderableCoordinatesProvider(this);
    }
    
    public void addRenderableGroup(RenderableGroup group, Position position) {
        IndividualRenderableGroupConfiguration config = new IndividualRenderableGroupConfiguration(group, position);
        trackRenderableGroupConfigurations.put(group.getUuid(), config);
        coordinateProvider.addRenderableConfiguration(config);
        component.repaint();
    }
    
    public void updateRenderableGroup(RenderableGroup group, Position position) {
        IndividualRenderableGroupConfiguration config = new IndividualRenderableGroupConfiguration(group, position);
        trackRenderableGroupConfigurations.put(group.getUuid(), config);
        coordinateProvider.addRenderableConfiguration(config);
        component.repaint();
    }
    
    public void removeRenderablesForGroup(UUID id) {
        if (!trackRenderableGroupConfigurations.containsKey(id)) {
            System.out.println("Cannot remove renderables for Track " + id + ", ID does not exist in the Map Display");
            return;
        }
        
        trackRenderableGroupConfigurations.remove(id);
        coordinateProvider.removeRenderableConfiguration(id);
        
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
            trackRenderableGroupConfigurations.values().stream().forEach((IndividualRenderableGroupConfiguration c) -> {
                Map<Renderable, Coordinates> renderablesWithCoordinates = coordinateProvider.getRenderableCoordinatesForGroup(c.getGroup().getUuid());
                
                //TODO maybe iterate over and draw original renderables, but get coordinates lookup from provider?
                //You shouldn't need to get the actual renderables themselves from the provider...
                
                //Draw all of the group's renderables with their appropriate offset
                List<Renderable> toRender = c.getGroup().getAllRenderables();
                toRender.stream().forEach((renderableKey) -> {
                    Graphics2D renderableGraphics = (Graphics2D)g2d.create();
                    Coordinates renderableTransformCoordinates = renderablesWithCoordinates.get(renderableKey);
                    renderableGraphics.translate(renderableTransformCoordinates.getX(), renderableTransformCoordinates.getY());
                    renderableKey.render(renderableGraphics);
                    renderableGraphics.dispose();
                });

            });
        }    
    }
    
}
