package imagebrowser.Application;

import imagebrowser.control.interfaces.Command;
import imagebrowser.control.NextImageCommand;
import imagebrowser.control.PrevImageCommand;
import imagebrowser.model.Bitmap;
import imagebrowser.model.Image;
import imagebrowser.persistence.abstracts.BitmapFactory;
import imagebrowser.persistence.FileImageFolderLoader;
import imagebrowser.persistence.abstracts.ImageFolderLoader;
import imagebrowser.ui.ApplicationFrame;
import imagebrowser.ui.SwingBitmap;
import imagebrowser.ui.interfaces.ActionListenerFactory;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.HashMap;

public class Application {
    
    private static HashMap<String, Command> commandDictionary;
    private static final String PATH = "C:/Users/Public/Pictures/Sample Pictures";
    
    public static void main(String[] args) {
        new Application().execute();
    }

    private void execute() {
        ApplicationFrame frame = createApplicationFrame(createImageFolderLoader().load()[0]);
        createCommands(frame);
        frame.execute();
    }

    private static ImageFolderLoader createImageFolderLoader() {
        return new FileImageFolderLoader(PATH, new BitmapFactory<String>() {
            @Override
            public Bitmap createBitmap(String path) {
                return new SwingBitmap(path);
            }
        });
    }

    private static ApplicationFrame createApplicationFrame(Image image) {
        return new ApplicationFrame(image, new ActionListenerFactory() {
            @Override
            public ActionListener createActionListener(final String action) {
                return new ActionListener() {
                    @Override
                    public void actionPerformed(ActionEvent ae) {
                        if (commandDictionary.get(action) != null) {
                            commandDictionary.get(action).execute();
                        }
                    }
                };
            }
        });
    }

    private static void createCommands(ApplicationFrame frame) {
        commandDictionary = new HashMap<>();
        commandDictionary.put("NextImage", new NextImageCommand(frame.getViewer()));
        commandDictionary.put("PrevImage", new PrevImageCommand(frame.getViewer()));
    }
}
