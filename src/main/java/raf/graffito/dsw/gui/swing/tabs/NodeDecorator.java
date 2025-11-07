package raf.graffito.dsw.gui.swing.tabs;

import java.util.HashMap;
import java.util.Map;

/** Osnovni dekorator za eventualne dodatne atribute oko nekog objekta. */
public class NodeDecorator {
    private Object node;
    private Map<String, Object> attrs = new HashMap<>();

    public NodeDecorator() {}
    public NodeDecorator(Object node) { this.node = node; }

    public Object getNode() { return node; }
    public void setAttr(String key, Object value) { attrs.put(key, value); }
    public Object getAttr(String key) { return attrs.get(key); }
}
