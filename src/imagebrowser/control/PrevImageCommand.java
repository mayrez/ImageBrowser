package imagebrowser.control;

import imagebrowser.ui.interfaces.ImageViewer;

public class PrevImageCommand extends ImageViewerCommand {

    public PrevImageCommand(ImageViewer viewer) {
        super(viewer);
    }

    @Override
    public void execute() {
        viewer.setCurrentImage(viewer.getImage().getPrevImage());
    }
}
