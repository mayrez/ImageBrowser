package imagebrowser.persistence;

import imagebrowser.model.Image;
import imagebrowser.model.RealImage;
import imagebrowser.persistence.abstracts.BitmapFactory;
import imagebrowser.persistence.abstracts.ImageLoader;

public class FileImageLoader implements ImageLoader {

    private String fileName;
    private BitmapFactory bitmapFactory;

    public FileImageLoader(String fileName, BitmapFactory bitmapFactory) {
        this.fileName = fileName;
        this.bitmapFactory = bitmapFactory;
    }

    @Override
    public Image load() {
        return new RealImage(bitmapFactory.createBitmap(fileName));
    }
}
