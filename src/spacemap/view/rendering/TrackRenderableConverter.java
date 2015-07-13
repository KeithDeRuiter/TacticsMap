package spacemap.view.rendering;

import java.awt.FontMetrics;
import spacemap.model.track.Track;

/**
 * A renderable converter for a Track.
 * @author adam
 */
public class TrackRenderableConverter {

    private final FontMetrics m_fontMetrics;
    
    private static final int LABEL_OFFSET = RenderableSymbol.BOX_SIDE;
    
    /**
     * Creates a new TrackRenderableConverter.
     * @param fontMetrics the metrics to use for text.
     */
    public TrackRenderableConverter(FontMetrics fontMetrics){
        m_fontMetrics = fontMetrics;
    }

    
    public RenderableGroup generateRenderableGroup(Track track, Coordinates anchorPoint) {
        RenderableText text = generateRenderableText(track, anchorPoint);
        RenderableSymbol symbol = generateRenderableSymbol(track, anchorPoint);
        
        RenderableGroup group = new RenderableGroup();
        group.addText(text);
        group.addSymbol(symbol);
        
        return group;
    }
    
    public RenderableText generateRenderableText(Track track, Coordinates anchorPoint) {
        Coordinates labelCoords = new Coordinates(anchorPoint.getX() + LABEL_OFFSET, anchorPoint.getY());
        RenderableText text = new RenderableText(labelCoords, track.getName(), track.getHostility().getColor(), track.getId(), m_fontMetrics);
        return text;
    }

    public RenderableSymbol generateRenderableSymbol(Track track, Coordinates anchorPoint){
        return new RenderableSymbol(anchorPoint, track.getHostility().getColor(), track.getId());
    }

}
