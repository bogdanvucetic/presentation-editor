package raf.graffito.dsw.serializer;

import raf.graffito.dsw.model.composite.Project;

import java.io.File;

public interface Serializer {
    Project openProject(File file);
    void saveProject(Project project, File file);
}
