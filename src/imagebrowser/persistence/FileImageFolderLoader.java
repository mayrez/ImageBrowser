package imagebrowser.persistence;

import imagebrowser.persistence.abstracts.ImageFolderLoader;
import imagebrowser.persistence.abstracts.BitmapFactory;
import imagebrowser.model.Image;
import java.io.File;
import java.util.*;

public class FileImageFolderLoader implements ImageFolderLoader {

    private final File folder;
    private final BitmapFactory bitmapFactory;
    private final ArrayList<String> formats;

    public FileImageFolderLoader(String path, BitmapFactory bitmapFactory) {
        this.folder = new File(path);
        this.bitmapFactory = bitmapFactory;
        this.formats = new ArrayList<>(Arrays.asList(new String[]{
            ".bmp", ".jpg", ".jpeg", ".jpe", ".jfif", ".dib", ".png", ".gif", "tiff"}));
    }

    @Override
    public Image[] load() {
        return linkedImages(createProxies(createFilesList(folder.listFiles(), new ArrayList<String>())));
    }

    private String[] createFilesList(File[] filesName, ArrayList<String> list) {
        for (File file : filesName) {
            int imageIndex = file.getName().lastIndexOf(".");
            if (imageIndex > -1) {
                if (formats.contains(file.getName().substring(imageIndex))) {
                    list.add(file.getName());
                }
            }
        }
        return list.toArray(new String[0]);
    }

    private ProxyImage[] createProxies(String[] filesName) {
        ProxyImage[] proxyImagesList = new ProxyImage[filesName.length];
        for (int i = 0; i < proxyImagesList.length; i++) {
            proxyImagesList[i] = new ProxyImage(new FileImageLoader(folder.getAbsolutePath() + "/" + filesName[i], bitmapFactory));
        }

        return proxyImagesList;
    }

    private Image[] linkedImages(ProxyImage[] images) {
        for (int i = 0; i < images.length; i++) {
            images[i].setNextImage(images[(i + 1) % images.length]);
            images[i].setPrevImage(images[(i - 1 + images.length) % images.length]);
        }
        return images;
    }
}
