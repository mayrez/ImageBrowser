package imagebrowser.ui;

import imagebrowser.ui.interfaces.ImageViewer;
import imagebrowser.model.Image;
import java.awt.Graphics;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.awt.event.MouseMotionListener;
import javax.swing.*;

public final class ImagePanel extends JPanel implements ImageViewer {

    private Image image;
    private int imageWidth;
    private int imageHeight;
    private int offset;
    private int initialX;

    public ImagePanel(Image image) {
        super();
        this.offset = 0;
        setCurrentImage(image);
        this.hookEvents();
    }

    @Override
    public void setCurrentImage(Image image) {
        this.image = image;
        this.repaint();
    }

    @Override
    public void paint(Graphics graphics) {
        clearPanel(graphics);
        checkBounds((java.awt.Image) image.getBitmap().getImage());
        graphics.drawImage((java.awt.Image) image.getBitmap().getImage(), offset, 0,
                imageWidth, imageHeight, null);
    }

    private void clearPanel(Graphics graphics) {
        super.paint(graphics);
    }

    private void checkBounds(java.awt.Image imgage) {
        imageWidth = (imgage.getWidth(null) > this.getWidth()) ? this.getWidth() : imgage.getWidth(null);
        imageHeight = (imgage.getHeight(null) > this.getHeight()) ? this.getHeight() : imgage.getHeight(null);
    }

    @Override
    public Image getImage() {
        return image;
    }

    private void hookEvents() {
        this.hookMouseListener();
        this.hookMouseListenerMotion();



    }

    private void hookMouseListener() {
        this.addMouseListener(new MouseListener() {
            @Override
            public void mouseClicked(MouseEvent me) {
            }

            @Override
            public void mousePressed(MouseEvent me) {
                initialX = me.getX();
            }

            @Override
            public void mouseReleased(MouseEvent me) {
                if (offset > ((java.awt.Image) image.getBitmap().getImage()).getWidth(null) / 2) {
                    setCurrentImage(image.getPrevImage());
                }

                if (offset < -((java.awt.Image) image.getBitmap().getImage()).getWidth(null) / 2) {
                    setCurrentImage(image.getNextImage());
                }

                offset = 0;
                repaint();
            }

            @Override
            public void mouseEntered(MouseEvent me) {
            }

            @Override
            public void mouseExited(MouseEvent me) {
            }
        });
    }

    private void hookMouseListenerMotion() {
        this.addMouseMotionListener(new MouseMotionListener() {
            @Override
            public void mouseDragged(MouseEvent me) {
                offset = me.getX() - initialX;
                repaint();
            }

            @Override
            public void mouseMoved(MouseEvent me) {
            }
        });
    }
}
