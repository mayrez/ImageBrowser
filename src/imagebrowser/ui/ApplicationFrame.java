package imagebrowser.ui;

import imagebrowser.ui.interfaces.ActionListenerFactory;
import imagebrowser.ui.interfaces.ImageViewer;
import imagebrowser.model.Image;
import java.awt.*;
import javax.swing.*;

public class ApplicationFrame extends JFrame {

    private ImageViewer viewer;
    private ActionListenerFactory factory;

    public ApplicationFrame(Image image, ActionListenerFactory factory) {
        super("Image Browser");
        this.setSize(1024, 700);
        this.setLocationRelativeTo(null);
        this.factory = factory;
        this.setDefaultCloseOperation(EXIT_ON_CLOSE);
        this.createComponents(image);


    }

    private void createComponents(Image image) {
        this.add(createImagePanel(image), BorderLayout.CENTER);
        this.add(createToolBar(), BorderLayout.SOUTH);
    }

    private ImagePanel createImagePanel(Image image) {
        viewer = new ImagePanel(image);
        return (ImagePanel) viewer;
    }

    private JPanel createToolBar() {
        JPanel panel = new JPanel(new FlowLayout(FlowLayout.CENTER));
        panel.add(createButton("PrevImage", "Prev"));
        panel.add(createButton("NextImage", "Next"));
        return panel;
    }

    private JButton createButton(String action, String label) {
        JButton button = new JButton(label);
        button.addActionListener(factory.createActionListener(action));
        return button;
    }

    public ImageViewer getViewer() {
        return viewer;
    }

    public void execute() {
        this.setVisible(true);
    }
}
