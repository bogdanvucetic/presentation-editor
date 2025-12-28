package raf.graffito.dsw.model.evaluation;

import raf.graffito.dsw.model.composite.GraffNode;
import raf.graffito.dsw.model.composite.elements.SlideElement;

import java.awt.*;
import java.util.List;

public class PreciseEvalStrategy implements EvalStrategy {
    @Override
    public double computeRemainingSpacePercentage(Dimension resolution, List<GraffNode> elements) {
        boolean[][] occupationMatrix = new boolean[resolution.width][resolution.height];

        for (GraffNode element : elements) {
            if (!(element instanceof SlideElement e)) {
                continue;
            }

            markOccupiedSpace(occupationMatrix, e, resolution);
        }

        int totalSpace = resolution.width * resolution.height;
        int occupiedSpace = 0;

        for (int x = 0; x < resolution.width; x++) {
            for (int y = 0; y < resolution.height; y++) {
                if (occupationMatrix[x][y]) {
                    occupiedSpace++;
                }
            }
        }

        return (double) (totalSpace - occupiedSpace) / totalSpace;
    }

    private void markOccupiedSpace(boolean[][] matrix, SlideElement element, Dimension resolution) {
        int startX = Math.max(0, Math.min(resolution.width - 1, element.getXCoordinate()));
        int endX = Math.min(resolution.width, element.getXCoordinate() + element.getWidth());
        int startY = Math.max(0, Math.min(resolution.height - 1, element.getYCoordinate()));
        int endY = Math.min(resolution.height, element.getYCoordinate() + element.getHeight());

        if (startX >= endX || startY >= endY) {
            return;
        }

        for (int x = startX; x < endX; x++) {
            for (int y = startY; y < endY; y++) {
                matrix[x][y] = true;
            }
        }
    }
}
