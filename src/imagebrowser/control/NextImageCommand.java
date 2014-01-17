package imagebrowser.control;

import imagebrowser.ui.interfaces.ImageViewer;

public class NextImageCommand extends ImageViewerCommand {

    public NextImageCommand(ImageViewer viewer) {
        super(viewer);
    }

    @Override
    public void execute() {
        viewer.setCurrentImage(viewer.getImage().getNextImage());
    }
}
