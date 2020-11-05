package heatmap;

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

public class ImageGrid extends JLabel {

//    private static final int CELLS = 10;
//    private static final int

    BufferedImage img;

    public ImageGrid() {
        try {
            img = ImageIO.read(getClass().getClassLoader().getResource("Map.png"));
            setIcon(new ImageIcon(img));
        } catch (IOException ex) {
            Logger.getLogger(ImageGrid.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    @Override
    protected void paintComponent(Graphics g) {
        super.paintComponent(g);
        if (img != null) {
//            Image background = Toolkit.getDefaultToolkit().createImage(map)
//            g.drawImage(img,0,0,null);
            Graphics2D g2 = (Graphics2D) g;
            double cellHeight = (double) (getHeight() / 4.0);
            double cellWidth = (double) (getWidth() / 10.0);
            for (int y = 0; y < getHeight(); y += cellHeight) {
                for (int x = 0; x < getWidth(); x += cellWidth) {
                    g2.setColor(Color.BLACK);
//                    Rectangle2D.Double rect = new Rectangle2D.Double(x, y, getWidth(), getHeight());
                    g2.draw(new Rectangle2D.Double(x, y, getWidth(), getHeight()));
                    System.out.println(cellWidth);
                    System.out.println(cellHeight);
//                    g.setColor(Color.CYAN);
//                    g.fillRect(x, y, cellWidth, cellHeight);

                }
            }
        }

    }




    public static void main(String[] args) {
        SwingUtilities.invokeLater(new Runnable() {
            public void run() {
                JFrame frame = new JFrame();

                ImageGrid grid =new ImageGrid();
                grid.setOpaque(true);

                frame.getContentPane().add(grid,BorderLayout.CENTER);

                ApHeatmap heatmap =  new ApHeatmap();
                heatmap.setOpaque(false);

//                GlassPane heatmap2 =
//                frame.setGlassPane();


                frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
                frame.pack();
                frame.setLocationRelativeTo(null);
                frame.setVisible(true);
            }
        });
    }
}
