package heatmap;



import java.awt.AlphaComposite;
import java.awt.BasicStroke;
import java.awt.Color;
import java.awt.Composite;
import java.awt.Dimension;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.RenderingHints;
import java.awt.Stroke;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;

import javax.imageio.ImageIO;
import javax.swing.*;

public class Test2 extends JPanel {
    private static final int PREF_W = 400;
    private static final int PREF_H = PREF_W;
    private static final Stroke BASIC_STROKE = new BasicStroke(6f);
    BufferedImage backgroundImage;
    BufferedImage overlayImage;

    public Test2() throws IOException {
        backgroundImage = createBackGroundImage();
        overlayImage = createOverlayImage();
    }

    private  BufferedImage createBackGroundImage() throws IOException {
        BufferedImage image = ImageIO.read(new File("Map.png"));
        BufferedImage img = new BufferedImage(image.getWidth(),image.getHeight(),BufferedImage.TYPE_INT_ARGB);
        Graphics2D g2 = img.createGraphics();


        g2.dispose();
        return img;
    }

    private BufferedImage createOverlayImage() throws IOException {
        BufferedImage image = ImageIO.read(new File("java-heat-chart.png"));
        BufferedImage img = new BufferedImage(image.getWidth(),image.getHeight(),BufferedImage.TYPE_INT_ARGB);
        Graphics2D g2 = img.createGraphics();


            int rule = AlphaComposite.SRC_OVER;
            Composite comp = AlphaComposite.getInstance(rule , 1f );
            g2.setComposite(comp );

        g2.dispose();
        return img;
    }

    @Override
    public Dimension getPreferredSize() {
        return new Dimension(PREF_W, PREF_H);
    }

    @Override
    protected void paintComponent(Graphics g) {
        super.paintComponent(g);
        if (backgroundImage != null) {
            g.drawImage(backgroundImage, 0, 0, null);
        }
        if (overlayImage != null) {
            g.drawImage(overlayImage, 0, 0, null);
        }
    }

    private static void createAndShowGui() throws IOException {
        JFrame frame = new JFrame("TestAlphaComposite");
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.getContentPane().add(new Test2());
        frame.pack();
        frame.setLocationRelativeTo(null);
        frame.setVisible(true);
    }

    public static void main(String[] args) {
        SwingUtilities.invokeLater(new Runnable() {
            public void run() {
                try {
                    createAndShowGui();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        });
    }
}
