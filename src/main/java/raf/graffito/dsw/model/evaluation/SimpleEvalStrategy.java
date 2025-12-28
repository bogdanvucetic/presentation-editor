package raf.graffito.dsw.model.evaluation;

import raf.graffito.dsw.model.composite.GraffNode;
import raf.graffito.dsw.model.composite.elements.SlideElement;

import java.awt.*;
import java.util.List;

public class SimpleEvalStrategy implements EvalStrategy {
    @Override
    public double computeRemainingSpacePercentage(Dimension resolution, List<GraffNode> elements) {
        int totalSpace = resolution.width * resolution.height;
        int occupiedSpace = 0;

        for (GraffNode element : elements) {
            if (!(element instanceof SlideElement e)) {
                continue;
            }

            occupiedSpace += e.getWidth() * e.getHeight();
        }

        return (double) (totalSpace - occupiedSpace) / totalSpace;
    }
}
