package spacemap.view.rendering;


import java.util.Collections;
import java.util.HashSet;
import java.util.Set;
import java.util.UUID;

/**
 * A group of renderable objects.
 * @author Keith
 */
public class RenderableGroup {
    
    private final UUID uuid;
    
    private final Set<RenderableText> m_texts;

    private final Set<RenderableLine> m_lines;

    private final Set<RenderableSymbol> m_symbols;

    public RenderableGroup(UUID id) {
        this.uuid = id;
        m_texts = new HashSet<>();
        m_lines = new HashSet<>();
        m_symbols = new HashSet<>();
    }

    public UUID getUuid() {
        return this.uuid;
    }
    
    public void addText(RenderableText text){
        m_texts.add(text);
    }

    public void addLine(RenderableLine line){
        m_lines.add(line);
    }

    public void addSymbol(RenderableSymbol symbol){
        m_symbols.add(symbol);
    }

    public Set<RenderableText> getTexts(){
        return Collections.unmodifiableSet(m_texts);
    }

    public Set<RenderableLine> getLines(){
        return Collections.unmodifiableSet(m_lines);
    }

    public Set<RenderableSymbol> getSymbols(){
        return Collections.unmodifiableSet(m_symbols);
    }
    
    public Set<Renderable> getAllRenderables() {
        Set<Renderable> r = new HashSet<>();
        r.addAll(m_texts);
        r.addAll(m_lines);
        r.addAll(m_symbols);
        
        return Collections.unmodifiableSet(r);
    }

}
