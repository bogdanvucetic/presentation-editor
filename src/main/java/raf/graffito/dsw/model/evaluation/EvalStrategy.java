package raf.graffito.dsw.model.evaluation;

import raf.graffito.dsw.model.composite.GraffNode;

import java.awt.*;
import java.util.List;

public interface EvalStrategy {
    double computeRemainingSpacePercentage(Dimension resolution, List<GraffNode> elements);
}
