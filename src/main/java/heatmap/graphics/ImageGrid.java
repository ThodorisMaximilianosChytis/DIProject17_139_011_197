package heatmap.graphics;

import javax.imageio.ImageIO;
import java.awt.*;
import java.awt.geom.Rectangle2D;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;

public class ImageGrid {


    public ImageGrid(String inimagepath,String imagewithgridpath) throws IOException {
        File inFile = new File(inimagepath);
        BufferedImage image = ImageIO.read(inFile);
//        BufferedImage resultImage2 = ImageToBufferedImage(transpImg2,image.getWidth(), image.getHeight());
        BufferedImage dest = new BufferedImage(image.getWidth(), image.getHeight(), BufferedImage.TYPE_INT_ARGB);
        Graphics2D g2 = dest.createGraphics();
        g2.drawImage(image, 0, 0, null);
        g2.dispose();

        Graphics2D g2d = image.createGraphics();
        double cellHeight = (double) (dest.getHeight() / 4.0);
        double cellWidth = (double) (dest.getWidth() / 10.0);
        for (int y = 0; y < dest.getHeight(); y += cellHeight) {
            for (int x = 0; x < dest.getWidth(); x += cellWidth) {
                g2d.setColor(Color.BLACK);
//                    Rectangle2D.Double rect = new Rectangle2D.Double(x, y, getWidth(), getHeight());
                g2d.draw(new Rectangle2D.Double(x, y, dest.getWidth(), dest.getHeight()));
                System.out.println(cellWidth);
                System.out.println(cellHeight);

            }
        }
        g2d.dispose();
        ImageIO.write(image, "png", new File(imagewithgridpath));



    }


}
