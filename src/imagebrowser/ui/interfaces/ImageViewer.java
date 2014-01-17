package imagebrowser.ui.interfaces;

import imagebrowser.model.Image;

public interface ImageViewer {

    public Image getImage();

    public void setCurrentImage(Image image);
}
