package raf.graffito.dsw.serializer;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.SerializationFeature;
import raf.graffito.dsw.core.ApplicationFramework;
import raf.graffito.dsw.model.composite.GraffNode;
import raf.graffito.dsw.model.composite.GraffNodeComposite;
import raf.graffito.dsw.model.composite.Project;
import raf.graffito.dsw.observer.message.MessageType;

import java.io.File;
import java.io.IOException;
import java.util.List;

public class JacksonSerializer implements Serializer {
    private final ObjectMapper mapper;

    public JacksonSerializer() {
        this.mapper = new ObjectMapper();
        this.mapper.enable(SerializationFeature.INDENT_OUTPUT);
    }


    @Override
    public Project openProject(File file) {
        try {
            Project p = mapper.readValue(file, Project.class);
            relinkParents(p);
            subscribeProjectToChildren(p, p.getChildren());
            return p;
        } catch (IOException e) {
            ApplicationFramework.getInstance().getMessageGenerator().createMessage("Neuspešno otvaranje projekta: " + e.getMessage(), MessageType.ERROR);
            return null;
        }
    }

    @Override
    public void saveProject(Project project, File file) {
        try {
            mapper.writeValue(file, project);
            ApplicationFramework.getInstance().getMessageGenerator().createMessage("Projekat uspešno sačuvan", MessageType.INFO);
        } catch (IOException e) {
            ApplicationFramework.getInstance().getMessageGenerator().createMessage("Projekat nije sačuvan: " + e.getMessage(), MessageType.ERROR);
        }
    }

    private void relinkParents(GraffNode node) {
        if (node instanceof GraffNodeComposite composite) {
            for (GraffNode child : composite.getChildren()) {
                child.setParent(node);
                relinkParents(child);
            }
        }
    }

    private void subscribeProjectToChildren(Project project, List<GraffNode> children) {
        for (GraffNode child : children) {
            if (child instanceof GraffNodeComposite composite) {
                subscribeProjectToChildren(project, composite.getChildren());
            }

            child.addSubscriber(project);
        }
    }
}
