package raf.graffito.dsw.gui.swing.painter;

import raf.graffito.dsw.model.composite.Image;

import java.awt.*;

public class ImagePainter {
    public void paint(Graphics2D g2, Image element) {
        Composite oldComposite = g2.getComposite();

        g2.setComposite(AlphaComposite.SrcOver);
        g2.setRenderingHint(RenderingHints.KEY_INTERPOLATION, RenderingHints.VALUE_INTERPOLATION_BILINEAR);
        g2.setRenderingHint(RenderingHints.KEY_RENDERING, RenderingHints.VALUE_RENDER_QUALITY);
        g2.setRenderingHint(RenderingHints.KEY_ANTIALIASING, RenderingHints.VALUE_ANTIALIAS_ON);

        int x = element.getXCoordinate();
        int y = element.getYCoordinate();
        int w = element.getWidth();
        int h = element.getHeight();

        g2.drawImage(element.getImage(), x, y, w, h, null);

        g2.setComposite(oldComposite);
        
        // SELEKCIJA SLIKE
//        if (element.isSelected()) {
//            Stroke oldStroke = g2.getStroke();
//            Color oldColor = g2.getColor();
//
//            g2.setColor(Color.BLUE);
//            g2.setStroke(new BasicStroke(2, BasicStroke.CAP_BUTT, BasicStroke.JOIN_BEVEL, 0, new float[]{6}, 0));
//
//            g2.drawRect(element.getX(), element.getY(), element.getWidth(), element.getHeight());
//            g2.setStroke(oldStroke);
//            g2.setColor(oldColor);
//        }
    }
}
