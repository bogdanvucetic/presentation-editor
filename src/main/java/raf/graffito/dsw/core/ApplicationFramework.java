package raf.graffito.dsw.core;
import raf.graffito.dsw.gui.swing.frames.MainFrame;
import raf.graffito.dsw.model.repository.GraffRepository;
import raf.graffito.dsw.model.repository.GraffRepositoryImpl;
import raf.graffito.dsw.services.events.message.logger.LoggerFactory;
import raf.graffito.dsw.services.events.message.MessageGenerator;

public class ApplicationFramework {
    private static ApplicationFramework instance;
    private MessageGenerator messageGenerator;
    private GraffRepository graffRepository;

    private ApplicationFramework(){
        messageGenerator = new MessageGenerator();
        graffRepository = new GraffRepositoryImpl();

        LoggerFactory loggerFactory = new LoggerFactory();
        messageGenerator.addSubscriber(loggerFactory.createLogger("file"));
        messageGenerator.addSubscriber(loggerFactory.createLogger("console"));
    }

    public void initialize(){
        MainFrame mainFrame = MainFrame.getInstance();
        messageGenerator.addSubscriber(mainFrame);
        mainFrame.setVisible(true);
    }

    public static ApplicationFramework getInstance(){
        if (instance == null) {
            instance = new ApplicationFramework();
        }
        return instance;
    }

    public GraffRepository getGraffRepository() {
        return graffRepository;
    }

    public MessageGenerator getMessageGenerator() {
        return messageGenerator;
    }
}
