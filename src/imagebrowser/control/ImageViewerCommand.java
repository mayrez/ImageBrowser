package imagebrowser.control;

import imagebrowser.control.interfaces.Command;
import imagebrowser.ui.interfaces.ImageViewer;

public abstract class ImageViewerCommand implements Command {

    protected final ImageViewer viewer;

    public ImageViewerCommand(ImageViewer viewer) {
        this.viewer = viewer;
    }

    @Override
    public abstract void execute();
}
