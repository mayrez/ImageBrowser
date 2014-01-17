package imagebrowser.persistence.abstracts;

import imagebrowser.model.Bitmap;

public interface BitmapFactory<Attribute> {

    public Bitmap createBitmap(Attribute path);
}
