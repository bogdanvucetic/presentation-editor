package raf.graffito.dsw.serializer;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.SerializationFeature;
import raf.graffito.dsw.core.ApplicationFramework;
import raf.graffito.dsw.model.composite.Project;
import raf.graffito.dsw.observer.message.MessageType;

import java.io.File;
import java.io.IOException;

public class JacksonSerializer implements Serializer {
    private final ObjectMapper mapper;

    public JacksonSerializer() {
        this.mapper = new ObjectMapper();
        this.mapper.enable(SerializationFeature.INDENT_OUTPUT);
    }


    @Override
    public Project openProject(File file) {
        try {
            return mapper.readValue(file, Project.class);
        } catch (IOException e) {
            ApplicationFramework.getInstance().getMessageGenerator().createMessage("Neuspešno otvaranje projekta", MessageType.ERROR);
            return null;
        }
    }

    @Override
    public void saveProject(Project project, File file) {
        try {
            mapper.writeValue(file, project);
            ApplicationFramework.getInstance().getMessageGenerator().createMessage("Uspešno čuvanje projekta", MessageType.INFO);
        } catch (IOException e) {
            ApplicationFramework.getInstance().getMessageGenerator().createMessage("Neuspešno čuvanje projekta", MessageType.ERROR);
        }
    }
}
