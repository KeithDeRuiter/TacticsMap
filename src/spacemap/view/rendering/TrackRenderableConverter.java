package spacemap.view.rendering;

import spacemap.model.track.Track;

/**
 * A renderable converter for a Track.
 * @author Keith
 */
public class TrackRenderableConverter {
    
    /**
     * Creates a new TrackRenderableConverter.
     */
    public TrackRenderableConverter(){
    }
    
    public RenderableGroup generateRenderableGroup(Track track) {
        RenderableText nameText = generateNameRenderableText(track);
        RenderableText identifierText = generateIdentifierRenderableText(track);
        RenderableSymbol symbol = generateRenderableSymbol(track);
        
        RenderableGroup group = new RenderableGroup(track.getId());
        group.addText(nameText);
        group.addText(identifierText);
        group.addSymbol(symbol);
        
        return group;
    }

    private RenderableSymbol generateRenderableSymbol(Track track){
        return new RenderableSymbol(track.getHostility().getColor());
    }
    
    private RenderableText generateNameRenderableText(Track track) {
        RenderableText text = new RenderableText(track.getName(), track.getHostility().getColor());
        return text;
    }
    
    private RenderableText generateIdentifierRenderableText(Track track) {
        RenderableText text = new RenderableText(track.getIdentifier(), track.getHostility().getColor());
        return text;
    }

}
