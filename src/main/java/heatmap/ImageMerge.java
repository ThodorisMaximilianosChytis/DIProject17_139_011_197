package heatmap;

import java.awt.AlphaComposite;
import java.awt.Graphics2D;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import java.net.URL;
import java.util.logging.Handler;
//from  ww  w .j a v a  2  s  .c  o  m
import javax.imageio.ImageIO;
import javax.swing.ImageIcon;
import javax.swing.JFrame;
import javax.swing.JLabel;

public class ImageMerge {

    private  BufferedImage mergedimage;
    public  ImageMerge(String background, String overlay, String mergedphoto) throws IOException {

        /// RSSI MERGE
        BufferedImage im = ImageIO.read(new File(overlay));
        BufferedImage im2 = ImageIO.read(new File(background));
        Graphics2D g = im.createGraphics();
        g.setComposite(AlphaComposite.getInstance(AlphaComposite.SRC_OVER, 0.4f));
        g.drawImage(im2, (im.getWidth() - im2.getWidth()) / 2, (im.getHeight() - im2.getHeight()) / 2, null);
        g.dispose();
        ImageIO.write(im, "png", new File(mergedphoto));
        mergedimage = im;

    }

    public void display() throws IOException{

        JFrame f = new JFrame();
        f.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        f.getContentPane().add(new JLabel(new ImageIcon(this.mergedimage)));
        f.pack();
        f.setVisible(true);
    }

    public BufferedImage getMergedimage() {
        return mergedimage;
    }
}





