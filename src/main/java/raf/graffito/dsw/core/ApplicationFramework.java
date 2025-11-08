package raf.graffito.dsw.core;
import raf.graffito.dsw.gui.swing.view.MainFrame;
import raf.graffito.dsw.model.GraffRepository;
import raf.graffito.dsw.model.GraffRepositoryImplementation;
import raf.graffito.dsw.observer.message.LoggerFactory;
import raf.graffito.dsw.observer.message.MessageGenerator;

public class ApplicationFramework {
    // Buduća polja za model celog projekta

    private static ApplicationFramework instance;
    private MessageGenerator messageGenerator;
    private GraffRepository graffRepository;

    private ApplicationFramework(){
        initialize();
    }

    public void initialize(){
        MainFrame mainFrame = MainFrame.getInstance();
        mainFrame.setVisible(true);

        messageGenerator = new MessageGenerator();
        graffRepository = new GraffRepositoryImplementation();

        LoggerFactory loggerFactory = new LoggerFactory();

        messageGenerator.addSubscriber(mainFrame);
        messageGenerator.addSubscriber(loggerFactory.createLogger("file"));
        messageGenerator.addSubscriber(loggerFactory.createLogger("console"));
    }

    public static ApplicationFramework getInstance(){
        if(instance == null){
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
