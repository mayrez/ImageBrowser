package imagebrowser.persistence;

import imagebrowser.persistence.abstracts.ImageLoader;
import imagebrowser.model.Bitmap;
import imagebrowser.model.Image;
import imagebrowser.model.RealImage;

public class ProxyImage implements Image {

    private RealImage realImage;
    private Image prevImage;
    private Image nextImage;
    private final ImageLoader loader;

    public ProxyImage(ImageLoader loader) {

        this.loader = loader;
    }

    @Override
    public Image getNextImage() {
        return nextImage;
    }

    @Override
    public void setNextImage(Image image) {
        nextImage = image;
    }

    @Override
    public Image getPrevImage() {
        return prevImage;
    }

    @Override
    public void setPrevImage(Image image) {
        prevImage = image;
    }

    @Override
    public Bitmap getBitmap() {
        if (realImage == null) {
            realImage = (RealImage) loader.load();
        }
        return realImage.getBitmap();
    }
}
