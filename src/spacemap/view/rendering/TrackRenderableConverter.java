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
        RenderableText text = generateRenderableText(track);
        RenderableSymbol symbol = generateRenderableSymbol(track);
        
        RenderableGroup group = new RenderableGroup(track.getId());
        group.addText(text);
        group.addSymbol(symbol);
        
        return group;
    }
    
    private RenderableText generateRenderableText(Track track) {
        RenderableText text = new RenderableText(track.getName(), track.getHostility().getColor());
        return text;
    }

    private RenderableSymbol generateRenderableSymbol(Track track){
        return new RenderableSymbol(track.getHostility().getColor());
    }

}
