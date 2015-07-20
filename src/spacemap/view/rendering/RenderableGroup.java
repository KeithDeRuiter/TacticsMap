package spacemap.view.rendering;


import java.util.ArrayList;
import java.util.Collections;
import java.util.HashSet;
import java.util.List;
import java.util.Set;
import java.util.UUID;

/**
 * A group of renderable objects.
 * @author Keith
 */
public class RenderableGroup {
    
    private final UUID uuid;
    
    private final List<RenderableText> m_texts;

    private final List<RenderableLine> m_lines;

    private final List<RenderableSymbol> m_symbols;

    public RenderableGroup(UUID id) {
        this.uuid = id;
        m_texts = new ArrayList<>();
        m_lines = new ArrayList<>();
        m_symbols = new ArrayList<>();
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

    public List<RenderableText> getTexts(){
        return Collections.unmodifiableList(m_texts);
    }

    public List<RenderableLine> getLines(){
        return Collections.unmodifiableList(m_lines);
    }

    public List<RenderableSymbol> getSymbols(){
        return Collections.unmodifiableList(m_symbols);
    }
    
    public List<Renderable> getAllRenderables() {
        List<Renderable> r = new ArrayList<>();
        r.addAll(m_texts);
        r.addAll(m_lines);
        r.addAll(m_symbols);
        
        return Collections.unmodifiableList(r);
    }

}
