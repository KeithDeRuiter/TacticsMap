/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package spacemap.view;

import java.awt.Graphics;
import java.awt.geom.Rectangle2D;
import java.util.HashMap;
import java.util.Map;
import java.util.UUID;
import spacemap.view.rendering.Coordinates;
import spacemap.view.rendering.Renderable;
import spacemap.view.rendering.RenderableGroup;
import spacemap.view.rendering.RenderableSymbol;
import spacemap.view.rendering.RenderableText;

/**
 * Provides default coordinates for renderables.
 * @author Keith
 */
public class DeclutteringRenderableCoordinatesProvider implements RenderableCoordinatesProvider {

    private final Map<UUID, IndividualRenderableGroupConfiguration> configs;
    
    private MapDisplay mapDisplay;
    
    public DeclutteringRenderableCoordinatesProvider(MapDisplay mapDisplay) {
        this.mapDisplay = mapDisplay;
        configs = new HashMap<>();
    }
    
    
    @Override
    public void addRenderableConfiguration(IndividualRenderableGroupConfiguration config) {
        configs.put(config.getGroup().getUuid(), config);
    }

    @Override
    public void removeRenderableConfiguration(UUID uuid) {
        configs.remove(uuid);
    }

    @Override
    public Map<Renderable, Coordinates> getRenderableCoordinatesForGroup(UUID uuid) {
        Map<Renderable, Coordinates> renderableCoordinates = new HashMap<>();
        
        IndividualRenderableGroupConfiguration config = configs.get(uuid);
        
        if (config == null) {
            System.out.println("Cannot get renderable coordinates for group " + uuid + ", group configuration not found in coordinates provider.");
            return renderableCoordinates;
        }
        
        RenderableGroup group = config.getGroup();
        Coordinates groupAnchorCoordinates = mapDisplay.getScreenCoordinatesForPosition(config.getPosition());
        
        //Symbols
        group.getSymbols().stream().forEach((symbol) -> {
            Coordinates symbolCoords = groupAnchorCoordinates;
            renderableCoordinates.put(symbol, symbolCoords);
        });
        
        //Labels
        int labelCount = 0;
        for (RenderableText label : group.getTexts()) {
            Coordinates labelCoords = new Coordinates(groupAnchorCoordinates);
            
            Graphics g = mapDisplay.getComponent().getGraphics().create();
            Rectangle2D bounds = g.getFontMetrics().getStringBounds(label.getText(), g);
            
            labelCoords.translate(RenderableSymbol.BOX_SIDE, labelCount * (int)bounds.getHeight());
            renderableCoordinates.put(label, labelCoords);
            labelCount++;
        }
        
        
        return renderableCoordinates;
    }
    
}
