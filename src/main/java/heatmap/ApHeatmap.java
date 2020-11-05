package heatmap;

import javax.swing.*;


import java.awt.*;
import java.awt.geom.Rectangle2D;
        import java.awt.image.BufferedImage;
        import java.io.IOException;
        import java.util.logging.Level;
        import java.util.logging.Logger;
        import javax.imageio.ImageIO;
        import javax.swing.ImageIcon;
        import javax.swing.JFrame;
        import javax.swing.JLabel;
        import javax.swing.JPanel;
        import javax.swing.SwingUtilities;

public class ApHeatmap extends JLabel{

    BufferedImage img;

    public ApHeatmap() {
        try {
            img = ImageIO.read(getClass().getClassLoader().getResource("java-heat-chart.png"));
            setIcon(new ImageIcon(img));
        } catch (IOException ex) {
            Logger.getLogger(ImageGrid.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    @Override
    protected void paintComponent(Graphics g) {
                super.paintComponent(g);
                float alpha = 0.1f;
                // Create your own copy...
                Graphics2D g2d = (Graphics2D)g.create();
                g2d.setComposite(AlphaComposite.getInstance(AlphaComposite.SRC_OVER, alpha));
                g2d.drawImage(img, 0, 0, null);

                // Don't forget to dispose of it
                g2d.dispose();
    }

}