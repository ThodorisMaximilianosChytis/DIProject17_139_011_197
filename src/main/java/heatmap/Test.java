package heatmap;

import javax.imageio.ImageIO;
import java.awt.*;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;

public class Test {
    public static void main(String[] args){


        BufferedImage image = null;
        try {
            image = ImageIO.read(new File("Map.png"));
        } catch (IOException e) {
            e.printStackTrace();
        }
        BufferedImage overlay = null;
        try {
            overlay = ImageIO.read(new File( "java-heat-chart.png"));
        } catch (IOException e) {
            e.printStackTrace();
        }

        BufferedImage bimage = new BufferedImage(image.getWidth(), image.getHeight(), BufferedImage.TYPE_INT_ARGB);
        BufferedImage boverlay = new BufferedImage(overlay.getWidth(), overlay.getHeight(), BufferedImage.TYPE_INT_ARGB);

// create the new image, canvas size is the max. of both image sizes
        int w = Math.max(bimage.getWidth(), boverlay.getWidth());
        int h = Math.max(bimage.getHeight(), boverlay.getHeight());
        BufferedImage combined = new BufferedImage(w, h, BufferedImage.TYPE_INT_ARGB);

// paint both images, preserving the alpha channels
        Graphics2D g2 = (Graphics2D) combined.getGraphics();


        g2.drawImage(bimage, 0, 0, null);



        Graphics2D g = (Graphics2D) boverlay.getGraphics();

        Graphics2D transg = (Graphics2D) g.create();
        AlphaComposite ac = java.awt.AlphaComposite.getInstance(AlphaComposite.SRC_OVER, 0.5f);
        transg.setComposite(ac);

        transg.drawImage(boverlay, 0, 0, null);



        g2.drawImage(boverlay,0,0,null);


// Save as new image
        try {
            ImageIO.write(combined, "PNG", new File("combined.png"));
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
